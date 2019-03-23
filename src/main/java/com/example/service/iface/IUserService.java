package com.example.service.iface;

import java.util.Map;

import com.example.common.BaseResultInfo;

public interface IUserService {
	/**
	 * 登陆验证
	 * @param fUserCode
	 * @param fUserPwd
	 * @return
	 */
	BaseResultInfo login(String fUserCode,String fUserPwd);
	BaseResultInfo register(Map<String,Object> param) throws Exception;
}
