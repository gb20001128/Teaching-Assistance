// 学生与教师登陆界面的切换设置
$(function () {
    $('.body-r-nav li').click(function () {
        var _id = $(this).index();
        console.log(_id);
        $(this).addClass('body-r-nav-active').siblings().removeClass('body-r-nav-active');
        var index = $(this).index();
        $('#video-box-one,#video-box-two').hide();
        $('#video-box-one').eq(index).show();
        $('.body-r-body').find('.video-box').eq(_id).css('display', 'block').siblings('.video-box').css('display', 'none');
    });
});

// 学生登陆界面验证码设置
$('.captcha').click(function () {
    var studentname = $('#stuname').val();
    var studentqq = $('#stuphone').val();
    console.log(studentname, studentqq);

    // 输入判断
    var reg = /^[\u4e00-\u9fa5]{2,4}$/;
    var num = /^[1-9]\d{6,11}$/;
    if (studentname == '') {
        alert('请输入学生姓名');
    } else if (!reg.test(studentname)) {
        alert('输入的学生名字不符合要求');
        $('#stuname').val('');
        // return false;
    }
    if (studentqq == '') {
        alert('请输入学生QQ');
    } else if (!num.test(studentqq)) {// 学生QQ必须是数字
        alert('输入的学生QQ不符合要求');
        $('#stuphone').val('');
        return false;
    }
    // 如果输入正确，发送ajax请求
    if (studentname != '' && studentqq != '' && reg.test(studentname) && num.test(studentqq)) {
        // alert('发送验证码');
        $.ajax({
            url: './send_captcha.php',
            type: 'POST',
            data: {
                studentname: studentname,
                studentqq: studentqq
            },
            success: function (data) {
                console.log(data);
                if (data == 'success') {
                    alert('验证码已发送');
                } else {
                    //alert('验证码发送失败');
                }
            }
        });
    }

});


// 教师界面登录按钮点击设置
$('#teabtn').click(function () {
    var teachername = $('#teaname').val();
    var teacherpassword = $('#teapswd').val();
    console.log(teachername, teacherpassword);

    // 输入判断
    var reg = /^[\u4e00-\u9fa5]{2,4}$/;
    var pswd = /^[a-zA-Z0-9]{6,12}$/;
    if (teachername == '') {
        alert('请输入教师姓名');
    } else if (!reg.test(teachername)) {
        alert('输入的教师名字不符合要求');
        $('#teaname').val('');
        // return false;
    }
    if (teacherpassword == '') {
        alert('请输入教师密码');
    } else if (!pswd.test(teacherpassword)) {// 教师密码必须是数字
        alert('输入的教师密码不符合要求');
        $('#teapswd').val('');
        return false;
    }
    // 如果输入正确，发送ajax请求
    if (teachername != '' && teacherpassword != '' && reg.test(teachername) && pswd.test(teacherpassword)) {
        // alert('发送验证码');
        $.ajax({
            url: './send_captcha.php',
            type: 'POST',
            data: {
                teachername: teachername,
                teacherpassword: teacherpassword
            },
            success: function (data) {
                console.log(data);
                if (data == 'success') {
                    alert('验证码已发送');
                } else {
                    //alert('验证码发送失败');
                }
            }
        });
    }
});