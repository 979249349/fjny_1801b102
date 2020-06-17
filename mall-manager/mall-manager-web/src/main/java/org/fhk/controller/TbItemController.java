package org.fhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.fhk.pojo.TbItem;
import org.fhk.service.TbItemService;
import org.fhk.utils.EasyUIDataGridResult;
import org.fhk.utils.FjnyResult;

@Controller
@RequestMapping("/item")
public class TbItemController {

	@Autowired
	public TbItemService tbItemService;
	
	@RequestMapping("/getItem")
	@ResponseBody
	public EasyUIDataGridResult getTbItemList(@RequestParam(defaultValue = "1")
	Integer page
			,@RequestParam(defaultValue = "10")Integer rows){
		return tbItemService.getTbItemList(page, rows);
	}
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public FjnyResult saveTbItem(TbItem tbItem){
		System.out.println("========saveTbItem=========");
		tbItemService.saveTbItem(tbItem);
		return FjnyResult.ok();
	}
}
