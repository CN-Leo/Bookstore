package com.example.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.BaseResultInfo;
import com.example.dao.AdminMapper;
import com.example.model.Page;
import com.example.pojo.adminInfo.AdminInfoBean;
import com.example.service.iface.IAdminService;
import com.example.util.DateTimeUtil;

@Service("adminServiceImpl")
public class AdminServiceImpl implements IAdminService{

	@Autowired
	AdminMapper adminMapper;
	
	public BaseResultInfo register(Map<String, Object> param) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		AdminInfoBean admin = adminMapper.queryAdminInfoByfAdminCode((String)param.get("fAdminCode"));
		if(admin!=null) 
		{
			baseResultInfo.setResultCode("-2");
			baseResultInfo.setResultMsg("账号已存在");
		}else 
		{
			adminMapper.register(param);
		}
		return baseResultInfo;
	}
	
	@Transactional
	public BaseResultInfo login(Map<String, Object> param) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		AdminInfoBean admin = adminMapper.queryAdminInfoByLogin((String)param.get("fAdminCode"),(String)param.get("fAdminPwd"));
		if(admin == null)
		{
			baseResultInfo.setResultCode("-1");
			baseResultInfo.setResultMsg("账号或者密码不正确！");
		}
		else if(admin.getfAdminState()!=1){
			baseResultInfo.setResultCode("-2");
			baseResultInfo.setResultMsg("账号已禁用，请联系管理员！");
		}else{
			adminMapper.updateAdminLastLoginTime(admin.getfAdminId(),DateTimeUtil.getTodayChar14());
			admin.setfAdminPwd(null);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("admin", admin);
			baseResultInfo.setRetMap(map);
		}
		return baseResultInfo;
	}

	@Override
	public BaseResultInfo updatePwd(Map<String, Object> param) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		AdminInfoBean admin = adminMapper.queryAdminInfoById((String)param.get("fAdminId"));
		if(admin==null){
			baseResultInfo.setResultCode("-1");
			baseResultInfo.setResultMsg("没有查询到该管理员！");
		}else{
			if(!admin.getfAdminPwd().equals((String)param.get("oldPwd"))){
				baseResultInfo.setResultCode("-2");
				baseResultInfo.setResultMsg("原密码错误！");
			}else{
				adminMapper.updateAdminPwd((String)param.get("fAdminId"),(String)param.get("newPwd"));
			}
		}
		return baseResultInfo;
	}

	@Override
	public Page<AdminInfoBean> queryAdminInfoPage(Integer pageNumber, Integer pageSize, Map<String, Object> paraMap) {
		Page<AdminInfoBean> page = new Page<AdminInfoBean>();
		Integer count = this.adminMapper.queryAdminCount(paraMap);
		if(count==null||count==0)
		{
			return null;
		}
		page.setTotal(count);
		paraMap.put("limit", pageSize);
		paraMap.put("offset", (pageNumber - 1) * pageSize);		
		List<AdminInfoBean> adminList = this.adminMapper.queryAdminInfoPage(paraMap);
		if(adminList!=null)
		{
			for(AdminInfoBean bean :adminList){
				bean.setfAdminPwd(null);
			}
			page.setRows(adminList);
		}
		return page;
	}

	@Override
	public BaseResultInfo delAdmin(String fAdminId) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		this.adminMapper.delAdmin(fAdminId);
		return baseResultInfo;
	}

	@Override
	public BaseResultInfo resetAdmin(String fAdminId) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		this.adminMapper.updateAdminPwd(fAdminId,"123456");
		return baseResultInfo;
	}

	@Override
	public BaseResultInfo actAdmin(String fAdminId, String fAdminState) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		this.adminMapper.updateAdminState(fAdminId,fAdminState);
		return baseResultInfo;
	}

	@Override
	public BaseResultInfo updateAdmin(Map<String, Object> param) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		AdminInfoBean admin = adminMapper.queryAdminInfoByfAdminCode((String)param.get("fAdminCode"));
		if(admin!=null) 
		{
			baseResultInfo.setResultCode("-2");
			baseResultInfo.setResultMsg("账号已存在");
		}else 
		{
			adminMapper.updateAdmin((String)param.get("fAdminId"),(String)param.get("fAdminCode"));
		}
		return baseResultInfo;
	}

}
