var popup = {
    init: function () {
        var _this = this;
        var url = opener.window.location.href;
        $('#share-content').val(url);
        $('#share-content').attr("readonly", true);

        $('#url-copy-btn').on("click", function () {
            _this.copyUrl();
        })

        $("#popup-close-btn").on("click", function () {
            _this.close();
        })
    },
    copyUrl: function () {
        var copyText = $("#share-content");
        copyText.select();
        document.execCommand("Copy");
        alert(" 주소가 복사되었습니다. \'Ctrl+V\'를 눌러 붙여넣기 해주세요.");
    },
    close: function () {
        alert('이 피드를 공유합니다.');
        self.close();
    }
};
popup.init();