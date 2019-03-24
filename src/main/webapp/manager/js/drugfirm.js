
//产品 产品id
var drugName = "";
var id = "";

/*function exportExcel()
{
	window.location.replace(ip+"export/dname?nid="+id+"&pid="+$("#pid_like").val());
}*/
function queryNameDrugPage()
{
	$('#drugPage').bootstrapTable({
		method: 'get',
		contentType: "application/x-www-form-urlencoded",
		url: ip + "/drug/dname/page", 
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
				query_like: $("#query_like").val(),
				province_like: $("#province_like").val(),
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
			align:'left',
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
			title: '公司',
			field: 'firmname',
			align:'center',
		},{
			title: '省份',
			field: 'province',
			align:'center',
		},{
			title: '地区',
			field: 'area',
			align:'center',
		},{
			title: '联系人',
			field: 'linkman',
			align:'center',
		},{
			title: '电话',
			field: 'phone',
			align:'center',
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
function refreshDrugTable()
{
	$("#drugPage").bootstrapTable('refresh');
}

//
function operateFrimDrug(value, row, index)
{
	if(getCookie("role")=="1"){
		return ["<a href=\"javascript:void(0)\" class=\"ch_update\" onclick=\"updateNameDrug("+row.id+")\">修改</a>"+
	        "<a href=\"javascript:void(0)\"class=\"ch_del\" onclick=\"delDrug("+row.id+")\">删除</a>"].join(""); 
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
				refreshDrugTable();
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
function updateNameDrug(id)
{
	queryDrugById(id)
	$('#updateDrugModal').modal('show');
	$('#updatebtn').unbind('click');
	$("#updatebtn").click(function(){
		updateNameDrugSub(id);
	})
}
//根据产品id
function queryDrugById(id)
{
	$.ajax({
		url:ip+"/drug/find/id?id="+id,
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
			$("#updateFirmname").val(obj.firmname);
			$("#updatePhone").val(obj.phone);
			$("#updateProvince").val(obj.province);
			$("#updateRemarks").val(obj.remarks);
			$("#updateLinkman").val(obj.linkman);
			$("#updateArea").val(obj.area);
		},
		complete: function(XMLHttpRequest, textStatus) {
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			
		}
	});
}
function updateNameDrugSub(id)
{
	var drugName =$("#updateDrugName").val();
	var pid = $("#updatePid").val();
	var type = $("#updateType").val();
	var form =$("#updateForm").val();
	var content = $("#updateContent").val();
	var expiryTime = $("#updateExpiryTime").val();
	var firmname = $("#updateFirmname").val();
	var phone = $("#updatePhone").val();
	var province = $("#updateProvince").val();
	var area = $("#updateArea").val();
	var linkman = $("#updateLinkman").val();
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
	if(firmname==""||firmname==null)
	{
		layer.tips('请填写公司名！', '#updateFirmname',{tips: [2, '#ff0000']});
		$("#updateFirmname").focus();return;
	}
	if(province==""||province==null)
	{
		layer.tips('请填写公司省份！', '#updateProvince',{tips: [2, '#ff0000']});
		$("#updateProvince").focus();return;
	}
	$.ajax({
		url:ip+"drug/dname/updateDrug",
		async:false,
		data:JSON.stringify({"drug_name":drugName,"pid":pid,"type":type,"form":form,"content":content,"expiry_time":expiryTime,"nid":id,"firmname":firmname,"phone":phone,"province":province,"id":id,"area":area,"linkman":linkman,"remarks":remarks}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#updateDrugModal').modal('hide');
				$('#drugPage').bootstrapTable("refresh");
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
function addDrug()
{
	$("#addDrugName").val(drugName);
	$("#addPid").val("");
	$("#addType").val("");
	$("#addForm").val("");
	$("#addContent").val("");
	$("#addExpiryTime").val("");
	$("#addFirmname").val("");
	$("#addPhone").val("");
	$("#addProvince").val("");
	$("#addLinkman").val("");
	$("#addArea").val("");
	$("#addRemarks").val("");
	$('#addDrugModal').modal('show');
	$('#addbtn').unbind('click');
	$("#addbtn").click(function(){
		addDrugSub();
	})	
}

//确认添加
function addDrugSub()
{
	var pid = $("#addPid").val();
	var type = $("#addType").val();
	var form =$("#addForm").val();
	var content = $("#addContent").val();
	var expiryTime = $("#addExpiryTime").val();
	var firmname = $("#addFirmname").val();
	var phone = $("#addPhone").val();
	var province = $("#addProvince").val();
	var area = $("#addArea").val();
	var linkman = $("#addLinkman").val();
	var remarks = $("#addRemarks").val();
	$('#addDrugModal').modal('show');
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
	if(firmname==""||firmname==null)
	{
		layer.tips('请填写公司名！', '#addFirmname',{tips: [2, '#ff0000']});
		$("#addFirmname").focus();return;
	}
	if(province==""||province==null)
	{
		layer.tips('请填写公司省份！', '#addProvince',{tips: [2, '#ff0000']});
		$("#addProvince").focus();return;
	}
	$.ajax({
		url:ip+"/drug/dname/addDrug",
		async:false,
		data:JSON.stringify({"drug_name":drugName,"pid":pid,"type":type,"form":form,"content":content,"expiry_time":expiryTime,"nid":id,"firmname":firmname,"phone":phone,"province":province,"area":area,"linkman":linkman,"remarks":remarks}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#addDrugModal').modal('hide');
				$('#drugPage').bootstrapTable("refresh");
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
