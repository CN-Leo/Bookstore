function queryUserPage() {
	$('#userPage').bootstrapTable({
		method: 'get',
		contentType: "application/x-www-form-urlencoded",
		url: ip + "user/page", 
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
				search_like: $("#search_like").val(),
				         
			}; 
			return temp;  
		},
		columns: [{
			title: 'ID',
			field: 'id',
			align:'center',
			visible: false
		}, {
			title: '姓名',
			field: 'name',
			sortable: true,
			align:'center'
		}, {
			title: '登录名',
			field: 'account',
			sortable: true,
			align:'center'
		},{
			title: '手机号',
			field: 'phone',
			align:'center'
		},{
			title: '角色',
			field: 'role',
			align:'center',
			visible: false
		},{
			title: '角色',
			field: 'role',
			align:'center',
			formatter:function(value,row,index){
				if(row.role=='1')
				{
					value="超级管理员"
				}else{
					value="普通用户"
				}
				return value;
			}
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
//
function operateUser(value, row, index)
{
	if(getCookie("role")=="1"){
		return ["<a href=\"javascript:void(0)\" class=\"ch_update\" onclick=\"updateUser("+row.id+",'"+row.name+"','"+row.account+"','"+row.phone+"')\">修改</a>"+
	        "<a href=\"javascript:void(0)\"  class=\"ch_del\" onclick=\"delUser("+row.id+","+row.role+")\">删除</a>"+
		 "<a href=\"javascript:void(0)\"  class=\"ch_res\" onclick=\"resUser("+row.id+","+row.role+")\">重置密码</a>"].join("");
	}else{
		return "";
	}
	
}
//删除
function delUser(id,role)
{
	if(role=="1")
	{
		alert("您不能删除超级管理员!");
		return;
	}
	layer.confirm("确认删除该用户?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
		url:ip+"user/del?id="+id,
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
				alert("删除用户成功！");
				refreshUserTable();
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="400"){
				alert("删除用户失败！");
				return;
			}
		}
	});
	})
	
}
function resUser(id,role)
{
	if(role=="1")
	{
		alert("您不能重置超级管理员!");
		return;
	}
	layer.confirm("确认重置该用户密码为123456?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
		url:ip+"user/reset?id="+id,
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
				alert("重置用户密码成功！");
				refreshUserTable();
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="400"){
				alert("重置用户密码失败！");
				return;
			}
		}
	});
	})
	
}
//刷新table
function refreshUserTable()
{
	$("#userPage").bootstrapTable('refresh');
}
function updateUser(id,name,account,phone){
	$("#updatename").val(name);
	$("#updateaccount").val(account);
	$("#updatephone").val(phone);
	$('#updateUserModal').modal('show');
	$('#updateUser').unbind('click');
	$("#updateUser").click(function(){
		updateUserSub(id);
	})
	
}
function updateUserSub(id)
{
	var name =$("#updatename").val();
	var account = $("#updateaccount").val();
	var phone = $("#updatephone").val();
	if(name==""||name==null)
	{
		layer.tips('请填写姓名！', '#updatename',{tips: [2, '#ff0000']});
		$("#updatename").focus();
		return;
	}
	if(account==""||account==null)
	{
		layer.tips('请填写账号！', '#updateaccount',{tips: [2, '#ff0000']});
		$("#updateaccount").focus();
		return;
	}
	$.ajax({
		url:ip+"user/updateUser",
		async:false,
		data:JSON.stringify({"name":name,"account":account,"id":id,"phone":phone}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#updateUserModal').modal('hide');
				$('#userPage').bootstrapTable("refresh");
				alert("修改用户成功！");
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="401")
			{
				layer.tips('账号名已存在', '#updateaccount',{
					  tips: [2, '#ff0000']});
				$("#updateaccount").focus();
				return;
			}else if(XMLHttpRequest.status=="400"){
				alert("修改用户失败！");
			}
		}
	});
}
function addUser()
{
	if(getCookie("role")==null)
	{
		window.location.replace("login.html");
	}
	if(getCookie("role")=="0")
	{
		alert("您不是超级管理员,不能新增用户!");
		return;
	}
	$("#addname").val("");
	$("#addaccount").val("");
	$("#addphone").val("");
	$('#addUserModal').modal('show');
	$('#addUser').unbind('click');
	$("#addUser").click(function(){
		addUserSub();
	})
}

function addUserSub(){
	var name =$("#addname").val();
	var account = $("#addaccount").val();
	var phone = $("#addphone").val();
	var role = $("#addrole").val();
	if(name==""||name==null)
	{
		layer.tips('请填写姓名！', '#addname',{tips: [2, '#ff0000']});
		$("#addname").focus();
		return;
	}
	if(account==""||account==null)
	{
		layer.tips('请填写账号！', '#addaccount',{tips: [2, '#ff0000']});
		$("#addaccount").focus();
		return;
	}
	if(role==""||role==null)
	{
		layer.tips('请填写角色！', '#addrole',{
			  tips: [2, '#ff0000']});
		$("#addrole").focus();
		return;
	}
	$.ajax({
		url:ip+"user/addUser",
		async:false,
		data:JSON.stringify({"name":name,"account":account,"phone":phone,"role":role}),
		contentType:"application/json;charset=utf-8",
		type:"POST",
		dataType:"json",
		success:function()
		{
			
		},
		complete: function(XMLHttpRequest, textStatus) {
			if(XMLHttpRequest.status == "200") {
				$('#addUserModal').modal('hide');
				$('#userPage').bootstrapTable("refresh");
				alert("添加用户成功！");
				return;
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status=="401")
			{
				layer.tips('账号名不能重复', '#addaccount',{
					  tips: [2, '#ff0000']});
				$("#addaccount").focus();
				return;
			}else if(XMLHttpRequest.status=="400"){
				alert("添加用户失败！");
			}
		}
	});
}