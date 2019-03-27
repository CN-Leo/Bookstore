package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.common.BaseResultInfo;
import com.example.model.Page;
import com.example.pojo.drugInfo.DrugInfoBean;
import com.example.service.iface.IAdminService;
import com.example.service.iface.IDrugService;
import com.example.util.DateTimeUtil;
import com.example.util.MoneyFormatUtil;
import com.example.util.SequenceGenerator;

@Controller
@MapperScan("com.example.dao")
@RequestMapping("/manager")
public class DrugCtrl {

	@Autowired
	private IDrugService iDrugService;
	
	@Autowired
	SequenceGenerator logIdSeqGen;
	
	@RequestMapping(method=RequestMethod.POST,value ="/drug/add",produces = { "application/xml", "application/json" })
	public @ResponseBody String addDrug(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String,Object>();
		String fDrugName = String.valueOf(request.getParameter("fDrugName")).trim();
		String fDrugType = String.valueOf(request.getParameter("fDrugType")).trim();
		String fDrugPrice = String.valueOf(request.getParameter("fDrugPrice")).trim();
		String fIsPrescription = String.valueOf(request.getParameter("fIsPrescription")).trim();
		String fDrugImg = String.valueOf(request.getParameter("fDrugImg")).trim();
		String fContent = String.valueOf(request.getParameter("fContent")).trim();
		param.put("fId", logIdSeqGen.next());
		param.put("fDrugCode","D"+System.currentTimeMillis());
		param.put("fDrugName", fDrugName);
		param.put("fDrugType", fDrugType);
		param.put("fDrugPrivce",MoneyFormatUtil.fromYuanToFen(fDrugPrice));
		param.put("fIsPrescription", fIsPrescription);
		param.put("fDrugImg", fDrugImg);
		param.put("fCreateTime", DateTimeUtil.getTodayChar14());
		param.put("fContent", fContent);
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iDrugService.addDrug(param);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("新增药品失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}

	@RequestMapping(method=RequestMethod.POST,value ="/drug/updateDrug",produces = { "application/xml", "application/json" })
	public @ResponseBody String updateDrug(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String,Object>();
		String fId = String.valueOf(request.getParameter("fId")).trim();
		String fDrugName = String.valueOf(request.getParameter("fDrugName")).trim();
		String fDrugType = String.valueOf(request.getParameter("fDrugType")).trim();
		String fDrugPrivce = String.valueOf(request.getParameter("fDrugPrivce")).trim();
		String fIsPrescription = String.valueOf(request.getParameter("fIsPrescription")).trim();
		String fDrugImg = String.valueOf(request.getParameter("fDrugImg")).trim();
		String fContent = String.valueOf(request.getParameter("fContent")).trim();
		param.put("fId", fId);
		param.put("fDrugName", fDrugName);
		param.put("fDrugType", fDrugType);
		param.put("fDrugPrivce",MoneyFormatUtil.fromYuanToFen(fDrugPrivce));
		param.put("fIsPrescription", fIsPrescription);
		param.put("fDrugImg", fDrugImg);
		param.put("fContent", fContent);
		
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iDrugService.updateDrug(param);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("修改药品失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	
	@RequestMapping(method=RequestMethod.GET,value ="/drug/del",produces = { "application/xml", "application/json" })
	public @ResponseBody String delAdmin(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> param = new HashMap<String,Object>();
		String fId = String.valueOf(request.getParameter("fId")).trim();
		BaseResultInfo baseResultInfo = new BaseResultInfo();
		try {
			baseResultInfo = iDrugService.delDrug(fId);
		} catch(Exception e) 
		{
			baseResultInfo.setResultCode("0");
			baseResultInfo.setResultMsg("删除失败！");
		}
		return JSONObject.toJSONString(baseResultInfo);
	}
	@RequestMapping(value = "/drug/page",
	        produces = { "application/xml", "application/json" }, 
	        method = RequestMethod.GET)
	ResponseEntity<Page<DrugInfoBean>> queryDrugInfoByPage(
			@RequestParam(value = "pageSize", required = true) Integer pageSize,
			@RequestParam(value = "pageNumber", required = true) Integer pageNumber,
			@RequestParam(value = "search_like", required = false) String searchLike,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "sortName", required = false) String sortName) {
		Map<String,Object> paraMap = new HashMap<String,Object>();
		if(!StringUtils.isEmpty(searchLike))
		{
			paraMap.put("searchLike", searchLike);
		}
		if(!StringUtils.isEmpty(sortName))
		{
			paraMap.put("sortName", sortName);
		}
		if(!StringUtils.isEmpty(sortOrder))
		{
			paraMap.put("sortOrder", sortOrder);
		}
		Page<DrugInfoBean> page = this.iDrugService.queryDrugInfoPage(pageNumber,pageSize,paraMap);
		return new ResponseEntity<Page<DrugInfoBean>>(page,HttpStatus.OK);
	}
	
}
