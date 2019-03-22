package com.example.pojo.userInfo;

import java.io.Serializable;

public class UserInfoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3304388730991050639L;
	private String fUserCode; //用户编码
	private String fUserName; //用户姓名
	private String fUserPwd; //用户密码
	public String getfUserCode() {
		return fUserCode;
	}
	public void setfUserCode(String fUserCode) {
		this.fUserCode = fUserCode;
	}
	public String getfUserName() {
		return fUserName;
	}
	public void setfUserName(String fUserName) {
		this.fUserName = fUserName;
	}
	public String getfUserPwd() {
		return fUserPwd;
	}
	public void setfUserPwd(String fUserPwd) {
		this.fUserPwd = fUserPwd;
	}
	

}
