package com.upa.pet_shop.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

public class CriteriaParam {

	private List<Criterion> criterions;
	private List<Order> orders;
	private int pageId;
	private int pageSize;
	
	public CriteriaParam(){
		criterions = new ArrayList<Criterion>();
		orders = new ArrayList<Order>();
		pageId = 0;
		pageSize = 0;
	}
	
	public CriteriaParam addCriterion(Criterion criterion){
		criterions.add(criterion);
		return this;
	}
	
	public CriteriaParam addOrder(Order order){
		orders.add(order);
		return this;
	}
	
	public List<Criterion> getCriterions(){
		return criterions;
	}
	
	public List<Order> getOrders(){
		return orders;
	}
	
	public CriteriaParam setPageId(int pageId){
		this.pageId = pageId;
		return this;
	}
	
	public CriteriaParam setPageSize(int pageSize){
		this.pageSize = pageSize;
		return this;
	}
	
	/**
	 * 有分页参数
	 * @return
	 */
	public boolean hasPageParam(){
		if(pageSize==0){
			return false;
		}
		return true;
	}
	
	public int getPageId(){
		return pageId;
	}
	
	public int getPageSize(){
		return pageSize;
	}
	
}
