//다른 .js 파일에서 동일한 이름의 function이 발생되어도 서로 영향을 끼치지 않도록 index 변수에 function추가
//가장 하단에 main.init() 넣어줘야 index.js 실행됨
var main = {
    init :  function () {
        var _this = this;
        $('#btn-save').on('click',function() {
            _this.save();
        });
    },
    save : function() {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};

main.init()