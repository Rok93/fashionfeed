function resetPagination() {
    $(".page-item.active").removeClass("active");
    $(".page-item").eq(1).addClass("active");
}

var index = {
    init: function () {
        var _this = this;

        $('#feed').on("click", function (evt) {
            _this.feedClick(evt);
        });

        // N개씩 보기 selector 클릭 이벤트
        $("#size_selector").change(function () {
            var getSize = $(this).val();
            _this.feedSearch(0, getSize);
            resetPagination();
        });

        // 페이지 클릭 이벤트
        $(".page-item").on("click", function (evt) {
            $(".page-item.active").removeClass("active");
            $(this).addClass("active");
            var getPage = $(".page-item.active").filter(":first").text();  //todo: 이걸... 어떻게 jQuery로 바꾸지?
            var getSize = $("#size_selector").val();
            _this.feedSearch(getPage - 1, getSize);
        });
    },
    feedClick: function (evt) {
        var targetTagName = evt.target.tagName;
        var parentoffsetParent;

        if (targetTagName === "IMG") {
            parentoffsetParent = evt.target.parentElement.parentElement;
        } else if (targetTagName === "P") {
            parentoffsetParent = evt.target.offsetParent;
        } else if (targetTagName === "FIGURE") {
            parentoffsetParent = evt.target.parentElement;
        }

        window.location.href = "/feeds/" + parentoffsetParent.id;
    },
    feedSearch: function (getPage, getSize) {
        $.ajax({
            url: "/api/feeds?page=" + getPage + "&size=" + getSize,
            type: 'GET',
            dataType: "json",
            contentType: 'application/json; charset=utf-8',
        }).done(function (jsonData) {

            if (jsonData.last === true) {
                //todo: 페이징 처리 (다음, 이전 버튼...)
            }

            var itemList = jsonData.content;
            var itemTemplate = document.querySelector("#item-template").innerHTML;
            var resultHtml = "";

            for (var i = 0; i < itemList.length; i++) {
                var item = itemList[i];
                debugger;
                resultHtml += itemTemplate.replace("{id}", item.id)
                    .replace("{feedImage}", item.feedImage)
                    .replace("{feedTitle}", item.feedTitle)
                    .replace("{feedContent}", item.feedContent)
                    .replace("{createdDate}", item.createdDate)
                    .replace("{totalLikefullNumber}", item.likefulls.length)
                    .replace("{totalChatNumber}", item.chats.length)
                    .replace("{totalShareNumber}", item.shares.length);
            }

            document.querySelector("#feed").innerHTML = resultHtml;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
};

var loginFunction = {
    init: function () {
        var _this = _this;

        $("#login-btn").on("click", function () {
            window.location.href = "/login";
            alert("로그인 되었습니다.");
        });

        $("#logout-btn").on("click", function () {
            window.location.href = "/logout";
            alert("로그아웃 되었습니다.");
        });
    },
};

index.init();
loginFunction.init();