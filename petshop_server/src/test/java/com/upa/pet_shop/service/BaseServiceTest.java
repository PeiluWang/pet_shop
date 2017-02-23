package com.upa.pet_shop.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.upa.pet_shop.Application;
import com.upa.pet_shop.entity.User;
import com.upa.pet_shop.util.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class BaseServiceTest {

	@Autowired
	BaseService baseService;
	
	@Test
	@Transactional
	public void testExecuteQuerySql(){
		String sql = "select * from user where id = :userId";
		SqlParams params = new SqlParams().setParam("userId", 1);
		List<Object[]> result = baseService.executeQuerySql(sql, params.getParams());
		System.out.println(result.size());
	}
	
	@Test
	@Transactional
	public void testExecuteUpdateSql(){
		String sql = "update user set user_name = :userName where id = :userId";
		SqlParams params = new SqlParams()
							.setParam("userName", "Jackson")
							.setParam("userId", 1);
		boolean result = baseService.executeUpdateSql(sql, params.getParams());
		System.out.println(result);
	}
	
	@Test
	@Transactional
	public void testCriteriaQuery(){
		CriteriaParam param = new CriteriaParam()
		.addCriterion(Restrictions.eq("id", 1L))
		.addCriterion(Restrictions.le("createTime", new Date()))
		.addOrder(Order.asc("id"))
		.setPageId(0)
		.setPageSize(10);
		Page result = baseService.criteriaQuery(User.class, param);
		System.out.println(result.getTotalElements());
		User user = (User)result.getPageData().get(0);
		System.out.println(user.getUserName());
	}
	
}
