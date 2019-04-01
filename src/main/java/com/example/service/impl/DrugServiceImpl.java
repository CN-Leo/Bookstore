package com.example.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.BaseResultInfo;
import com.example.dao.DrugMapper;
import com.example.model.Page;
import com.example.pojo.drugInfo.DrugInfoBean;
import com.example.service.iface.IDrugService;
@Service
public class DrugServiceImpl implements IDrugService{

	@Autowired
	DrugMapper drugMapper;
	
	@Override
	public BaseResultInfo addDrug(Map<String, Object> param) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		this.drugMapper.addDrug(param);
		return baseResultInfo;
	}

	@Override
	public BaseResultInfo updateDrug(Map<String, Object> param) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		drugMapper.updateDrug(param);
		return baseResultInfo;
	}

	@Override
	public BaseResultInfo delDrug(String fId) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		this.drugMapper.delDrug(fId);
		return baseResultInfo;
	}

	@Override
	public Page<DrugInfoBean> queryDrugInfoPage(Integer pageNumber, Integer pageSize, Map<String, Object> paraMap) {
		Page<DrugInfoBean> page = new Page<DrugInfoBean>();
		Integer count = this.drugMapper.queryDrugCount(paraMap);
		if(count==null||count==0)
		{
			return null;
		}
		page.setTotal(count);
		paraMap.put("limit", pageSize);
		paraMap.put("offset", (pageNumber - 1) * pageSize);		
		List<DrugInfoBean> drugList = this.drugMapper.queryDrugInfoPage(paraMap);
		if(drugList!=null)
		{
			page.setRows(drugList);
		}
		return page;
	}

	@Override
	public BaseResultInfo selectDrugById(String fId) {
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		DrugInfoBean bean = this.drugMapper.selectDrugById(fId);
		if(bean!=null)
		{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("drugInfo", bean);
			baseResultInfo.setRetMap(map);
		}
		return baseResultInfo;
	}

	
}
