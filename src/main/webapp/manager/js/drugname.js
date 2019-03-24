function queryDrugNamePage() {
	$('#drugNamePage').bootstrapTable({
		method: 'get',
		contentType: "application/x-www-form-urlencoded",
		url: ip + "dname/page", 
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
				         
			}; 
			return temp;  
		},
		columns: [{
			title: 'ID',
			field: 'id',
			align:'center',
			visible: false
		}, {
			title: '产品名',
			field: 'drug_name',
			sortable: true,
			align:'center',
			formatter:function(value,row,index){
				value="<a style='color:blue' href='drugfirm.html?id="+row.id+"&drug_name="+encodeURI(row.drug_name)+"'>"+row.drug_name+"</a>";
				return value;
			}
		},{
			title: '厂家数量',
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
function refreshDrugNameTable()
{
	$("#drugNamePage").bootstrapTable('refresh');
}

//
function operateUser(value, row, index)
{
	if(getCookie("role")=="1"){
		return ["<a href=\"javascript:void(0)\" class=\"ch_update\" onclick=\"updateDrugName("+row.id+",'"+row.drug_name+"')\">修改</a>"+
	        "<a href=\"javascript:void(0)\"  class=\"ch_del\" onclick=\"delDrugName("+row.id+")\">删除</a>"].join(""); 
	}else{
		return "";
	}
	
}
//新增公司
function addDrugName()
{
	$("#addDrugName").val("");
	$('#addDrugNameModal').modal('show');
	$('#addbtn').unbind('click');
	$("#addbtn").click(function(){
		addDrugNameSub();
	})
}
function addDrugNameSub()
{
	var drugName =$("#addDrugName").val();
	if(drugName==""||drugName==null)
	{
		layer.tips('请输入产品名！', '#addDrugName',{tips: [2, '#ff0000']});
		$("#addDrugName").focus();
		return;
	}
	$.ajax({
		url:ip+"dname/addDrugName",
		async:false,
		data:JSON.stringify({"drug_name":drugName}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#addDrugNameModal').modal('hide');
				$('#drugNamePage').bootstrapTable("refresh");
				alert("添加产品名成功！");
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="401")
			{
				layer.tips('产品名不能重复', '#addDrugName',{
					  tips: [2, '#ff0000']});
				$("#addDrugName").focus();
				return;
			}else if(XMLHttpRequest.status=="400"){
				alert("添加产品名失败！");
			}
		}
	});
}


//删除
function delDrugName(id)
{
	layer.confirm("确认删除该产品,以及该产品下面的所有药品?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
		url:ip+"dname/del?id="+id,
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
				alert("删除产品名成功！");
				refreshDrugNameTable();
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="400"){
				alert("删除产品名失败！");
				return;
			}
		}
	});
	})
}

function updateDrugName(id,drug_name){
	$("#updateDrugName").val(drug_name);
	$('#updateDrugNameModal').modal('show');
	$('#updatebtn').unbind('click');
	$("#updatebtn").click(function(){
		updateDrugNameSub(id);
	})
}
function updateDrugNameSub(id)
{
	var drugName =$("#updateDrugName").val();
	if(drugName==""||drugName==null)
	{
		layer.tips('请填写产品名！', '#updateDrugName',{tips: [2, '#ff0000']});
		$("#updateDrugName").focus();
		return;
	}
	$.ajax({
		url:ip+"dname/updateDrugName",
		async:false,
		data:JSON.stringify({"drug_name":drugName,"id":id}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#updateDrugNameModal').modal('hide');
				$('#drugNamePage').bootstrapTable("refresh");
				alert("修改产品名成功！");
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="401")
			{
				layer.tips('产品名已存在', '#updateDrugName',{
					  tips: [2, '#ff0000']});
				$("#updateDrugName").focus();
				return;
			}else if(XMLHttpRequest.status=="400"){
				alert("修改产品名失败！");
			}
		}
	});
}