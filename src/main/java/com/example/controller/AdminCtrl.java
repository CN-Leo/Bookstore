package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.common.BaseResultInfo;
import com.example.model.Page;
import com.example.pojo.adminInfo.AdminInfoBean;
import com.example.service.iface.IAdminService;
import com.example.util.DateTimeUtil;
import com.example.util.SequenceGenerator;

@Controller
@MapperScan("com.example.dao")
@RequestMapping("/manager")
public class AdminCtrl {

	private Logger logger = LoggerFactory.getLogger(AdminCtrl.class);
	
	@Autowired
	private IAdminService iAdminService;
	@Autowired
	SequenceGenerator logIdSeqGen;
	
	@RequestMapping(method=RequestMethod.POST,value ="/admin/register",produces = { "application/xml", "application/json" })
	public @ResponseBody String register(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String,Object>();
		String fAdminCode = String.valueOf(request.getParameter("fAdminCode")).trim();
		String fAdminRole = String.valueOf(request.getParameter("fAdminRole")).trim();
		
		param.put("fAdminId", logIdSeqGen.next());
		param.put("fAdminCode", fAdminCode);
		param.put("fAdminPwd", "123456");
		param.put("fAdminRole", fAdminRole);
		param.put("fCreateTime", DateTimeUtil.getTodayChar14());
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iAdminService.register(param);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("新建管理员失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	
	@RequestMapping(method=RequestMethod.POST,value ="/admin/login",produces = { "application/xml", "application/json" })
	public @ResponseBody String adminLogin(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String,Object>();
		String fAdminCode = String.valueOf(request.getParameter("fAdminCode")).trim();
		String fAdminPwd = String.valueOf(request.getParameter("fAdminPwd")).trim();
		
		param.put("fAdminCode", fAdminCode);
		param.put("fAdminPwd", fAdminPwd);
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iAdminService.login(param);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("登陆失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	
	@RequestMapping(method=RequestMethod.POST,value ="/admin/updatePwd",produces = { "application/xml", "application/json" })
	public @ResponseBody String updatePwd(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String,Object>();
		String fAdminId = String.valueOf(request.getParameter("fAdminId")).trim();
		String oldPwd = String.valueOf(request.getParameter("oldPwd")).trim();
		String newPwd = String.valueOf(request.getParameter("newPwd")).trim();
		
		param.put("fAdminId", fAdminId);
		param.put("oldPwd", oldPwd);
		param.put("newPwd", newPwd);
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iAdminService.updatePwd(param);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("修改密码失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	@RequestMapping(method=RequestMethod.POST,value ="/admin/updateAdmin",produces = { "application/xml", "application/json" })
	public @ResponseBody String updateAdmin(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String,Object>();
		String fAdminId = String.valueOf(request.getParameter("fAdminId")).trim();
		String fAdminCode = String.valueOf(request.getParameter("fAdminCode")).trim();
		
		param.put("fAdminId", fAdminId);
		param.put("fAdminCode", fAdminCode);
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iAdminService.updateAdmin(param);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("修改管理员失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	
	@RequestMapping(method=RequestMethod.GET,value ="/admin/del",produces = { "application/xml", "application/json" })
	public @ResponseBody String delAdmin(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String,Object>();
		String fAdminId = String.valueOf(request.getParameter("fAdminId")).trim();
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iAdminService.delAdmin(fAdminId);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("删除失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	@RequestMapping(method=RequestMethod.GET,value ="/admin/reset",produces = { "application/xml", "application/json" })
	public @ResponseBody String resetAdmin(HttpServletRequest request,HttpServletResponse response) {
		String fAdminId = String.valueOf(request.getParameter("fAdminId")).trim();
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iAdminService.resetAdmin(fAdminId);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("重置密码失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	@RequestMapping(method=RequestMethod.GET,value ="/admin/actAdmin",produces = { "application/xml", "application/json" })
	public @ResponseBody String actAdmin(HttpServletRequest request,HttpServletResponse response) {
		String fAdminId = String.valueOf(request.getParameter("fAdminId")).trim();
		String fAdminState = String.valueOf(request.getParameter("fAdminState")).trim();
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iAdminService.actAdmin(fAdminId,fAdminState);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("重置密码失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	
	@RequestMapping(value = "/admin/page",
	        produces = { "application/xml", "application/json" }, 
	        method = RequestMethod.GET)
	ResponseEntity<Page<AdminInfoBean>> queryFirmDrugByPage(
			@RequestParam(value = "pageSize", required = true) Integer pageSize,
			@RequestParam(value = "pageNumber", required = true) Integer pageNumber,
			@RequestParam(value = "search_like", required = false) String searchLike,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "sortName", required = false) String sortName) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(searchLike))
		{
			paraMap.put("searchLike", searchLike);
		}
		if(!StringUtils.isEmpty(sortName))
		{
			paraMap.put("sortName", sortName);
		}
		if(!StringUtils.isEmpty(sortOrder))
		{
			paraMap.put("sortOrder", sortOrder);
		}
		Page<AdminInfoBean> page = this.iAdminService.queryAdminInfoPage(pageNumber,pageSize,paraMap);
		return new ResponseEntity<Page<AdminInfoBean>>(page,HttpStatus.OK);
	}
	
	
}
