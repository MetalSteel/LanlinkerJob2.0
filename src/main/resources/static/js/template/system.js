$(function () {
    var total = 0;
    // 简历总数量
    $.ajax({
        url:"/background/countAllResumes",
        async:"false",
        success:function (data) {
            total = parseInt(data.msg);
            $("#resumesAll").text(data.msg);
            // 初试通过率
            $.ajax({
                url:"/rest/countResumesByStatus?status=5",
                async:"false",
                success:function (data) {
                    $("#resumesPreliminary").text(getPercent(data.msg,total));
                }
            })
            // 复试通过率
            $.ajax({
                url:"/rest/countResumesByStatus?status=7",
                async:"false",
                success:function (data) {
                    $("#resumesRetrial").text(getPercent(data.msg,total));
                }
            })
        }
    })
    // 今日新增
    $.ajax({
        url:"/background/countTodayResumes",
        async:"false",
        success:function (data) {
            $("#resumesToday").text(data.msg);
        }
    })
})
///计算两个整数的百分比值
function getPercent(num, total) {
    num = parseFloat(num);
    total = parseFloat(total);
    if (isNaN(num) || isNaN(total)) {
        return "-";
    }
    return total <= 0 ? "0%" : (Math.round(num / total * 10000) / 100.00);
}