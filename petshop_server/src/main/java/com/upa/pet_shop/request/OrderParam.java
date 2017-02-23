package com.upa.pet_shop.request;

/**
 * 排序参数
 */
public class OrderParam{
	//排序对象
	String name; 
	// 升序or降序标志，
	int order;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrder() {
		return order;
	}
	public void setOrder(int order) {
		this.order = order;
	}
	
	
}