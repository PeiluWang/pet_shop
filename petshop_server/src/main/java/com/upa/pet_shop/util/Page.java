package com.upa.pet_shop.util;

import java.util.List;

/**
 * 分页数据
 */
public class Page {
	
	long totalElements;
	List pageData;
	
	public Page(long totalElements, List pageData) {
		super();
		this.totalElements = totalElements;
		this.pageData = pageData;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public List getPageData() {
		return pageData;
	}
	public void setPageData(List pageData) {
		this.pageData = pageData;
	}
	
}
