function queryFirmPage() {
	$('#firmPage').bootstrapTable({
		method: 'get',
		contentType: "application/x-www-form-urlencoded",
		url: ip + "firm/page", 
		sortName: "addtime",
	    sortOrder:"desc",
	    sortable: true,
		striped: true, 
		pageNumber: 1, 
		pagination: true, 
		showRefresh: false, 
		sidePagination: 'server', //server:服务器端分页|client：前端分页
		pageSize: 10, //单页记录数
		pageList: [10, 20, 50], //可选择单页记录数
		queryParamsType : "",
		queryParams: function(params) {    
			var temp = {      
				pageNumber: params.pageNumber, 
				pageSize: params.pageSize, 
				sortOrder: params.sortOrder,
	            sortName: params.sortName,
				query_like: $("#area_like").val(),
				province_like :$("#province_like").val()       
			}; 
			return temp;  
		},
		columns: [{
			title: 'ID',
			field: 'id',
			align:'center',
			visible: false
		}, {
			title: '公司名',
			field: 'firmname',
			sortable: true,
			align:'center',
			formatter:function(value,row,index){
				value="<a style='color:blue' href='firmdrug.html?id="+row.id+"&firmname="+encodeURI(row.firmname)+"'>"+row.firmname+"</a>";
				return value;
			}
		},{
			title:'省份',
			field: 'province',
			align:'center'
		},{
			title: '地区',
			field: 'area',
			align:'center'
		},{
			title: '联系人',
			field: 'linkman',
			align:'center'
		},{
			title: '手机号',
			field: 'phone',
			align:'center'
		},{
			title: '产品数量',
			field: 'amount',
			align:'center',
		},{
			title: '添加时间',
			field: 'addtime',
			align:'center',
			sortable: true,
		},{
			field: 'operate',
	    	title: '操作',
	    	align: 'center',
	    	formatter: operateUser,
		}]
	})
}
//刷新table
function refreshFirmTable()
{
	$("#firmPage").bootstrapTable('refresh');
}

//
function operateUser(value, row, index)
{
	if(getCookie("role")=="1"){
		return ["<a href=\"javascript:void(0)\"class=\"ch_update\" onclick=\"updateFirm("+row.id+")\">修改</a>"+
	        "<a href=\"javascript:void(0)\"  class=\"ch_del\" onclick=\"delFirm("+row.id+")\">删除</a>"].join(""); 
	}else{
		return "";
	}
	
}
//新增公司
function addFirm()
{
	$("#addfirmname").val("");
	$("#addprovince").val("");
	$("#addarea").val("");
	$("#addlinkman").val("");
	$("#addphone").val("");
	$('#addFirmModal').modal('show');
	$('#addFirm').unbind('click');
	$("#addFirm").click(function(){
		addFirmSub();
	})
}
function addFirmSub()
{
	var firmname =$("#addfirmname").val();
	var province = $("#addprovince").val();
	var phone = $("#addphone").val();
	var area = $("#addarea").val();
	var linkman = $("#addlinkman").val();
	if(firmname==""||firmname==null)
	{
		layer.tips('请填写公司名！', '#addfirmname',{tips: [2, '#ff0000']});
		$("#addfirmname").focus();
		return;
	}
	if(province==""||province==null)
	{
		layer.tips('请填写省份！', '#addprovince',{tips: [2, '#ff0000']});
		$("#addaccount").focus();
		return;
	}
	$.ajax({
		url:ip+"firm/addFirm",
		async:false,
		data:JSON.stringify({"firmname":firmname,"province":province,"phone":phone,"area":area,"linkman":linkman}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#addFirmModal').modal('hide');
				$('#firmPage').bootstrapTable("refresh");
				alert("添加公司成功！");
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="401")
			{
				layer.tips('公司名不能重复', '#addfirmname',{
					  tips: [2, '#ff0000']});
				$("#addfirmname").focus();
				return;
			}else if(XMLHttpRequest.status=="400"){
				alert("添加公司失败！");
			}
		}
	});
}


//删除
function delFirm(id)
{
	layer.confirm("确认删除该公司,以及该公司下面的所有药品?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
		url:ip+"firm/del?id="+id,
		async:false,
		contentType:"application/json;charset=utf-8",
		type:"get",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				layer.closeAll();
				alert("删除公司成功！");
				refreshFirmTable();
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="400"){
				alert("删除公司失败！");
				return;
			}
		}
	});
	})
}

function updateFirm(id){
	initFirmById(id);
	$('#updateFirmModal').modal('show');
	$('#updateFirm').unbind('click');
	$("#updateFirm").click(function(){
		updateFirmSub(id);
	})
}
function updateFirmSub(id)
{
	var firmname =$("#updatefirmname").val();
	var province = $("#updateprovince").val();
	var phone = $("#updatephone").val();
	var area = $("#updatearea").val();
	var linkman = $("#updatelinkman").val();
	if(firmname==""||firmname==null)
	{
		layer.tips('请填写公司名！', '#updatefirmname',{tips: [2, '#ff0000']});
		$("#updatefirmname").focus();
		return;
	}
	if(province==""||province==null)
	{
		layer.tips('请填写省份！', '#updateprovince',{tips: [2, '#ff0000']});
		$("#updateprovince").focus();
		return;
	}
	$.ajax({
		url:ip+"firm/updateFirm",
		async:false,
		data:JSON.stringify({"firmname":firmname,"province":province,"phone":phone,"id":id,"area":area,"linkman":linkman}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#updateFirmModal').modal('hide');
				$('#firmPage').bootstrapTable("refresh");
				alert("修改公司成功！");
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="401")
			{
				layer.tips('公司名不能重复', '#updatefirmname',{
					  tips: [2, '#ff0000']});
				$("#updatefirmname").focus();
				return;
			}else if(XMLHttpRequest.status=="400"){
				alert("修改公司失败！");
			}
		}
	});
}
function initFirmById(id)
{
	$.ajax({
		url:ip+"firm/find/id?id="+id,
		async:false,
		contentType:"application/json;charset=utf-8",
		type:"GET",
		dataType:"json",
		success:function(obj)
		{
			$("#updatefirmname").val(obj.firmname);
			$("#updateprovince").val(obj.province);
			$("#updatearea").val(obj.area);
			$("#updatelinkman").val(obj.linkman);
			$("#updatephone").val(obj.phone);
		},
		complete: function(XMLHttpRequest, textStatus) {
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
		}
	});
}
