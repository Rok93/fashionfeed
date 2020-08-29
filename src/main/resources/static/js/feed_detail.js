var currentFeedId = window.location.pathname.split("/")[2];

// 댓글 기능
var chatFunction = { //todo: CRUD 모두 구현(C는 여기서 구현하지 않았음)
    init: function () {
        var _this = this;
        $('#chat-save').on('click', function () {
            _this.save();
        });

        $('.chat-update').on('click', function (evt) {
            var chatId = evt.target.parentElement.parentElement.id;
            // _this.update(chatId); todo: 댓글 업데이트 기능 추가예정
        });

        $('.chat-delete').on('click', function (evt) {
            var chatId = evt.target.parentElement.parentElement.parentElement.id;
            _this.delete(chatId);
        });

    },
    save: function () {
        var data = {
            content: $('#chat_content').val(),
        };
        $.ajax({
            type: 'POST',
            url: '/api/' + currentFeedId + '/chats',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('댓글이 등록되었습니다.');
            window.location.href = window.location.pathname; //현재 페이지로 다시돌아가기
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update: function (chatId) {
        let data = {
            content: $('#chat_content').val(),
        };
        $.ajax({
            type: 'PUT',
            url: '/api' + currentFeedId + '/chats/' + chatId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (data) {
            alert('댓글이 수정되었습니다.');
            window.location.href = window.location.pathname; //현재 페이지로 다시돌아가기
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function (chatId) {
        $.ajax({
            type: 'DELETE',
            url: '/api/chats/' + chatId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('댓글이 삭제되었습니다.');
            window.location.href = window.location.pathname;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
};

// 좋아요 기능 !!
let likeFunction = {
    init: function () {
        var _this = this;
        //공유하기 버튼 클릭!
        $("#like-execution").on("click", function () {
            _this.put();
        });

        //공유 취소 버튼 클릭!
        $("#like-cancel").on("click", function () {
            _this.delete();
        });
    },
    put: function () {
        $.ajax({
            type: 'PUT',
            url: '/api/' + currentFeedId + '/likes',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function (jsonData) {
            if (jsonData === 0) {
                alert('로그인을 해주세요!');
                return;
            }
            alert('이 피드를 좋아합니다.');
            window.location.href = window.location.pathname; //현재 페이지로 다시돌아가기
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        $.ajax({
            type: 'DELETE',
            url: '/api/' + currentFeedId + '/likes',
        }).done(function () {
            alert('이 피드를 좋아하지 않습니다.');
            window.location.href = window.location.pathname;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

// 공유 기능
let shareFunction = {
    init: function () {
        var _this = this;
        //공유하기 버튼 클릭!
        $("#share-active").on("click", function () {
            window.open("/shares/popup", "공유하기", "width=400, height=200, left=0, top=0");
            _this.put();
        });
        //공유 취소 버튼 클릭!
        $("#share-cancel").on("click", function () {
            _this.delete();
        });
    },
    put: function () {
        $.ajax({
            type: 'PUT',
            url: '/api/' + currentFeedId + '/shares',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function (jsonData) {
            if (jsonData === 0) {
                alert(jsonData);
                alert('로그인을 해주세요!');
                return;
            }
            window.location.href = window.location.pathname; //현재 페이지로 다시돌아가기
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {
        $.ajax({
            type: 'DELETE',
            url: '/api/' + currentFeedId + '/shares',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function () {
            alert('이 피드의 공유를 회수합니다.');
            window.location.href = window.location.pathname;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

chatFunction.init();
likeFunction.init();
shareFunction.init();
