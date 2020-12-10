package com.fjt.test.relation;
/*
 * 多对一测试类
 */

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fjt.entitys.manyone.Customer;
import com.fjt.entitys.manyone.Order;

/**
 * jpa多对一测试类
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class ManyOneTest {
	
		@PersistenceContext
		private EntityManager entityManager;
		
		
		
		@Test
		@Transactional
		@Rollback(false)
		public void testManyToOneUpdate(){
			Order order = entityManager.find(Order.class, 151);
			order.getCustomer().setLastName("FFF");
		}
			
		//不能直接删除 1 的一端, 因为有外键约束. 
		@Test
		@Transactional
		@Rollback(false)
		public void testManyToOneRemove(){
			Order order = entityManager.find(Order.class, 150);
			entityManager.remove(order);
			
			/*Customer customer = entityManager.find(Customer.class, 150);
			entityManager.remove(customer);*/
		}
	
		
		
	//默认情况下, 使用左外连接的方式来获取 n 的一端的对象和其关联的 1 的一端的对象. 
	//可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	@Test
	@Transactional
	public void testManyToOneFind(){
		Order order = entityManager.find(Order.class, 150);
		System.out.println(order.getOrderName());
		
		System.out.println(order.getCustomer().getLastName());
	}
	
	
	/**
	 * 保存多对一时, 建议先保存 1 的一端, 后保存 n 的一端, 这样不会多出额外的 UPDATE 语句.
	 */
	
	@Test
	@Transactional
	@Rollback(false)
	public void testManyToOnePersist(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("gg@163.com");
		customer.setLastName("GG");
		
		Order order1 = new Order();
		order1.setOrderName("G-GG-1");
		
		Order order2 = new Order();
		order2.setOrderName("G-GG-2");
		
		//设置关联关系
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//执行保存操作
		entityManager.persist(customer);
		entityManager.persist(order1);
		entityManager.persist(order2);
		
		
	}
	

}
