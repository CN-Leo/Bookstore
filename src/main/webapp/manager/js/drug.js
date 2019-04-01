var drug_img;
function queryDrugPage() {
	$('#drugPage').bootstrapTable({
		method: 'get',
		contentType: "application/x-www-form-urlencoded",
		url: "/manager/drug/page", 
		sortName: "f_oper_time",
	    sortOrder:"asc",
	    sortable: true,
		striped: true, 
		pageNumber: 1, 
		pagination: true, 
		showRefresh: false, 
		sidePagination: 'server', //server:服务器端分页|client：前端分页
		pageSize: 10, //单页记录数
		pageList: [10, 20, 50], //可选择单页记录数
		queryParamsType : "",
		queryParams: function(params) {    
			var temp = {      
				pageNumber: params.pageNumber, 
				pageSize: params.pageSize, 
				sortOrder: params.sortOrder,
	            sortName: params.sortName,
				search_like: $("#search_like").val(),
				         
			}; 
			return temp;  
		},
		columns: [{
			title: 'ID',
			field: 'fId',
			align:'center',
			visible: false
		}, {
			title: '药品编号',
			field: 'fDrugCode',
			sortable: false,
			align:'center'
		},{
			title: '名称',
			field: 'fDrugName',
			align:'center',
		},{
			title: '分类',
			field: 'fDrugType',
			align:'center',
			formatter:function(value,row,index){
				if(row.fDrugType=='1')
				{
					value="解热镇痛药"
				}
				else if(row.fDrugType=="2"){
					value="循环系统"
				}
				else if(row.fDrugType=="3"){
					value="妇科用药"
				}
				else if(row.fDrugType=="4"){
					value="皮肤科用"
				}
				else if(row.fDrugType=="5"){
					value="动物类"
				}
				else if(row.fDrugType=="6"){
					value="矿物类"
				}
				else if(row.fDrugType=="7"){
					value="I类器材"
				}
				else if(row.fDrugType=="8"){
					value="II类器材"
				}
				else if(row.fDrugType=="9"){
					value="辅助用品"
				}
				else if(row.fDrugType=="10"){
					value="避孕用品"
				}
				else if(row.fDrugType=="11"){
					value="改善促进"
				}
				else if(row.fDrugType=="12"){
					value="排毒养颜"
				}
				return value;
			}
		},{
			title: '价格（元）',
			field: 'fDrugPrice',
			align:'center',
			formatter:function(value,row,index){
				return changeMoney(row.fDrugPrice)
			}
		},{
			title: '处方药',
			field: 'fIsPrescription',
			align:'center',
			formatter:function(value,row,index){
				if(row.fIsPrescription=='1')
				{
					value="是"
				}else{
					value="否"
				}
				return value;
			}
		},{
			title: '成分',
			field: 'fDrugModel',
			align:'center',
			formatter:function(value,row,index){
				if(row.fDrugModel=='0')
				{
					value="中药"
				}else if(row.fDrugModel=='1'){
					value="西药"
				}else{
					value="复方药"
				}
				return value;
			}
		},{
			title: '创建时间',
			field: 'fOperTime',
			align:'center',
			sortable: false,
			formatter:function(value,row,index)
			{
				if(value)
				{
					return formatDate14(value)
				}else{
					return "--"
				}
				
			}
		},{
			title: '描述',
			field: 'fContent',
			align:'center',
		},{
			title: '库存',
			field: 'fStock',
			align:'center',
		},{
			field: 'operate',
	    	title: '操作',
	    	align: 'center',
	    	formatter: operateFunction,
		}]
	})
}
//
function operateFunction(value, row, index)
{
	if(getCookie("adminRole")=="1"){
		return ["<a class=\"a_green\" href=\"drugupdate.html?fId="+row.fId+"&fDrugName="+row.fDrugName+"\">修改</a>"+
	        "<a href=\"javascript:void(0)\"  class=\"a_red\" onclick=\"delFunction('"+row.fId+"')\">删除</a>"].join("");
	}else{
		return "";
	}
}
//删除
function delFunction(fId)
{
	layer.confirm("确认删除该药品?",{closeBtn:0,btn:['是','否']},function(){
		$.ajax({
		url:"/manager/drug/del?fId="+fId,
		async:false,
		type:"get",
		dataType:"text",
		success:function(result)
		{
			var obj = JSON.parse(result);
			layer.closeAll();
			if(obj.resultCode=="200")
			{
				alert("删除成功！");
				refreshDrugTable();
			}else{
				alert(obj.resultMsg);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
		}
		});
	})
}
//刷新table
function refreshDrugTable()
{
	$("#drugPage").bootstrapTable('refresh');
}

//新增
function addDrug(){
	var fDrugName = $("#fDrugName").val();
	var fDrugType = $("#fDrugType").val();
	var fDrugModel = $("#fDrugModel").val();
	var fIsPrescription = $("#fIsPrescription").val();
	var fDrugPrice = $("#fDrugPrice").val();
	var fStock = $("#fStock").val();
	var fContent = $("#fContent").val();
	if(fDrugName==""||fDrugName==null)
	{
		layer.tips('不为空！', '#fDrugName',{tips: [2, '#ff0000']});
		$("#fDrugName").focus();return;
	}
	if(fDrugPrice==""||fDrugPrice==null)
	{
		layer.tips('不为空！', '#fDrugPrice',{tips: [2, '#ff0000']});
		$("#fDrugPrice").focus();return;
	}else{
		var reg =/^(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/ ;
		if(!reg.test(fDrugPrice)||fDrugPrice==0){
			layer.tips('价格必须大于0并且小数最多2位！', '#fDrugPrice',{tips: [2, '#ff0000']});
			$("#fDrugPrice").focus();return;
		}
	}
	if(fStock==""||fStock==null)
	{
		layer.tips('不为空！', '#fStock',{tips: [2, '#ff0000']});
		$("#fStock").focus();return;
	}else{
		if(!reg.test(fStock)){
			layer.tips('库存必须大于0！', '#fStock',{tips: [2, '#ff0000']});
			$("#fStock").focus();return;
		}
	}
	if(drug_img==""||drug_img==null)
	{
		layer.tips('请上传图片！', '#upload-file',{tips: [2, '#ff0000']});
		$("#upload-file").focus();return;
	}
	if(fContent==""||fContent==null)
	{
		layer.tips('不为空！', '#fContent',{tips: [2, '#ff0000']});
		$("#fContent").focus();return;
	}
	var str = {"fDrugName":fDrugName,"fDrugType":fDrugType,
			"fDrugModel":fDrugModel,"fIsPrescription":fIsPrescription,
			"fDrugPrice":fDrugPrice,"fStock":fStock,"fContent":fContent,
			"fDrugImg":drug_img}
	$.ajax({
		url:"/manager/drug/add",
		async:false,
		data:str,
		type:"POST",
		dataType :"text",
		success:function(result)
		{
			var obj = JSON.parse(result); 
			if(obj.resultCode=="200")
			{
				alert("新增药品成功！");
				window.location.href="/manager/drug.html";
			}else{
				alert(obj.resultMsg);
			}
		},
	});
}
function updateDrug(fId){
	var fDrugName = $("#fDrugName").val();
	var fDrugType = $("#fDrugType").val();
	var fDrugModel = $("#fDrugModel").val();
	var fIsPrescription = $("#fIsPrescription").val();
	var fDrugPrice = $("#fDrugPrice").val();
	var fStock = $("#fStock").val();
	var fContent = $("#fContent").val();
	if(fDrugName==""||fDrugName==null)
	{
		layer.tips('不为空！', '#fDrugName',{tips: [2, '#ff0000']});
		$("#fDrugName").focus();return;
	}
	if(fDrugPrice==""||fDrugPrice==null)
	{
		layer.tips('不为空！', '#fDrugPrice',{tips: [2, '#ff0000']});
		$("#fDrugPrice").focus();return;
	}else{
		var reg =/^(0|[1-9]\d*)(\s|$|\.\d{1,2}\b)/ ;
		if(!reg.test(fDrugPrice)||fDrugPrice==0){
			layer.tips('价格必须大于0并且小数最多2位！', '#fDrugPrice',{tips: [2, '#ff0000']});
			$("#fDrugPrice").focus();return;
		}
	}
	if(fStock==""||fStock==null)
	{
		layer.tips('不为空！', '#fStock',{tips: [2, '#ff0000']});
		$("#fStock").focus();return;
	}else{
		if(!reg.test(fStock)){
			layer.tips('库存必须大于0！', '#fStock',{tips: [2, '#ff0000']});
			$("#fStock").focus();return;
		}
	}
	if(drug_img==""||drug_img==null)
	{
		layer.tips('请上传图片！', '#upload-file',{tips: [2, '#ff0000']});
		$("#upload-file").focus();return;
	}
	if(fContent==""||fContent==null)
	{
		layer.tips('不为空！', '#fContent',{tips: [2, '#ff0000']});
		$("#fContent").focus();return;
	}
	var str = {"fId":fId,"fDrugName":fDrugName,"fDrugType":fDrugType,
			"fDrugModel":fDrugModel,"fIsPrescription":fIsPrescription,
			"fDrugPrice":fDrugPrice,"fStock":fStock,"fContent":fContent,
			"fDrugImg":drug_img}
	$.ajax({
		url:"/manager/drug/updateDrug",
		async:false,
		data:str,
		type:"POST",
		dataType :"text",
		success:function(result)
		{
			var obj = JSON.parse(result); 
			if(obj.resultCode=="200")
			{
				alert("更新药品成功！");
				window.location.href="/manager/drug.html";
			}else{
				alert(obj.resultMsg);
			}
		},
	});
}
//
function queryDrugById(fId){
	$.ajax({
		url:"/manager/drug/select?fId="+fId,
		async:false,
		type:"GET",
		dataType :"text",
		success:function(result)
		{
			var obj = JSON.parse(result); 
			if(obj.resultCode=="200")
			{
				$("#fDrugName").val(obj.retMap.drugInfo.fDrugName);
				$("#fDrugCode").val(obj.retMap.drugInfo.fDrugCode);
				$("#fDrugType").val(obj.retMap.drugInfo.fDrugType);
				$("#fDrugModel").val(obj.retMap.drugInfo.fDrugModel);
				$("#fIsPrescription").val(obj.retMap.drugInfo.fIsPrescription);
				$("#fDrugPrice").val(changeMoney(obj.retMap.drugInfo.fDrugPrice));
				$("#fStock").val(obj.retMap.drugInfo.fStock);
				$("#fContent").val(obj.retMap.drugInfo.fContent);
				$("#fDrugImg").attr("src",obj.retMap.drugInfo.fDrugImg);
            	drug_img = obj.retMap.drugInfo.fDrugImg;
			}else{
				alert(obj.resultMsg);
			}
		},
	});
}
