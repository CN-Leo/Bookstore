package com.example.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.pojo.adminInfo.AdminInfoBean;

public interface AdminMapper {

	AdminInfoBean queryAdminInfoByfAdminCode(@Param("fAdminCode")String fAdminCode);

	void register(Map<String, Object> param);

	AdminInfoBean queryAdminInfoByLogin(@Param("fAdminCode")String fAdminCode,@Param("fAdminPwd") String fAdminPwd);

	void updateAdminLastLoginTime(@Param("fAdminId")String fAdminId,@Param("fLastLoginTime") String fLastLoginTime);

	AdminInfoBean queryAdminInfoById(@Param("fAdminId") String fAdminId);

	void updateAdminPwd(@Param("fAdminId") String fAdminId,@Param("fAdminPwd")String fAdminPwd);

	Integer queryAdminCount(Map<String, Object> paraMap);

	List<AdminInfoBean> queryAdminInfoPage(Map<String, Object> paraMap);

	void delAdmin(@Param("fAdminId")String fAdminId);

	void updateAdminState(@Param("fAdminId") String fAdminId,@Param("fAdminState")String fAdminState);

	void updateAdmin(@Param("fAdminId") String fAdminId,@Param("fAdminCode")String fAdminCode);
}
