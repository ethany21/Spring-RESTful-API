var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            firstName : $('#firstName').val(),
            lastName : $('#lastName').val(),
            email : $('#email').val(),
            gender : $('#gender').val()
        };

        $.ajax({
            type: 'POST',
            url: '/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert("saved new Customer");
            window.location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
};


main.init();