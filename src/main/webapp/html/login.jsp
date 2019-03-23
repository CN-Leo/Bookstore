<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Document</title>
<link rel="stylesheet" type="text/css" href="../res/static/css/main.css">
<link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
<script type="text/javascript" src="../res/layui/layui.js"></script>
<script type="text/javascript" src="../script/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="../script/qm_main.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
</head>
<body>

	<div class="content content-nav-base  login-content">
		<div class="login-bg">
			<div class="login-cont w1200">
				<div class="form-box">
					<form class="layui-form" action="">
						<legend>手机号登陆</legend>
						<div class="layui-form-item">
							<div class="layui-inline iphone">
								<div class="layui-input-inline">
									<i class="layui-icon layui-icon-cellphone iphone-icon"></i> <input
										type="tel" name="fUserCode" id="fUserCode"
										lay-verify="required|phone" placeholder="请输入手机号"
										autocomplete="off" class="layui-input">
								</div>
							</div>
							<div class="layui-inline iphone">
								<div class="layui-input-inline">
									<i class="layui-icon layui-icon-password iphone-icon"></i> <input
										id="fUserPwd" type="password" name="fUserPwd"
										lay-verify="required" placeholder="请输入密码" autocomplete="off"
										class="layui-input">
								</div>
							</div>
						</div>
						<div class="layui-form-item login-btn">
							<div class="layui-input-block">
								<button class="layui-btn" lay-submit="" lay-filter="login"
									onclick="return false">登陆</button><a href="register.html">去注册></a>

							</div>
							
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="footer">
		<div class="ng-promise-box">
			<div class="ng-promise w1200">
				<p class="text">
					<a class="icon1" href="javascript:;">7天无理由退换货</a> <a class="icon2"
						href="javascript:;">满99元全场免邮</a> <a class="icon3"
						style="margin-right: 0" href="javascript:;">100%品质保证</a>
				</p>
			</div>
		</div>
		 <div class="mod_help w1200">                                     
      <p>
        <a href="javascript:;">关于我们</a>
        <span>|</span>
        <a href="javascript:;">帮助中心</a>
        <span>|</span>
        <a href="javascript:;">售后服务</a>
        <span>|</span>
        <a href="javascript:;">药品资讯</a>
        <span>|</span>
        <a href="javascript:;">关于货源</a>
      </p>
      <p class="coty">药品商城版权所有 &copy; 2012-2020</p>
    </div>
	</div>
	
	<script>
layui.config({
      base: '../res/static/js/util' 
    }).use(['jquery','form','layer'], function(){
  var form = layui.form; 
  //
  form.on('submit(login)', function(data){
	  $.post("/login",data.field,function(res){
			if(res.resultCode==GLOBAL_SUCCESS_CODE)
			{
				var obj = res.retMap.user;
				window.location.href="index.jsp?fUserCode="+obj.fUserCode+"&fUserName="+obj.fUserName
			}else{
				delError(res);
				return false
			}
		},'json');
  });
});
var register = function()
{
window.location.href = "register.html"   
}
</script>
</body>
</html>