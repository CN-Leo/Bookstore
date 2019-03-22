package com.example.dao;

import org.apache.ibatis.annotations.Param;

import com.example.pojo.userInfo.UserInfoBean;

public interface UserMapper {
	UserInfoBean queryUserInfoByfUserCode(@Param("fUserCode")String fUserCode);
}
