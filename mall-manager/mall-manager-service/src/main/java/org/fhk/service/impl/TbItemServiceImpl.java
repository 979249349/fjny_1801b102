package org.fhk.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.fhk.mapper.TbItemMapper;
import org.fhk.pojo.TbItem;
import org.fhk.pojo.TbItemExample;
import org.fhk.service.TbItemService;
import org.fhk.utils.EasyUIDataGridResult;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TbItemServiceImpl implements TbItemService {

	@Resource
	private TbItemMapper tbItemMapper;
	
	@Override
	public EasyUIDataGridResult getTbItemList(Integer page, Integer rows) {
		//分页插件
		PageHelper.startPage(page, rows);
		TbItemExample example = new TbItemExample();
		List<TbItem> list  = tbItemMapper.selectByExample(example);
		for (int i =0; i<list.size(); i++) {
			System.out.println(list.get(i).toString());
			
		}
		PageInfo<TbItem> pageInfo =new PageInfo<>(list);
		long total = pageInfo.getTotal();
		EasyUIDataGridResult easyUIDataGridResult = new EasyUIDataGridResult(total,list);
		return easyUIDataGridResult;
	}

	

}
