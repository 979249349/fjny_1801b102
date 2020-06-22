package org.fhk.service;

import java.util.List;

import org.fhk.pojo.TbItem;
import org.fhk.utils.EasyUIDataGridResult;
import org.fhk.utils.FjnyResult;

public interface TbItemService {
	//获取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows);
	//添加商品
	public FjnyResult saveTbItem(TbItem tbItem, String desc, String itemParams);
	//更新商品
	public FjnyResult updateTbItem(TbItem tbItem,String desc);
	//刪除商品
	public FjnyResult deleteTbItem(List<Long> ids);
	//上架商品
	public FjnyResult putawayTbItem(List<Long> ids);
	//下架商品
	public FjnyResult soldoutTbItem(List<Long> ids);
}
