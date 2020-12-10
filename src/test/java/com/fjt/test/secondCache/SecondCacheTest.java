package com.fjt.test.secondCache;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjt.entitys.manyone.Customer;


/*
 * spring data jpa二级缓存测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class SecondCacheTest {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void testSecondLevelCache(){
		Customer customer1 = entityManager.find(Customer.class, 500);
		System.out.println("customer1="+customer1.getAge());
		Customer customer2 = entityManager.find(Customer.class, 500);
		System.out.println("customer2="+customer2.getAge());

	}

}
