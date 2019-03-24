package com.example.service.iface;

import java.util.Map;

import com.example.common.BaseResultInfo;
import com.example.model.Page;
import com.example.pojo.adminInfo.AdminInfoBean;

public interface IAdminService {

	BaseResultInfo register(Map<String, Object> param);

	BaseResultInfo login(Map<String, Object> param);

	BaseResultInfo updatePwd(Map<String, Object> param);

	Page<AdminInfoBean> queryAdminInfoPage(Integer pageNumber, Integer pageSize, Map<String, Object> paraMap);

	BaseResultInfo delAdmin(String fAdminId);

	BaseResultInfo resetAdmin(String fAdminId);

	BaseResultInfo actAdmin(String fAdminId, String fAdminState);

}
