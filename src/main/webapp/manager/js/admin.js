function queryAdminPage() {
	$('#adminPage').bootstrapTable({
		method: 'get',
		contentType: "application/x-www-form-urlencoded",
		url: "/manager/admin/page", 
		sortName: "f_create_time",
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
			field: 'fAdminId',
			align:'center',
			visible: false
		}, {
			title: '账号',
			field: 'fAdminCode',
			sortable: false,
			align:'center'
		},{
			title: '角色',
			field: 'fAdminRole',
			align:'center',
			visible: false
		},{
			title: '角色',
			field: 'fAdminRole',
			align:'center',
			formatter:function(value,row,index){
				if(row.fAdminRole=='1')
				{
					value="超级管理员"
				}else{
					value="管理员"
				}
				return value;
			}
		},{
			title: '状态',
			field: 'fAdminState',
			align:'center',
			formatter:function(value,row,index){
				if(row.fAdminState=='1')
				{
					value="启用"
				}else{
					value="禁用"
				}
				return value;
			}
		},{
			title: '创建时间',
			field: 'fCreateTime',
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
			title: '最近登录时间',
			field: 'fLastLoginTime',
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
			field: 'operate',
	    	title: '操作',
	    	align: 'center',
	    	formatter: operateAdmin,
		}]
	})
}
//
function operateAdmin(value, row, index)
{
	if(getCookie("adminRole")=="1"&&row.fAdminState=="1"){
		return ["<a href=\"javascript:void(0)\" class=\"a_green\" onclick=\"updateUser('"+row.fAdminId+"','"+row.fAdminCode+"')\">修改</a>"+
	        "<a href=\"javascript:void(0)\"  class=\"a_red\" onclick=\"delAdmin('"+row.fAdminId+"',"+row.fAdminRole+")\">删除</a>"+
	        "<a href=\"javascript:void(0)\"  class=\"a_red\" onclick=\"closeAdmin('"+row.fAdminId+"',"+row.fAdminRole+")\">禁用</a>"+
		 "<a href=\"javascript:void(0)\"  class=\"a_blue\" onclick=\"resAdmin('"+row.fAdminId+"',"+row.fAdminRole+")\">重置密码</a>"].join("");
	}else if(getCookie("adminRole")=="1"&&row.fAdminState=="0"){
		return ["<a href=\"javascript:void(0)\" class=\"a_green\" onclick=\"updateUser('"+row.fAdminId+"','"+row.fAdminCode+"')\">修改</a>"+
		        "<a href=\"javascript:void(0)\"  class=\"a_red\" onclick=\"delAdmin('"+row.fAdminId+"',"+row.fAdminRole+")\">删除</a>"+
		        "<a href=\"javascript:void(0)\"  class=\"a_blue\" onclick=\"openAdmin('"+row.fAdminId+"',"+row.fAdminRole+")\">启用</a>"+
			 "<a href=\"javascript:void(0)\"  class=\"a_blue\" onclick=\"resAdmin('"+row.fAdminId+"',"+row.fAdminRole+")\">重置密码</a>"].join("");
	}else{
		return "";
	}
}
//删除
function delAdmin(fAdminId,fAdminRole)
{
	if(fAdminRole=="1")
	{
		alert("您不能删除超级管理员!");
		return;
	}
	layer.confirm("确认删除该管理员?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
		url:"/manager/admin/del?fAdminId="+fAdminId,
		async:false,
		type:"get",
		dataType:"text",
		success:function(result)
		{
			var obj = JSON.parse(result);
			layer.closeAll();
			if(obj.resultCode=="200")
			{
				alert("删除用户成功！");
				refreshAdminTable();
			}else{
				alert(obj.resultMsg);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
		}
		});
	})
}
//重置密码
function resAdmin(fAdminId,fAdminRole)
{
	if(fAdminRole=="1")
	{
		alert("您不能重置超级管理员!");
		return;
	}
	layer.confirm("确认重置该管理员密码为123456?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
		url:"/manager/admin/reset?fAdminId="+fAdminId,
		async:false,
		type:"get",
		dataType:"text",
		success:function(result)
		{
			var obj = JSON.parse(result); 
			if(obj.resultCode=="200")
			{
				layer.closeAll();
				alert("重置密码成功！");
				refreshAdminTable();
			}else{
				alert(obj.resultMsg);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
		}
	});
	})
	
}
//启用
function openAdmin(fAdminId,fAdminRole)
{
	layer.confirm("确认启用该管理员?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
			url:"/manager/admin/actAdmin?fAdminId="+fAdminId+"&fAdminState="+1,
			async:false,
			dataType:"text",
			type:"get",
			success:function(result)
			{
				var obj = JSON.parse(result); 
				if(obj.resultCode=="200")
				{
					layer.closeAll();
					alert("操作成功！");
					refreshAdminTable();
				}else{
					alert(obj.resultMsg);
				}
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
			}
		});
	})
	
}
//禁用
function closeAdmin(fAdminId,fAdminRole)
{
	if(fAdminRole=="1")
	{
		alert("您不能禁用超级管理员!");
		return;
	}
	layer.confirm("确认禁用该管理员?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
			url:"/manager/admin/actAdmin?fAdminId="+fAdminId+"&fAdminState="+0,
			async:false,
			dataType:"text",
			type:"get",
			success:function(result)
			{
				var obj = JSON.parse(result); 
				if(obj.resultCode=="200")
				{
					layer.closeAll();
					alert("操作成功！");
					refreshAdminTable();
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
function refreshAdminTable()
{
	$("#adminPage").bootstrapTable('refresh');
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