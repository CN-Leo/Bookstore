package com.example.common;

import java.util.Map;

public class BaseResultInfo {
	private String resultCode = "200";
	private String resultMsg = "操作成功";
	private Map<String,Object> retMap;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public Map<String, Object> getRetMap() {
		return retMap;
	}
	public void setRetMap(Map<String, Object> retMap) {
		this.retMap = retMap;
	}
}
