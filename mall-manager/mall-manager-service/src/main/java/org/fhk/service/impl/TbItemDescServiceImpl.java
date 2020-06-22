package org.fhk.service.impl;

import org.fhk.mapper.TbItemDescMapper;
import org.fhk.pojo.TbItemDesc;
import org.fhk.service.TbItemDescService;
import org.fhk.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbItemDescServiceImpl implements TbItemDescService {

	@Autowired
	private TbItemDescMapper tbItemDescMapper;
	
	@Override
	public FjnyResult getTbItemDesc(Long itemId) {
		TbItemDesc itemDesc = tbItemDescMapper.selectByPrimaryKey(itemId);
		return FjnyResult.ok(itemDesc);
	}

}
