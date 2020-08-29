function resetPagination() {
    $(".page-item.active").removeClass("active");
    $(".page-item").eq(1).addClass("active");
}

let index = {
    init: function () {
        let _this = this;

        $('#feed').on("click", function (evt) {
            _this.feedClick(evt);
        });

        // N개씩 보기 selector 클릭 이벤트
        $("#size_selector").change(function () {
            let getSize = $(this).val();
            _this.feedSearch(0, getSize);
            resetPagination();
        });

        // 페이지 클릭 이벤트
        $(".page-item").on("click", function (evt) {
            $(".page-item.active").removeClass("active");
            $(this).addClass("active");
            let getPage = $(".page-item.active").filter(":first").text();  //todo: 이걸... 어떻게 jQuery로 바꾸지?
            let getSize = $("#size_selector").val();
            _this.feedSearch(getPage - 1, getSize);
        });
    },
    feedClick: function (evt) {
        let targetTagName = evt.target.tagName;
        let parentoffsetParent;

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

            let itemList = jsonData.content;

            let resultHtml = "";
            for (let i = 0; i < itemList.length; i++) {
                let item = itemList[i];
                resultHtml +=
                    `<div class="item" id="${item.id}">
                       <figure>
                         <img src="${item.feedImage}" alt="${item.feedTitle}" class="feedMediaItem w-100">
                       </figure>
                       <article class="feedTags">
                         <p class="feedText" style="white-space: pre-line">${item.feedContent}</p>
                       </article>
                       <div class="item-ft">
                         <em class="feedDate">${item.createdDate}</em>
                         <ul class="main_page feed_icon_wrap">
                           <li>
                             <img src="images/like_icon.png" class="icon-btn_likefull">
                             <span>${item.likefulls.length}</span>
                           </li>
                           <li>
                             <img src="images/comment_icon.png" class="icon-ico_chat">
                             <span>${item.chats.length}</span>
                           </li>
                           <li>
                             <img src="images/share_icon.png" class="icon-ico_share"></i>
                             <span>${item.shares.length}</span>
                           </li>
                         </ul>
                       </div>
                    </div>`
            }

            document.querySelector("#feed").innerHTML = resultHtml;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },
};

index.init();
