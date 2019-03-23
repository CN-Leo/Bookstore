package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.common.BaseResultInfo;
import com.example.common.SystemConstant;
import com.example.common.SystemResultCodeConstant;
import com.example.exception.CRUDException;
import com.example.pojo.userInfo.UserInfoBean;
import com.example.service.iface.IUserService;
import com.example.util.DESTools;
import com.example.util.DateTimeUtil;
import com.example.util.RequstUtil;
import com.example.util.SessionUtil;


@Controller
@MapperScan("com.example.dao")
public class UserCtrl {
	private Logger logger = LoggerFactory.getLogger(UserCtrl.class);
	@Autowired
	IUserService iUserService;

	@RequestMapping(method=RequestMethod.POST,value ="/login",produces = "text/plain")
	public @ResponseBody String login(HttpServletRequest request,HttpServletResponse response) {
		logger.info("######login start######");
		String fUserCode = (String)request.getParameter("fUserCode");//获取用户名
		String fUserPwd = (String)request.getParameter("fUserPwd");//获取密码
		BaseResultInfo baseResultInfo =  iUserService.login(request,fUserCode,fUserPwd);
		return JSONObject.toJSONString(baseResultInfo);
	}
	@RequestMapping(method=RequestMethod.POST,value ="/register",produces = "text/plain")
	public @ResponseBody String register(HttpServletRequest request,HttpServletResponse response) {
		logger.info("######register start######");
		Map<String,Object> param = new HashMap<String,Object>();
		String fUserCode = RequstUtil.getRequesParameterAsStr(request, "fUserCode", SystemConstant.BLANK_STRING);//获取用户编码
		String fUserName = RequstUtil.getRequesParameterAsStr(request, "fUserName", SystemConstant.BLANK_STRING);//获取用户名称
		String fUserPwd = RequstUtil.getRequesParameterAsStr(request, "fUserPwd", SystemConstant.BLANK_STRING);//获取用户密码
		String fUserRole = RequstUtil.getRequesParameterAsStr(request, "fUserRole", SystemConstant.BLANK_STRING);//获取用户密码
		String fResource = RequstUtil.getRequesParameterAsStr(request, "fResource", SystemConstant.BLANK_STRING);//获取用户密码
		DESTools desTools = DESTools.getInstace();
		DateTimeUtil.getTodayChar14();
		fUserPwd = desTools.getEncString(fUserPwd);
		param.put("fUserCode", fUserCode);
		param.put("fUserName", fUserName);
		param.put("fUserRole", fUserRole);
		param.put("fUserPwd", fUserPwd);
		param.put("fUserState", "1");
		param.put("fCreateTime", DateTimeUtil.getTodayChar14());
		param.put("fResource",fResource);
		param.put("fUserAccountBalance",0);
		param.put("fUserIntegral",0);
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iUserService.register(param);
		} catch (CRUDException e) {
			baseResultInfo.setResultCode(SystemResultCodeConstant.REGISTER_ERROR);
			baseResultInfo.setResultMsg(SystemResultCodeConstant.REGISTER_ERROR);
		}catch(Exception e1) 
		{
			logger.error("##注册未知错误##"+e1);
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	
	@RequestMapping(method=RequestMethod.POST,value ="/getIndexInfo",produces = "text/plain")
	public @ResponseBody String getIndexInfo(HttpServletRequest request,HttpServletResponse response) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		UserInfoBean user = (UserInfoBean)SessionUtil.getObjectAttribute(request, "user_info");
		return JSONObject.toJSONString(baseResultInfo);
	}
}
