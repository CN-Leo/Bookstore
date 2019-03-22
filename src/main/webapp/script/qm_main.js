GLOBAL_SUCCESS_CODE = "200";
 function delError (res)
{
	var resultMsg = "系统错误";
	if(res.resultMsg)
	{
		resultMsg = res.resultMsg;
	}
	layer.msg(resultMsg);
}