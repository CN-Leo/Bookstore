package com.example.pojo.userInfo;

import java.io.Serializable;

public class UserInfoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3304388730991050639L;
	private String fUserCode; //用户编码
	private String fUserName; //用户姓名
	private String fUserRole; //用户角色  1普通用户
	private String fUserState;//用户状态 0停用  1启用
	private String fUserPwd; //用户密码
	private String fOperCode;//操作人编码
	private String fOperName;//操作人名称
	private String fOperTime;//操作时间
	private String fCreateTime;//创建时间
	private int fUserAccountBalance;//用户账户余额
	private int fUserIntegral;//用户积分
	private String fResource;//用户来源  1注册  2后台新增
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
	public String getfUserRole() {
		return fUserRole;
	}
	public void setfUserRole(String fUserRole) {
		this.fUserRole = fUserRole;
	}
	public String getfUserState() {
		return fUserState;
	}
	public void setfUserState(String fUserState) {
		this.fUserState = fUserState;
	}
	public String getfOperCode() {
		return fOperCode;
	}
	public void setfOperCode(String fOperCode) {
		this.fOperCode = fOperCode;
	}
	public String getfOperName() {
		return fOperName;
	}
	public void setfOperName(String fOperName) {
		this.fOperName = fOperName;
	}
	public String getfOperTime() {
		return fOperTime;
	}
	public void setfOperTime(String fOperTime) {
		this.fOperTime = fOperTime;
	}
	public String getfCreateTime() {
		return fCreateTime;
	}
	public void setfCreateTime(String fCreateTime) {
		this.fCreateTime = fCreateTime;
	}
	public int getfUserAccountBalance() {
		return fUserAccountBalance;
	}
	public void setfUserAccountBalance(int fUserAccountBalance) {
		this.fUserAccountBalance = fUserAccountBalance;
	}
	public int getfUserIntegral() {
		return fUserIntegral;
	}
	public void setfUserIntegral(int fUserIntegral) {
		this.fUserIntegral = fUserIntegral;
	}
	public String getfResource() {
		return fResource;
	}
	public void setfResource(String fResource) {
		this.fResource = fResource;
	}
	

}
