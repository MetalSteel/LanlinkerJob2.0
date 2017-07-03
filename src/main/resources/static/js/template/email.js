$(function () {
    // 初始化邮箱服务和内容
    $.ajax({
        url:"/background/findEmail",
        type:"GET",
        success:function (data) {
            $("#emailHost").val(data.host);
            $("#emailUsername").val(data.username);
            $("#emailPassword").val(data.password);
            $("#emailSubject").val(data.subject);
            $("#emailStart").val(data.start);
            $("#emailEnd").val(data.end);
        }
    })

})
// 更新Email配置
function updateEmail() {
    $.ajax({
        url:"/background/updateEmail",
        type:"POST",
        data:$("#emailForm").serialize(),
        success:function (data) {
            $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
            $("#toastMsgDiv").css({"display":"block"});
            $("#toastMsg").html(data.msg).fadeIn(300).delay(2000).fadeOut(300);
        }
    })
}