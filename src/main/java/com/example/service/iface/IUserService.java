package com.example.service.iface;

import com.example.common.BaseResultInfo;

public interface IUserService {
	/**
	 * 登陆验证
	 * @param fUserCode
	 * @param fUserPwd
	 * @return
	 */
	BaseResultInfo login(String fUserCode,String fUserPwd);
}
