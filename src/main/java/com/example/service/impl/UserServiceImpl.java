package com.example.service.impl;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.BaseResultInfo;
import com.example.common.SystemConstant;
import com.example.common.SystemResultCodeConstant;
import com.example.common.SystemResultMsgConstant;
import com.example.dao.UserMapper;
import com.example.exception.CRUDException;
import com.example.pojo.userInfo.UserInfoBean;
import com.example.service.iface.IUserService;
import com.example.util.DESTools;
import com.example.util.DateTimeUtil;
import com.example.util.SequenceGenerator;
import com.example.util.SessionUtil;
@Service("userServiceImpl")
public class UserServiceImpl implements IUserService {
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
@Autowired
UserMapper userMapper;
@Autowired
SequenceGenerator logIdSeqGen;
	@Override
	public BaseResultInfo login(HttpServletRequest request,String fUserCode, String fUserPwd) {
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
				//登陆成功后往session中塞入用户信息
//				request.getCookies()
				SessionUtil.setObjectAttribute(request, SystemConstant.USER_INFO, user);
				baseResultInfo.setRetMap(retMap);
			}
		}
		return baseResultInfo;
	}
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
	public BaseResultInfo register(Map<String, Object> param) throws Exception{
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			userMapper.register(param);
		} catch (Exception e) {
			logger.error("##注册失败##"+e);
			throw new CRUDException();
		}
		return baseResultInfo;
	}
}
