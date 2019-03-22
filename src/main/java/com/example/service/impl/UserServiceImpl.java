package com.example.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.BaseResultInfo;
import com.example.common.SystemResultCodeConstant;
import com.example.common.SystemResultMsgConstant;
import com.example.dao.UserMapper;
import com.example.pojo.userInfo.UserInfoBean;
import com.example.service.iface.IUserService;
import com.example.util.DESTools;
import com.example.util.DateTimeUtil;
import com.example.util.SequenceGenerator;
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {
@Autowired
UserMapper userMapper;
@Autowired
SequenceGenerator logIdSeqGen;
	@Override
	public BaseResultInfo login(String fUserCode, String fUserPwd) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		DESTools desTools = DESTools.getInstace();
		DateTimeUtil.getTodayChar14();
		UserInfoBean user = userMapper.queryUserInfoByfUserCode(fUserCode);
		if(null==user) 
		{
			baseResultInfo.setResultCode(SystemResultCodeConstant.USER_UNREGISTER);
			baseResultInfo.setResultMsg(SystemResultMsgConstant.USER_UNREGISTER);
		}else 
		{
			String userPwd = user.getfUserPwd();
			userPwd = desTools.getDesString(userPwd);
			if(!userPwd.equals(fUserPwd)) 
			{
				baseResultInfo.setResultCode(SystemResultCodeConstant.USER_PASSWORD_ERROR);
				baseResultInfo.setResultMsg(SystemResultMsgConstant.USER_PASSWORD_ERROR);

			}else 
			{
				Map<String,Object> retMap = new HashMap<String,Object>();
				retMap.put("user", user);
				baseResultInfo.setRetMap(retMap);
			}
		}
		return baseResultInfo;
	}
}
