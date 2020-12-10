package com.fjt.test.relation;

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
 * jpa一对多测试类
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class OneManyTest {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	//更新
	@Test
	@Transactional
	@Rollback(false)
	public void testOneToManyUpdate(){
		Customer customer = entityManager.find(Customer.class, 400);
		
		customer.getOrders().iterator().next().setOrderName("O-XXX-10");
	}
	
	//默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行删除. 
	//可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略. 
	@Test
	@Transactional
	@Rollback(false)
	public void testOneToManyRemove(){
		Customer customer = entityManager.find(Customer.class, 450);
		entityManager.remove(customer);
	}
	
	//默认对关联的多的一方使用懒加载的加载策略. 
	//可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	@Test
	@Transactional
	public void testOneToManyFind(){
		Customer customer = entityManager.find(Customer.class, 400);
		System.out.println(customer.getLastName());
		
		System.out.println(customer.getOrders().size());
	}
	
	//若是双向 1-n 的关联关系, 执行保存时
	//若先保存 n 的一端, 再保存 1 的一端, 默认情况下, 会多出 n 条 UPDATE 语句.
	//若先保存 1 的一端, 则会多出 n 条 UPDATE 语句
	
	//在进行双向 1-n 关联关系时, 建议使用 n 的一方来维护关联关系, 而 1 的一方不维护关联系, 这样会有效的减少 SQL 语句. 
	//注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了. 
	
	//单向 1-n 关联关系执行保存时, 一定会多出 UPDATE 语句.
	//因为 n 的一端在插入时不会同时插入外键列. 
	@Test
	@Transactional
	@Rollback(false)
	public void testOneToManyPersist(){
		Customer customer = new Customer();
		customer.setAge(18);
		customer.setBirth(new Date());
		customer.setCreatedTime(new Date());
		customer.setEmail("mm@163.com");
		customer.setLastName("MM");
		
		Order order1 = new Order();
		order1.setOrderName("O-MM-1");
		
		Order order2 = new Order();
		order2.setOrderName("O-MM-2");
		
		//建立关联关系
		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		
		order1.setCustomer(customer);
		order2.setCustomer(customer);
		
		//执行保存操作
		entityManager.persist(customer);
		
		entityManager.persist(order1);
		entityManager.persist(order2);
		
		
	   
	}
	

}
