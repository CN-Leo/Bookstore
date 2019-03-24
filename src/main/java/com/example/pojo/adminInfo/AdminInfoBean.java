package com.example.pojo.adminInfo;

import java.io.Serializable;

public class AdminInfoBean implements Serializable{

	private static final long serialVersionUID = -4230154342458579159L;

	private String fAdminId;//ID
	private String fAdminCode;//用户名
	private String fAdminPwd;//密码
	private int fAdminRole;//角色 1草鸡管理员 0 普通管理员
	private int fAdminState; //状态 1 启用 0 禁用
	private String fCreateTime;//创建时间
	private String fLastLoginTime;//最近登录时间
	public String getfAdminCode() {
		return fAdminCode;
	}
	public void setfAdminCode(String fAdminCode) {
		this.fAdminCode = fAdminCode;
	}
	public String getfAdminPwd() {
		return fAdminPwd;
	}
	public void setfAdminPwd(String fAdminPwd) {
		this.fAdminPwd = fAdminPwd;
	}
	public int getfAdminRole() {
		return fAdminRole;
	}
	public void setfAdminRole(int fAdminRole) {
		this.fAdminRole = fAdminRole;
	}
	public int getfAdminState() {
		return fAdminState;
	}
	public void setfAdminState(int fAdminState) {
		this.fAdminState = fAdminState;
	}
	public String getfCreateTime() {
		return fCreateTime;
	}
	public void setfCreateTime(String fCreateTime) {
		this.fCreateTime = fCreateTime;
	}
	public String getfLastLoginTime() {
		return fLastLoginTime;
	}
	public void setfLastLoginTime(String fLastLoginTime) {
		this.fLastLoginTime = fLastLoginTime;
	}
	public String getfAdminId() {
		return fAdminId;
	}
	public void setfAdminId(String fAdminId) {
		this.fAdminId = fAdminId;
	}
	
}
