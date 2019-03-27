package com.example.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.example.pojo.drugInfo.DrugInfoBean;

public interface DrugMapper {

	void addDrug(Map<String, Object> param);

	void updateDrug(Map<String, Object> param);

	void delDrug(@Param(value="fId")String fId);

	Integer queryDrugCount(Map<String, Object> paraMap);

	List<DrugInfoBean> queryDrugInfoPage(Map<String, Object> paraMap);

}
