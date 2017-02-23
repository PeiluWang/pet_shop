package com.upa.pet_shop.request;

import java.util.List;

/**
 * 分页数据请求
 */
public class PageRequest {
	
	// 页面id，由1开始计数
	int pageId;
	
	// 页面数据数目
	int pageSize;
	
	// 排序选项
	List<OrderParam> orderParams;
	
	//搜索名称
	String searchName;
	

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<OrderParam> getOrderParams() {
		return orderParams;
	}

	public void setOrderParams(List<OrderParam> orderParams) {
		this.orderParams = orderParams;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}
