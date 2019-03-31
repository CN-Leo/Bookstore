function queryDrugPage() {
	$('#drugPage').bootstrapTable({
		method: 'get',
		contentType: "application/x-www-form-urlencoded",
		url: "/manager/drug/page", 
		sortName: "f_oper_time",
	    sortOrder:"asc",
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
				search_like: $("#search_like").val(),
				         
			}; 
			return temp;  
		},
		columns: [{
			title: 'ID',
			field: 'fId',
			align:'center',
			visible: false
		}, {
			title: '药品编号',
			field: 'fDrugCode',
			sortable: false,
			align:'center'
		},{
			title: '名称',
			field: 'fDrugName',
			align:'center',
		},{
			title: '分类',
			field: 'fDrugType',
			align:'center',
			formatter:function(value,row,index){
				if(row.fDrugType=='1')
				{
					value="解热镇痛药"
				}
				else if(row.fDrugType=="2"){
					value="循环系统"
				}
				else if(row.fDrugType=="3"){
					value="妇科用药"
				}
				else if(row.fDrugType=="4"){
					value="皮肤科用"
				}
				else if(row.fDrugType=="5"){
					value="动物类"
				}
				else if(row.fDrugType=="6"){
					value="矿物类"
				}
				else if(row.fDrugType=="7"){
					value="I类器材"
				}
				else if(row.fDrugType=="8"){
					value="II类器材"
				}
				else if(row.fDrugType=="9"){
					value="辅助用品"
				}
				else if(row.fDrugType=="10"){
					value="避孕用品"
				}
				else if(row.fDrugType=="11"){
					value="改善促进"
				}
				else if(row.fDrugType=="12"){
					value="排毒养颜"
				}
				return value;
			}
		},{
			title: '价格（元）',
			field: 'fDrugPrice',
			align:'center',
			formatter:function(value,row,index){
				return changeMoney(row.fDrugPrice)
			}
		},{
			title: '处方药',
			field: 'fIsPrescription',
			align:'center',
			formatter:function(value,row,index){
				if(row.fIsPrescription=='1')
				{
					value="是"
				}else{
					value="否"
				}
				return value;
			}
		},{
			title: '成分',
			field: 'fDrugModel',
			align:'center',
			formatter:function(value,row,index){
				if(row.fDrugModel=='0')
				{
					value="中药"
				}else if(row.fDrugModel=='1'){
					value="西药"
				}else{
					value="复方药"
				}
				return value;
			}
		},{
			title: '创建时间',
			field: 'fOperTime',
			align:'center',
			sortable: false,
			formatter:function(value,row,index)
			{
				if(value)
				{
					return formatDate14(value)
				}else{
					return "--"
				}
				
			}
		},{
			title: '描述',
			field: 'fContent',
			align:'center',
		},{
			title: '库存',
			field: 'fStock',
			align:'center',
		},{
			field: 'operate',
	    	title: '操作',
	    	align: 'center',
	    	formatter: operateFunction,
		}]
	})
}
//
function operateFunction(value, row, index)
{
	if(getCookie("adminRole")=="1"){
		return ["<a class=\"a_green\" href=\"drugupdate.html?fId="+row.fId+"&fDrugName="+row.fDrugName+"\">修改</a>"+
	        "<a href=\"javascript:void(0)\"  class=\"a_red\" onclick=\"delFunction('"+row.fId+"')\">删除</a>"].join("");
	}else{
		return "";
	}
}
//删除
function delFunction(fId)
{
	layer.confirm("确认删除该药品?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
		url:"/manager/drug/del?fId="+fId,
		async:false,
		type:"get",
		dataType:"text",
		success:function(result)
		{
			var obj = JSON.parse(result);
			layer.closeAll();
			if(obj.resultCode=="200")
			{
				alert("删除成功！");
				refreshDrugTable();
			}else{
				alert(obj.resultMsg);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
		}
		});
	})
}
//刷新table
function refreshDrugTable()
{
	$("#drugPage").bootstrapTable('refresh');
}




function updateUser(fAdminId,fAdminCode){
	$("#updateCode").val(fAdminCode);
	$('#updateAdminModal').modal('show');
	$('#updateAdmin').unbind('click');
	$("#updateAdmin").click(function(){
		updateAdminSub(fAdminId);
	})
	
}
function updateAdminSub(fAdminId)
{
	var fAdminCode = $("#updateCode").val();
	if(fAdminCode==""||fAdminCode==null)
	{
		layer.tips('请填写用户名！', '#updateCode',{tips: [2, '#ff0000']});
		$("#updateCode").focus();
		return;
	}
	$.ajax({
		url:"/manager/admin/updateAdmin",
		async:false,
		data:{"fAdminId":fAdminId,"fAdminCode":fAdminCode},
		type:"POST",
		dataType:"text",
		success:function(result)
		{
			$('#updateAdminModal').modal('hide');
			var obj = JSON.parse(result); 
			if(obj.resultCode=="200")
			{
				alert("操作成功！");
				refreshAdminTable();
			}else{
				alert(obj.resultMsg);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
		}
	});
}
function addAdmin()
{
	if(getCookie("adminRole")==null)
	{
		window.location.replace("login.html");
	}
	if(getCookie("adminRole")=="0")
	{
		alert("您不是超级管理员,不能新增管理员!");
		return;
	}
	$("#addcode").val("");
	$('#addAdminModal').modal('show');
	$('#addAdmin').unbind('click');
	$("#addAdmin").click(function(){
		addAdminSub();
	})
}

function addAdminSub(){
	var fAdminCode =$("#addcode").val();
	var fAdminRole =$("#addrole").val();
	if(fAdminCode==""||fAdminCode==null)
	{
		layer.tips('请填写账号！', '#addcode',{tips: [2, '#ff0000']});
		$("#addcode").focus();
		return;
	}
	if(fAdminRole==""||fAdminRole==null)
	{
		layer.tips('请填写角色！', '#addrole',{
			  tips: [2, '#ff0000']});
		$("#addrole").focus();
		return;
	}
	$.ajax({
		url:"/manager/admin/register",
		async:false,
		data:{"fAdminCode":fAdminCode,"fAdminRole":fAdminRole},
		type:"POST",
		dataType : "text",
		success:function(result)
		{
			$('#addAdminModal').modal('hide');
			var obj = JSON.parse(result); 
			if(obj.resultCode=="200")
			{
				alert("新增管理员成功！");
				refreshAdminTable();
			}else{
				alert(obj.resultMsg);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			
		}
	});
}