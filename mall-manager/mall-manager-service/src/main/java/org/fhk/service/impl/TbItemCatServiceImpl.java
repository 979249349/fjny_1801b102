package org.fhk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.fhk.mapper.TbItemCatMapper;
import org.fhk.pojo.TbItem;
import org.fhk.pojo.TbItemCat;
import org.fhk.pojo.TbItemCatExample;
import org.fhk.pojo.TbItemCatExample.Criteria;
import org.fhk.service.TbItemCatService;
import org.fhk.service.TbItemService;
import org.fhk.utils.EasyUIDataGridResult;
import org.fhk.utils.EasyUITreeNodeBean;
import org.fhk.utils.FjnyResult;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {

	
	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Override
	public List<EasyUITreeNodeBean> getTbItemCatList(Long parendId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parendId);
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		List<EasyUITreeNodeBean> easyUiTreeNodeBeans = new ArrayList<>();
		for (TbItemCat tbItemCat : list) {
			EasyUITreeNodeBean e = new EasyUITreeNodeBean();
			e.setId(tbItemCat.getId());
			e.setText(tbItemCat.getName());
			/*Boolean isParent = tbItemCat.getIsParent();
			if(isParent) {
				e.setState("closed");
			}else {
				e.setState("open");
			}*/
			e.setState(tbItemCat.getIsParent()?"closed":"open");
			easyUiTreeNodeBeans.add(e);
		}
		return easyUiTreeNodeBeans;
	}
	

}
