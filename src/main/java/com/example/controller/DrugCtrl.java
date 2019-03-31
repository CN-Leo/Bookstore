package com.example.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.example.common.BaseResultInfo;
import com.example.model.Page;
import com.example.pojo.drugInfo.DrugInfoBean;
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
	
	@RequestMapping(value = "/upload/uploadDrugImg", method = RequestMethod.POST)
    public ResponseEntity<String> upload(HttpServletRequest req, @RequestParam("file") MultipartFile file) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
            String destFileName = req.getServletContext().getRealPath("") + "manager"+File.separator+"upload" + File.separator + fileName;
            System.out.println(destFileName);
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
            //6.把文件名放在model里，以便后续显示用
            //byte[]b = FileUtils.readFileToByteArray(destFile);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("name", destFileName);
            map.put("src", "/upload/"+fileName);
            return new ResponseEntity<String>("/manager/upload/"+fileName,HttpStatus.OK);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }
	
}
