package org.fhk.service;

import java.util.List;

import org.fhk.utils.EasyUITreeNodeBean;

public interface TbItemCatService {

	List<EasyUITreeNodeBean> getTbItemCatList(Long parendId);
}
