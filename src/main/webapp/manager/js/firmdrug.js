
//公司名 公司id
var firmname = "";
var id = "";
/*function exportExcel()
{
	window.location.replace(ip+"export/firm?firmId="+id+"&drugName="+$("#name_like").val());
}*/
function queryFirmDrugPage()
{
	$('#firmDrugPage').bootstrapTable({
		method: 'get',
		contentType: "application/x-www-form-urlencoded",
		url: ip + "/drug/firm/page", 
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
				name_like: $("#name_like").val(),
				id:id,         
			}; 
			return temp;  
		},
		columns: [{
			title: 'ID',
			field: 'id',
			align:'center',
			visible: false
		}, {
			title: '',
			field: 'remarks_time',
			sortable: false,
			align:'center',
			formatter:function(value,row,index){
				if(value=='Y')
				{
					return[
					   "<img src='images/remarks.png' class='height_img'>"
					  ].join("");
				}else{
					return[""].join("");
				}
				
			},
		}, {
			title: '登记证号',
			field: 'pid',
			sortable: true,
			align:'center'
		},{
			title: '品名',
			field: 'drug_name',
			align:'center'
		},{
			title: '类型',
			field: 'type',
			align:'center'
		},{
			title: '剂型',
			field: 'form',
			align:'center',
		},{
			title: '含量',
			field: 'content',
			align:'center',
		},{
			title: '到期时间',
			field: 'expiry_time',
			align:'center',
			sortable: true,
		},{
			title: '添加时间',
			field: 'addtime',
			align:'center',
			sortable: true,
		},{
			title: '登记证',
			field: 'istrue',
			align:'center',
			cellStyle:function(value,row,index){
			　　　if (value=="有效"){
			 　		return {css:{"color":"green"}}
			　　　}else if(value=="过期"){
			 		return {css:{"color":"red"}}
			　　　}else{
					return {css:{"color":"black"}}
			}
			 
			}
		},{
			title: '备注',
			field: 'remarks',
			align:'center',
		},{
			field: 'operate',
	    	title: '操作',
	    	align: 'center',
	    	formatter: operateFrimDrug,
		}]
	})
}
//刷新table
function refreshFirmDrugTable()
{
	$("#firmDrugPage").bootstrapTable('refresh');
}

//
function operateFrimDrug(value, row, index)
{
	if(getCookie("role")=="1"){
		return ["<a href=\"javascript:void(0)\" class=\"ch_update\" onclick=\"updateFirmDrug("+row.id+")\">修改</a>"+
	        "<a href=\"javascript:void(0)\" class=\"ch_del\" onclick=\"delDrug("+row.id+")\">删除</a>"].join(""); 
	}else{
		return "";
	}
}
//删除
function delDrug(id)
{
	layer.confirm("确认删除该产品?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
		url:ip+"drug/del?id="+id,
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
				alert("删除产品成功！");
				refreshFirmDrugTable();
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="400"){
				alert("删除产品失败！");
				return;
			}
		}
	});
	})
}
//修改产品
function updateFirmDrug(id)
{
	queryDrugById(id)
	$('#updateFirmDrugModal').modal('show');
	$('#updatebtn').unbind('click');
	$("#updatebtn").click(function(){
		updateFirmDrugSub(id);
	})
}
//根据产品id
function queryDrugById(id)
{
	$.ajax({
		url:ip+"drug/firm/id?id="+id,
		async:false,
		contentType:"application/json;charset=utf-8",
		type:"GET",
		dataType:"json",
		success:function(obj)
		{
			$("#updateDrugName").val(obj.drug_name);
			$("#updatePid").val(obj.pid);
			$("#updateType").val(obj.type);
			$("#updateForm").val(obj.form);
			$("#updateContent").val(obj.content);
			$("#updateExpiryTime").val(obj.expiry_time);
			$("#updateRemarks").val(obj.remarks);
		},
		complete: function(XMLHttpRequest, textStatus) {
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			
		}
	});
}
function updateFirmDrugSub(id)
{
	var drugName =$("#updateDrugName").val();
	var pid = $("#updatePid").val();
	var type = $("#updateType").val();
	var form =$("#updateForm").val();
	var content = $("#updateContent").val();
	var expiryTime = $("#updateExpiryTime").val();
	var remarks = $("#updateRemarks").val();
	if(drugName==""||drugName==null)
	{
		layer.tips('请填写产品名！', '#updateDrugName',{tips: [2, '#ff0000']});
		$("#updateDrugName").focus();return;
	}
	if(type==""||type==null)
	{
		layer.tips('请填写类别！', '#updateType',{tips: [2, '#ff0000']});
		$("#updateType").focus();return;
	}
	$.ajax({
		url:ip+"drug/firm/updateDrug",
		async:false,
		data:JSON.stringify({"drug_name":drugName,"pid":pid,"type":type,"form":form,"content":content,"expiry_time":expiryTime,"firm_id":id,"id":id,"remarks":remarks}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#updateFirmDrugModal').modal('hide');
				$('#firmDrugPage').bootstrapTable("refresh");
				alert("修改产品成功！");
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="402")
			{
				layer.tips('登记证号不能重复', '#updatePid',{
					  tips: [2, '#ff0000']});
				$("#updatePid").focus();
				return;
			}else if(XMLHttpRequest.status=="400"){
				alert("修改产品失败！");
			}else if(XMLHttpRequest.status=="403"){
				layer.tips('登记证到期日有问题', '#updateExpiryTime',{
					  tips: [2, '#ff0000']});
				$("#updateExpiryTime").focus();
				return;
			}
		}
	});
}
//新增
function addFirmDrug()
{
	$("#addDrugName").val("");
	$("#addPid").val("");
	$("#addType").val("");
	$("#addForm").val("");
	$("#addContent").val("");
	$("#addExpiryTime").val("");
	$("#addRemarks").val("");
	$('#addFirmDrugModal').modal('show');
	$('#addbtn').unbind('click');
	$("#addbtn").click(function(){
		addFirmDrugSub();
	})	
}

//确认添加
function addFirmDrugSub()
{
	var drugName =$("#addDrugName").val();
	var pid = $("#addPid").val();
	var type = $("#addType").val();
	var form =$("#addForm").val();
	var content = $("#addContent").val();
	var expiryTime = $("#addExpiryTime").val();
	var remarks = $("#addRemarks").val();
	if(drugName==""||drugName==null)
	{
		layer.tips('请填写产品名！', '#addDrugName',{tips: [2, '#ff0000']});
		$("#addDrugName").focus();return;
	}
	if(type==""||type==null)
	{
		layer.tips('请填写类别！', '#addType',{tips: [2, '#ff0000']});
		$("#addType").focus();return;
	}
	$.ajax({
		url:ip+"drug/firm/addDrug",
		async:false,
		data:JSON.stringify({"drug_name":drugName,"pid":pid,"type":type,"form":form,"content":content,"expiry_time":expiryTime,"firm_id":id,"remarks":remarks}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#addFirmDrugModal').modal('hide');
				$('#firmDrugPage').bootstrapTable("refresh");
				alert("添加产品成功！");
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="402")
			{
				layer.tips('登记证号不能重复', '#addPid',{
					  tips: [2, '#ff0000']});
				$("#addPid").focus();
				return;
			}else if(XMLHttpRequest.status=="400"){
				alert("添加产品失败！");
			}
		}
	});
}
