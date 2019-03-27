/**
 * 
 */
package com.example.service.iface;

import java.util.Map;

import com.example.common.BaseResultInfo;
import com.example.model.Page;
import com.example.pojo.drugInfo.DrugInfoBean;

/**
 * @author CHHUAN
 *
 */
public interface IDrugService {

	BaseResultInfo addDrug(Map<String, Object> param);

	BaseResultInfo updateDrug(Map<String, Object> param);

	BaseResultInfo delDrug(String fId);

	Page<DrugInfoBean> queryDrugInfoPage(Integer pageNumber, Integer pageSize, Map<String, Object> paraMap);

	
}
