/**
 * Created by Administrator on 2017/6/20.
 */
$(function () {
    // 初始化职位
    initJob();
    // 初始化学历
    initEdu();
    // 初始化Bootstrap-Table
    $('#tables').bootstrapTable({
        method: 'POST',
        contentType: "application/x-www-form-urlencoded",//必须要有！！！！
        url:"/rest/findAllResumes",//要请求数据的文件路径
        toolbar: '#toolbar',//指定工具栏
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination:true,//是否分页
        queryParams:queryParams,//请求服务器时所传的参数
        sidePagination:'server',//指定服务器端分页
        pageSize:10,//单页记录数
        pageList:[5,10,20,30],//分页步进值
        showRefresh:true,//刷新按钮
        showColumns:true,
        clickToSelect: true,//是否启用点击选中行
        toolbarAlign:'left',//工具栏对齐方式
        buttonsAlign:'right',//按钮对齐方式
        showToggle:true, //是否显示详细视图和列表视图的切换按钮
        minimumCountColumns: 2, //最少允许的列数
        toolbar:'#toolbar',//指定工作栏
        columns:[
            {
                title:'全选',
                field:'select',
                //复选框
                checkbox:true,
                width:25,
                align:'center',
                valign:'middle'
            },
            {
                title:'主键',
                field:'id',
                sortable:true
            },
            {
                title:'职位',
                field:'job',
            },
            {
                title:'姓名',
                field:'name',
            },
            {
                title:'身份证号',
                field:'card',
            },
            {
                title:'户籍',
                field:'register'
            },
            {
                title:'居住地',
                field:'address'
            },
            {
                title:'手机',
                field:'phone'
            },
            {
                title:'邮箱',
                field:'email'
            },
            {
                title:'QQ',
                field:'qq'
            },
            {
                title:'大学',
                field:'university'
            },
            {
                title:'学历',
                field:'education'
            },
            {
                title:'专业',
                field:'major'
            },
            {
                title:'工作年限',
                field:'workYear',
                sortable:true,
                formatter : function (value, row, index) {
                    return value+"年";
                }
            },
            {
                title:'最低薪水',
                field:'workMinSalary',
                sortable:true
            },
            {
                title:'最高薪水',
                field:'workMaxSalary',
                sortable:true
            },
            {
                title:'入职时间',
                field:'workGoDate'
            },
            {
                title:'就职状态',
                field:'workStatus',
                formatter : function (value, row, index) {
                    if(row['workStatus']==true){
                        return "在职";
                    }else{
                        return "离职";
                    }
                }
            },
            {
                title:'出差',
                field:'workTrip',
                formatter : function (value, row, index) {
                    if(row['workTrip']==true){
                        return "能";
                    }else{
                        return "不能";
                    }
                }
            },
            {
                title:'加班',
                field:'workOvertime',
                formatter : function (value, row, index) {
                    if(row['workOvertime']==true){
                        return "能";
                    }else{
                        return "不能";
                    }
                }
            },
            {
                title:'面试日期',
                field:'createDate',
                sortable:true
            },
            {
                title:'备注',
                field:'remark'
            }
        ],
        locale:'zh-CN',//中文支持
        responseHandler:function(res){
            //在ajax获取到数据，渲染表格之前，修改数据源
            return res;
        }
    });
    // 初始隐藏不显示的列
    $('#tables').bootstrapTable('hideColumn', 'card');
    $('#tables').bootstrapTable('hideColumn', 'register');
    $('#tables').bootstrapTable('hideColumn', 'address');
    $('#tables').bootstrapTable('hideColumn', 'phone');
    $('#tables').bootstrapTable('hideColumn', 'email');
    $('#tables').bootstrapTable('hideColumn', 'qq');
    $('#tables').bootstrapTable('hideColumn', 'workGoDate');
    $('#tables').bootstrapTable('hideColumn', 'remark');
    $('#tables').bootstrapTable('hideColumn', 'workStatus');
    $('#tables').bootstrapTable('hideColumn', 'education');
    $('#tables').bootstrapTable('hideColumn', 'major');
    $('#tables').bootstrapTable('hideColumn', 'university');
    // 请求服务数据时所传参数
    function queryParams(params){
        return{
            // 每页多少条数据
            pageSize: params.limit,
            // 请求第几页
            pageIndex:params.offset,
            // 排序
            sortOrder: params.order,
            // 排序字段
            sortName:params.sort,
            // 状态
            status:$("#status").val(),
            // 职位
            job:$("#job").val(),
            // 学历
            education:$("#education").val(),
            // 姓名
            name:$("#name").val(),
            // 身份证号
            card:$("#rcard").val(),
            // 日期
            filterDate:formatDate($("#datepicker").datepicker("getDate"))
        }
    }
});
// 查询
$("#btn_query").click(function () {
    $('#tables').bootstrapTable('refresh');
});
// 日期格式化
function formatDate(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    return y + '-' + m + '-' + d;
};
// 清空查询条件
$("#btn_reset").click(function () {
    $('#formSearch')[0].reset();
})
// 删除
$("#btn_delete").click(function () {
    var rows = $('#tables').bootstrapTable('getSelections');
    if(rows.length==1){
        var obj = rows[0];
        $.ajax({
            type:"GET",
            url:"/rest/deleteResumeById?id="+obj.id,
            success:function (data) {
                <!--弹出提示框-->
                $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
                $("#toastMsgDiv").css({"display":"block"});
                $("#toastMsg").html(data.msg).fadeIn(300).delay(2000).fadeOut(300);
                $('#tables').bootstrapTable('refresh');
            }
        });
    }else {
        <!--弹出提示框-->
        $("#toastMsgDiv").css({"top":$('body').scrollTop()+200});
        $("#toastMsgDiv").css({"display":"block"});
        $("#toastMsg").html('请选中一行数据').fadeIn(300).delay(2000).fadeOut(300);
    }
});
// 列相关事件
window.operateEvents = {
    // 上传笔试图片
    'click .detail': function (e, value, row, index) {
        $("#modal").modal('toggle');
    }
}
// 初始化日期选择器
$("#datepicker").datepicker({
    language: "zh-CN",
    autoclose: true,//选中之后自动隐藏日期选择框
    format: "yyyy-mm-dd"//日期格式
});