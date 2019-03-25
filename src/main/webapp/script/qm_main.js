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
 /**
  * 格式化日期 yyyy-MM-dd HH:mm:ss
  * 
  * @param dateStr
  * @returns {String}
  */
 function formatDate14(dateStr)
 {
 	var formatDate = "";
 	if(dateStr == null || dateStr == "")
 	{
 		return "";
 	}
 	if(dateStr.length < 8)
 	{
 		return "";
 	}
 	formatDate += dateStr.substring(0, 4) + "-";
 	formatDate += dateStr.substring(4, 6) + "-";
 	formatDate += dateStr.substring(6, 8);
 	if(dateStr.length >= 10)
 	{
 		formatDate += " " + dateStr.substring(8, 10);
 	}
 	if(dateStr.length >= 12)
 	{
 		formatDate += ":" + dateStr.substring(10, 12);
 	}
 	if(dateStr.length >= 14)
 	{
 		formatDate += ":" + dateStr.substring(12, 14);
 	}
 	
 	return formatDate;
 }
 /**
  * 将以分为单位的金额字符串转换成以元为单位的字符串
  * 
  * @param money
  * @returns {String}
  */
 function formatMoney(money){
 	var rMoney = "";
 	if(money == null || money == "")
 	{
 		return "0.00";
 	}else{
 		var fMoney = parseFloat(money)/100;
 		money = fMoney + "";
 		if(money == null || money == "")
 		{
 			return "0.00";
 		}
 		var index = money.indexOf(".");
 		if (index == -1)
         {
 			return money+".00";
         }else{
         	 var sb = new StringBuffer();
         	 var s0 = money.substring(0, index);// 整数部分
              var s1 = money.substring(index + 1);// 小数部分
              if (s1.length == 1)
              {// 小数点后一位
                  s1 = s1 + "0";
              }
              else if (s1.length > 2)
              {// 如果超过3位小数，截取2位就可以了
                  s1 = s1.substring(0, 2);
              }
              sb.append(s0);
              sb.append(".");
              sb.append(s1);
              money = sb.toString();
              return money;
         }
 	}
 }
