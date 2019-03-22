package com.example.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class RequstUtil {
public static String getRequesParameterAsStr(HttpServletRequest request,String paramName,String defaultStr) 
{
	String result  =String.valueOf(request.getParameter(paramName));
	if(StringUtils.isBlank(result)) 
	{
		result  =defaultStr;
	}
	return result;
}
}
