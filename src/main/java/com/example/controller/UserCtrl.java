package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.common.BaseResultInfo;
import com.example.dao.UserMapper;
import com.example.service.iface.IUserService;


@Controller
@MapperScan("com.example.dao")
public class UserCtrl {
	@Autowired
	IUserService iUserService;

	@RequestMapping(method=RequestMethod.POST,value ="/login",produces = "text/plain")
	public @ResponseBody String getUserInfo(HttpServletRequest request,HttpServletResponse response) {
		String fUserCode = (String)request.getParameter("fUserCode");//获取用户名
		String fUserPwd = (String)request.getParameter("fUserPwd");//获取密码
		BaseResultInfo baseResultInfo =  iUserService.login(fUserCode,fUserPwd);
		return JSONObject.toJSONString(baseResultInfo);
	}
}
