package org.fhk.service;

import org.fhk.pojo.TbItem;
import org.fhk.utils.EasyUIDataGridResult;
import org.fhk.utils.FjnyResult;

public interface TbItemService {
	//获取商品列表
	public EasyUIDataGridResult getTbItemList(Integer page,Integer rows);
	//添加商品
		public FjnyResult saveTbItem(TbItem tbItem);
}
