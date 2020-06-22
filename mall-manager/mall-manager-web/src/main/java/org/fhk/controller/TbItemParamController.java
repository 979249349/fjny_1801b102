package org.fhk.controller;

import org.fhk.service.TbItemParamService;
import org.fhk.utils.EasyUIDataGridResult;
import org.fhk.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class TbItemParamController {
	
	@Autowired
	private TbItemParamService tbItemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(@RequestParam(defaultValue = "1")
	Integer page
	,@RequestParam(defaultValue = "10")Integer rows) {
		return tbItemParamService.getItemParamList(page,rows);
		
	}
	
	
	//查询类目是否纯在规格模板
	@RequestMapping("/query/itemcatid/{itemCatId}")
	@ResponseBody
	public FjnyResult checkParam(@PathVariable Long itemCatId) {
		return tbItemParamService.checkParam(itemCatId);
	}

	//保存添加的类目规格模板
	@RequestMapping("/save/{itemCatId}")
	@ResponseBody
	public FjnyResult addItemParam(@PathVariable Long itemCatId,String paramData) {
		return tbItemParamService.addItemParam(itemCatId,paramData);
	}
}
