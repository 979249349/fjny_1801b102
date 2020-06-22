package org.fhk.service;

import org.fhk.utils.EasyUIDataGridResult;
import org.fhk.utils.FjnyResult;

public interface TbItemParamService {

	EasyUIDataGridResult getItemParamList(Integer page, Integer rows);
	FjnyResult checkParam(Long itemCatId);
	
	FjnyResult addItemParam(Long itemCatId, String paramData);

}
