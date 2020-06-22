package org.fhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import org.fhk.pojo.TbItem;
import org.fhk.service.TbItemCatService;
import org.fhk.service.TbItemDescService;
import org.fhk.service.TbItemService;
import org.fhk.utils.EasyUIDataGridResult;
import org.fhk.utils.EasyUITreeNodeBean;
import org.fhk.utils.FjnyResult;

@Controller
@RequestMapping("/item")
public class TbItemController {

	@Autowired
	public TbItemService tbItemService;
	@Autowired
	public TbItemCatService tbItemCatService;
	@Autowired
	public TbItemDescService tbItemDescService;
	
	@RequestMapping("/getItem")
	@ResponseBody
	public EasyUIDataGridResult getTbItemList(@RequestParam(defaultValue = "1")
	Integer page
			,@RequestParam(defaultValue = "10")Integer rows){
		return tbItemService.getTbItemList(page, rows);
	}
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public FjnyResult saveTbItem(TbItem tbItem,String desc,String itemParams){
		System.out.println("========saveTbItem=========");
		tbItemService.saveTbItem(tbItem,desc,itemParams);
		return FjnyResult.ok();
	}
	
	@RequestMapping("/cat/list")
	@ResponseBody
	public List<EasyUITreeNodeBean> getTbItemCatList(@RequestParam(defaultValue = "0") Long id) {
		return tbItemCatService.getTbItemCatList(id);
	}
	
	@RequestMapping("/query/item-desc/{id}")
	@ResponseBody
	public FjnyResult getTbItemDesc(@PathVariable Long id){
		return tbItemDescService.getTbItemDesc(id);
	}
	
	@RequestMapping("/updata")
	@ResponseBody
	public FjnyResult updateTbItem(TbItem tbItem,String desc){
		return tbItemService.updateTbItem(tbItem, desc);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public FjnyResult deleteTbItem(@RequestParam("ids") List<Long> ids){
		return tbItemService.deleteTbItem(ids);
	}
	
	@RequestMapping("/putaway")
	@ResponseBody
	public FjnyResult putawayTbItem(@RequestParam("ids") List<Long> ids){
		return tbItemService.putawayTbItem(ids);
	}
	
	@RequestMapping("/soldout")
	@ResponseBody
	public FjnyResult soldoutTbItem(@RequestParam("ids") List<Long> ids){
		return tbItemService.soldoutTbItem(ids);
	}
}

