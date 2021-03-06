package com.example.pojo.drugInfo;

import java.io.Serializable;

public class DrugInfoBean implements Serializable{

	private static final long serialVersionUID = 135035957533844775L;
	
	private String fId;       //主键编码
	private String fDrugCode; //药品编码
	private String fDrugName; //药品名称
	private String fDrugType; //药品类型
	private long fDrugPrice;  //药品价格单位：分
	private String fIsPrescription; //0非处方 1处方
	private String fDrugModel; // 0中药 1西药 2复方药
	private String fOperTime; //操作时间
	private String fDrugImg; //药品图片
	private String fContent; //商品详细描述
	private int fStock; //商品详细描述
	private String fState; //商品状态1 有效 0 无效 用于删除和查询药品订单历史
	public String getfId() {
		return fId;
	}
	public void setfId(String fId) {
		this.fId = fId;
	}
	public String getfDrugCode() {
		return fDrugCode;
	}
	public void setfDrugCode(String fDrugCode) {
		this.fDrugCode = fDrugCode;
	}
	public String getfDrugName() {
		return fDrugName;
	}
	public void setfDrugName(String fDrugName) {
		this.fDrugName = fDrugName;
	}
	public String getfDrugType() {
		return fDrugType;
	}
	public void setfDrugType(String fDrugType) {
		this.fDrugType = fDrugType;
	}
	public long getfDrugPrice() {
		return fDrugPrice;
	}
	public void setfDrugPrice(long fDrugPrice) {
		this.fDrugPrice = fDrugPrice;
	}
	public String getfIsPrescription() {
		return fIsPrescription;
	}
	public void setfIsPrescription(String fIsPrescription) {
		this.fIsPrescription = fIsPrescription;
	}
	public String getfOperTime() {
		return fOperTime;
	}
	public void setfOperTime(String fOperTime) {
		this.fOperTime = fOperTime;
	}
	public String getfDrugImg() {
		return fDrugImg;
	}
	public void setfDrugImg(String fDrugImg) {
		this.fDrugImg = fDrugImg;
	}
	public String getfContent() {
		return fContent;
	}
	public void setfContent(String fContent) {
		this.fContent = fContent;
	}
	public String getfDrugModel() {
		return fDrugModel;
	}
	public void setfDrugModel(String fDrugModel) {
		this.fDrugModel = fDrugModel;
	}
	public int getfStock() {
		return fStock;
	}
	public void setfStock(int fStock) {
		this.fStock = fStock;
	}
	public String getfState() {
		return fState;
	}
	public void setfState(String fState) {
		this.fState = fState;
	}

}
