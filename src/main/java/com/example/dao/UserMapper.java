package com.example.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.pojo.userInfo.UserInfoBean;

public interface UserMapper {
	UserInfoBean queryUserInfoByfUserCode(@Param("fUserCode")String fUserCode);
	void register(Map<String,Object> param);
}
