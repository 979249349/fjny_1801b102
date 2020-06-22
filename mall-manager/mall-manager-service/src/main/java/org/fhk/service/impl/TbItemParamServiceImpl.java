package org.fhk.service.impl;

import java.util.Date;
import java.util.List;

import org.fhk.mapper.TbItemCatMapper;
import org.fhk.mapper.TbItemParamMapper;
import org.fhk.pojo.TbItemCat;
import org.fhk.pojo.TbItemParam;
import org.fhk.pojo.TbItemParamExample;
import org.fhk.pojo.TbItemParamExample.Criteria;
import org.fhk.service.TbItemParamService;
import org.fhk.utils.EasyUIDataGridResult;
import org.fhk.utils.ExceptionUtil;
import org.fhk.utils.FjnyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {

	@Autowired
	private TbItemParamMapper tbItemParamMapper;
	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public EasyUIDataGridResult getItemParamList(Integer page, Integer rows) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		for (TbItemParam tbItemParam : list) {
			if(null != tbItemParam.getItemCatId()) {
			String tbItemCatName = getItemCatName(tbItemParam.getItemCatId());
			tbItemParam.setItemCatName(tbItemCatName);
			}
		}
		PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
		long total =pageInfo.getTotal();
		EasyUIDataGridResult dataGrid = new EasyUIDataGridResult(total, list);
		return dataGrid;
	}
	
	public String getItemCatName(Long cid){
		TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(cid);
		return tbItemCat.getName();
	}

	@Override
	public FjnyResult checkParam(Long itemCatId) {
		try {
		TbItemParamExample example = new TbItemParamExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andItemCatIdEqualTo(itemCatId);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example );
		if(null == list || list.isEmpty()) {
			return FjnyResult.ok();
		}
		return FjnyResult.ok(list.get(0));
		} catch (Exception e) {
			return FjnyResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}

	@Override
	public FjnyResult addItemParam(Long itemCatId, String paramData) {
		try {
		TbItemParam record = new TbItemParam();
		record.setItemCatId(itemCatId);
		record.setParamData(paramData);
		record.setCreated(new Date());
		record.setUpdated(new Date());
		tbItemParamMapper.insert(record);
		return FjnyResult.ok();
		} catch (Exception e) {
			return FjnyResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
	
	
}
