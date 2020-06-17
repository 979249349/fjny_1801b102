package org.fhk.utils;

import java.util.List;

/**
 * 1
 * @author Administrator
 *easyUIGrid 要求数据格式
 */
public class EasyUIDataGridResult {
	private long total;
	private List<?> rows;
	public EasyUIDataGridResult() {
	}
	public EasyUIDataGridResult(long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
