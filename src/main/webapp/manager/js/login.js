//获取cookie值
function getCookie(name) 
{ 
	//document.cookie.setPath("/");
    var arr, reg = new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
	{
	      return unescape(arr[2]); 
	}
    else 
	{
        return null; 
	}
}


//设置cookie值
function setCookie(name,value) 
{ 
	//document.cookie.setPath("/");
    var hour = 168; 
    var exp = new Date(); 
    exp.setTime(exp.getTime() + hour*60*60*1000); 
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString()+";path=/"; 
}

function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return (r[2]); return null;
}

    

/*
 *管理员登录 
 */
function loginIn(){
	var code=$("input#code").val();
	var pwd=$("input#pwd").val();
	if(code==""){
		layer.tips('用户名不能为空',"#code",{
			tips:[2,'#ff0000']});
		$("input#code").focus();
		return;
	}
	if(pwd==""){
		layer.tips('密码不能为空','#pwd',{
			tips:[2,'#ff0000']
		});
		$("input#pwd").focus();	
		return;
	}
	$.ajax({
		url:"/manager/admin/login",
		type:"POST",
		data:{"fAdminCode":code,"fAdminPwd":pwd},
		dataType : "text",
		success:function(result){
			var obj = JSON.parse(result); 
			if(obj.resultCode=="200")
			{
				setCookie("adminCode",obj.retMap.admin.fAdminCode);
				setCookie("adminId",obj.retMap.admin.fAdminId);
				setCookie("adminRole",obj.retMap.admin.fAdminRole);
				window.location.replace("admin.html");
			}
			if(obj.resultCode == "-1"){
				alert(obj.resultMsg);
			}
			if(obj.resultCode == "-2"){
				alert(obj.resultMsg);
			}
		},
		error:function(){
		}
		
	});
}

//判断是否登陆状态
function isLogin()
{
	var adminCode = getCookie("adminCode");
	if(adminCode==""||adminCode==null)
	{
		window.location.replace("login.html");
	}
	$("#account").text(adminCode);
}

//退出登录
function loginOut()
{
	setCookie("adminCode","");
	setCookie("adminId","");
	setCookie("adminRole","");
	window.location.replace("login.html");
}

//查看编辑弹框
function updatepassword(){
	layer.open({
		type: 1,
		area: ['380px','250px'],
		fix: false, //不固定
		maxmin: true,
		shade:0.4,
		title: '修改密码',
		btn:['修改','取消'],
	    content: '<div class="table-zone">'+
         '<table style="margin-left: auto;margin-right: auto;">' +
            '<tbody>' +
              '<tr style="height:50px">' +
                '<td style="float:right;margin-top:10px">旧&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>' +
                '<td><input type="password" id="oldPwd" style="height:35px;width:232px;border: 1px solid #B6D2E8;padding:2px"></td>' +
              '</tr>' +
              '<tr style="height:50px">' +
		         '<td style="float:right;margin-top:10px">新&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>' +
		         '<td><input type="password" id="newPwd" style="height:35px;width:232px;border: 1px solid #B6D2E8;padding:2px"></td>' +
		      '</tr>' +
              '<tr style="height:50px">' +
                  '<td style="float:right;margin-top:10px">确认新密码：</td>' +
                  '<td><input type="password" id="againNPwd" style="height:35px;width:232px;border: 1px solid #B6D2E8;padding:2px"></td>' +
              '</tr>' +
            '</tbody>' +
         '</table>' +
        '</div>',
        yes:function(index){
        	updatePwd(getCookie("adminId"),index);
        },
        success:function(){
        }
	});
}
//修改密码
function updatePwd(adminId,index){
	var oldPwd=$("#oldPwd").val();
	var newPwd=$("#newPwd").val();
	var againNPwd=$("#againNPwd").val();
	if(adminId=="" || adminId==null){
		alert('登陆超时，请重新登陆');
	  	window.location.replace("login.html");//修改成功
	}
	if(oldPwd=="" || oldPwd==null){
		layer.tips('请输入旧密码！', '#oldPwd',{
			  tips: [2, '#ff0000']});
		$("#oldPwd").focus();
		return;
	}
	if(newPwd=="" || newPwd==null){
		layer.tips('请输入新密码！', '#newPwd',{
			  tips: [2, '#ff0000']});
		$("#newPwd").focus();
		return;
	}
	if(againNPwd=="" || againNPwd==null){
		layer.tips('请再次输入新密码！', '#againNPwd',{
			  tips: [2, '#ff0000']});
		$("#againNPwd").focus();
		return;
	}
	if(againNPwd!=newPwd){
		layer.tips('二次密码不一致！', '#againNPwd',{
			  tips: [2, '#ff0000']});
		$("#againNPwd").focus();
		return;
	}
	var strJSON={"oldPwd":oldPwd,"newPwd":newPwd,"fAdminId":adminId};
	$.ajax({
		url:"/manager/admin/updatePwd",
		async:false,
		type:"POST",
		dataType:"text",
		data:strJSON,
		success:function(result)
		{
			var obj = JSON.parse(result); 
			if(obj.resultCode=="200")
			{
				alert("修改密码成功！");
				layer.closeAll();
				return;
			}
			else if(obj.resultCode == "-2"){
				alert(obj.resultMsg);
			}else{
				alert(obj.resultMsg);
			}
			
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			
		}
	});
}
