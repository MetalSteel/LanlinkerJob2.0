// 初始化
$(function () {
    // 添加首页
    addFunction('/background/system');
    // 未处理,上传图片,上传简历
    var total = 0;
    // 未处理简历数量
    $.ajax({
        url:"/rest/countResumesByStatus?status=0",
        type:"GET",
        success:function (data) {
            total += parseInt(data.msg);
            $.ajax({
                url:"/rest/countResumesByStatus?status=1",
                type:"GET",
                success:function (data) {
                    total += parseInt(data.msg);
                    $.ajax({
                        url:"/rest/countResumesByStatus?status=2",
                        type:"GET",
                        success:function (data) {
                            total += parseInt(data.msg);
                            if(total != 0){
                                $("#noProcessCountSpan").text(total);
                            }else{
                                $("#noProcessCountSpan").text('');
                            }
                        }
                    })
                }
            })
        }
    })

    // 初试简历数量
    $.ajax({
        url:"/rest/countResumesByStatus?status=3",
        type:"GET",
        success:function (data) {
            if(data.msg != 0){
                $("#preliminaryCountSpan").text(data.msg);
            }else{
                $("#preliminaryCountSpan").text('');
            }
        }
    })

    // 复试简历数量
    $.ajax({
        url:"/rest/countResumesByStatus?status=5",
        type:"GET",
        success:function (data) {
            if(data.msg != 0){
                $("#retrialCountSpan").text(data.msg);
            }else{
                $("#retrialCountSpan").text('');
            }
        }
    })
});
// 添加页面到Container容器函数
function  addFunction(view) {
    // 清空菜单上的提示
    $("#preliminaryCountSpan").text('');
    $("#noProcessCountSpan").text('');
    $("#retrialCountSpan").text('');
    // 清空容器
    $("#center_container").empty();
    $("#center_container").append(
        "<div class='spinner'><div class='spinner-container container1'><div class='circle1'></div> <div class='circle2'></div> <div class='circle3'></div> <div class='circle4'></div> </div> <div class='spinner-container container2'> <div class='circle1'></div> <div class='circle2'></div> <div class='circle3'></div> <div class='circle4'></div> </div> <div class='spinner-container container3'> <div class='circle1'></div> <div class='circle2'></div> <div class='circle3'></div> <div class='circle4'></div> </div> </div>"
    );
    $.ajax({
        url:view,
        cache:false,
        success:function(html){
            $("#center_container").empty();
            $("#center_container").append(html);
        }
    });
}
$(".sidebar-menu>li").click(function () {
    console.log($(this));
   $(this).addClass('active')
       .siblings().removeClass('active');
});