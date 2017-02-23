package com.upa.pet_shop.response;

import java.util.List;

import com.upa.pet_shop.constant.ErrorCode;

/**
 * 返回分页数据请求
 */
public class PageResponse<T> extends BaseResponse {

	Long totalElements; // 总元素数
	List<T> pageData; // 页面数据

	public PageResponse(Long totalElements, List<T> data){
		super(ErrorCode.SUCCESS.getErrorId());
		this.totalElements=totalElements;
		this.pageData=data;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public List<T> getPageData() {
		return pageData;
	}

	public void setPageData(List<T> data) {
		this.pageData = data;
	}
	
}
