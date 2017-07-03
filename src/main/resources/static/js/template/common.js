/**
 * Created by Administrator on 2017/6/20.
 */
// 初始化应聘职位
function initJob(){
    $.ajax({
        url:"/rest/findJobs",
        type:"GET",
        success:function(data){
            $("#job").empty();
            $("#job").append("<option value=''>请选择应聘职位</option>");
            for(var i=0;i<data.length;i++){
                var tmp = data[i];
                $("#job").append("<option value='"+tmp.id+"'>"+tmp.name+"</option>");
            }
        }
    });
}
// 初始化学历
function initEdu(){
    $.ajax({
        url:"/rest/findEducations",
        type:"GET",
        success:function(data){
            $("#education").empty();
            $("#education").append("<option value=''>请选择学历</option>");
            for(var i=0;i<data.length;i++){
                var tmp = data[i];
                $("#education").append("<option value='"+tmp.id+"'>"+tmp.name+"</option>");
            }
        }
    });
}