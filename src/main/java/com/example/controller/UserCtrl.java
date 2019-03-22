package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.common.BaseResultInfo;
import com.example.common.SystemConstant;
import com.example.dao.UserMapper;
import com.example.service.iface.IUserService;
import com.example.util.RequstUtil;


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
		BaseResultInfo baseResultInfo =  iUserService.login(fUserCode,fUserPwd);
		return JSONObject.toJSONString(baseResultInfo);
	}
	@RequestMapping(method=RequestMethod.POST,value ="/register",produces = "text/plain")
	public @ResponseBody String register(HttpServletRequest request,HttpServletResponse response) {
		logger.info("######register start######");
		String fUserCode = RequstUtil.getRequesParameterAsStr(request, "fUserCode", SystemConstant.BLANK_STRING);//获取用户编码
		String fUserName = RequstUtil.getRequesParameterAsStr(request, "fUserName", SystemConstant.BLANK_STRING);//获取用户名称
		String fUserPwd = RequstUtil.getRequesParameterAsStr(request, "fUserPwd", SystemConstant.BLANK_STRING);//获取用户密码
		BaseResultInfo baseResultInfo =  iUserService.login(fUserCode,fUserPwd);
		return JSONObject.toJSONString(baseResultInfo);
	}
}
