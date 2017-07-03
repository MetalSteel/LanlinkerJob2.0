/**
 * Created by Administrator on 2017/6/20.
 */
$(function () {
    // 初始化应聘职位
    initJob();
})
// 简历
var resumes = null;
// 初始化初试人员
$("#job").change(function () {
    var selectVal = $("#job").val();
    $("#name").empty();
    $("#name").append("<option value='-1'>请选择简历</option>");
    $.ajax({
        type:"GET",
        url:"/rest/findResumeByJobAndStatus",
        data:"job="+selectVal+"&status=3",
        success:function(data){
            resumes = data;
            for(var i=0;i<data.length;i++){
                var tmp = data[i];
                $("#name").append("<option value='"+tmp.id+"'>"+tmp.name+"</option>");
            }
        }
    });
});

// 学历
var educations = null;
function getEducations() {
    $.ajax({
        url:"/rest/findEducations",
        type:"GET",
        success:function(data){
            educations = data;
        }
    });
}
getEducations();
// 简历编号
var resumeId = null;
// 个人简历
$("#name").change(function () {
    var selectVal = $("#name").val();
    for (var i=0;i<resumes.length;i++){
        var resume = resumes[i];
        if(resume.id == selectVal){
            // 初始化简历编号
            resumeId = resume.id;
            $("#resumeId").val(resumeId);
            // 初始化个人简历
            $("#card").text(resume.card);
            $("#register").text(resume.register);
            $("#address").text(resume.address);
            $("#phone").text(resume.phone);
            $("#email").text(resume.email);
            $("#qq").text(resume.qq);
            $("#university").text(resume.university);
            for (var i=0;i<educations.length;i++){
                var edu = educations[i];
                if(edu.id == resume.education){
                    $("#education").text(edu.name);
                    break;
                }
            }
            $("#major").text(resume.major);
            $("#workYear").text(resume.workYear+"年");
            $("#workMinSalary").text(resume.workMinSalary+"元");
            $("#workMaxSalary").text(resume.workMaxSalary+"元");
            $("#workGoDate").text(resume.workGoDate);
            if(resume.workStatus == true){
                $("#workStatus").text("在职");
            }else {
                $("#workStatus").text("离职");
            }
            if(resume.workTrip == true){
                $("#workTrip").text("可以出差");
            }else {
                $("#workTrip").text("不会出差");
            }
            if(resume.workOvertime == true){
                $("#workOvertime").text("可以加班");
            }else {
                $("#workOvertime").text("不会加班");
            }
            $("#remark").text(resume.remark);
            break;
        }
    }
});
// 查看图片
function findPicture() {
    if(resumeId==null){
        $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
        $("#toastMsgDiv").css({"display":"block"});
        $("#toastMsg").html("请先选中应聘人员").fadeIn(300).delay(2000).fadeOut(300);
        return;
    }
    var w = window.open();
    $.ajax({
        url:"/background/exam?uid="+resumeId,
        type:"GET",
        async:"false",
        success:function (data) {
            w.location = data.msg;
        }
    });
}

// 下载附件简历
function downloadAttachment() {
    if(resumeId==null){
        $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
        $("#toastMsgDiv").css({"display":"block"});
        $("#toastMsg").html("请先选中应聘人员").fadeIn(300).delay(2000).fadeOut(300);
        return;
    }
    window.location.href="/background/downloadAttachment?uid="+resumeId;
}
// 提交初试
$("#btn_submit").click(function () {
    if(resumeId == null){
        $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
        $("#toastMsgDiv").css({"display":"block"});
        $("#toastMsg").html("请先选中应聘人员").fadeIn(300).delay(2000).fadeOut(300);
        return;
    }
    if($("#grade").val().length<=0){
        $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
        $("#toastMsgDiv").css({"display":"block"});
        $("#toastMsg").html("请填写初试成绩").fadeIn(300).delay(2000).fadeOut(300);
        return;
    }
    if($("#pass").val()==0){
        $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
        $("#toastMsgDiv").css({"display":"block"});
        $("#toastMsg").html("请选择是否通过初试").fadeIn(300).delay(2000).fadeOut(300);
        return;
    }
    if($("#suggestion").val().length<=0){
        $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
        $("#toastMsgDiv").css({"display":"block"});
        $("#toastMsg").html("请填写初试评语").fadeIn(300).delay(2000).fadeOut(300);
        return;
    }
    // 保存初试结果
    $.ajax({
        url:"/background/savePreliminary",
        type:"POST",
        async:"false",
        data:$("#preliminaryForm").serialize(),
        success:function (data) {
            $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
            $("#toastMsgDiv").css({"display":"block"});
            $("#toastMsg").html(data.msg).fadeIn(300).delay(2000).fadeOut(300);
        }
    });
    $('#preliminaryForm')[0].reset();
    resumeId = null;
});
// 重置初试
$("#btn_reset").click(function () {
    $('#preliminaryForm')[0].reset();
})