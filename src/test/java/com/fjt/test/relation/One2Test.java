package com.fjt.test.relation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fjt.entitys.one2.Department;
import com.fjt.entitys.one2.Manager;


/**
 * jpa双向一对一测试类（基于外键的一对一）
 * 
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class One2Test {
	
		@PersistenceContext
		private EntityManager entityManager;
	
	    //1. 默认情况下, 若获取不维护关联关系的一方, 则也会通过左外连接获取其关联的对象. 
		//可以通过 @OneToOne 的 fetch 属性来修改加载策略. 但依然会再发送 SQL 语句来初始化其关联的对象
		//这说明在不维护关联关系的一方, 不建议修改 fetch 属性. 
		@Test
		@Transactional
		public void testOneToOneFind2(){
			Manager mgr = entityManager.find(Manager.class, 100);
			System.out.println(mgr.getMgrName());
			
			System.out.println(mgr.getDept().getDeptName());
		}
		
		//1.默认情况下, 若获取维护关联关系的一方, 则会通过左外连接获取其关联的对象. 
		//但可以通过 @OntToOne 的 fetch 属性来修改加载策略.
		@Test
		@Transactional
		public void testOneToOneFind(){
			Department dept = entityManager.find(Department.class, 250);
			System.out.println(dept.getDeptName());
			System.out.println(dept.getMgr().getMgrName());
	
		}
		
		//双向 1-1 的关联关系, 建议先保存不维护关联关系的一方, 即没有外键的一方, 这样不会多出 UPDATE 语句.
		@Test
		@Transactional
		@Rollback(false)
		public void testOneToOnePersistence(){
			Manager mgr = new Manager();
			mgr.setMgrName("K-BB");
			
			Department dept = new Department();
			dept.setDeptName("K-BB");
			
			//设置关联关系（双向的，在维护端类中保存相应对象就可以）
			//mgr.setDept(dept);  //这个可加可不加
			dept.setMgr(mgr);
			
			//执行保存操作
			entityManager.persist(mgr);
			entityManager.persist(dept);
		}
}
