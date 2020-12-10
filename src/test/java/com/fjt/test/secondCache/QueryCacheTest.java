package com.fjt.test.secondCache;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.ejb.QueryHints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fjt.entitys.Article;
import com.fjt.entitys.manyone.Customer;
import com.fjt.entitys.manyone.Order;
import com.fjt.repository.ArticleRepository;


/**
 * 查询缓存测试类
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class QueryCacheTest {
	
	
		@PersistenceContext
		private EntityManager entityManager;
		
		@Autowired
		private ArticleRepository articleRepository;
	
		
		/**
		 * jpql的查询缓存
		 */
	    //使用 hibernate 的查询缓存. 
		@Test
		public void testQueryCache(){
			String jpql = "FROM Customer c WHERE c.age > ?";
			Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
			
			//占位符的索引是从 1 开始
			query.setParameter(1, 1);
			List<Customer> customers = query.getResultList();
			System.out.println(customers.size());
			
			
			
			query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
			//占位符的索引是从 1 开始
			query.setParameter(1, 1);
			customers = query.getResultList();
			System.out.println(customers.size());
		}
		
		/**
		 * 自定义方法的查询缓存
		 */
		@Test
		public void testQueryCache2(){
			List<Article> list=articleRepository.findArticles("数码宝贝3");
			System.out.println("11="+list.get(0).getTitle());
			List<Article> list2=articleRepository.findArticles("数码宝贝3");
			System.out.println("11="+list.get(0).getTitle());
		}
		
		
		/**
		 *  集合查询缓存：在集合中定义的缓存。
		 */
		@Test
		public void testQueryCache3(){
			Customer ct1=entityManager.find(Customer.class, 500);
			for(Order order1:ct1.getOrders()) {
				System.out.println("order1="+order1.getOrderName());
				break;
			}
			
			Customer ct2=entityManager.find(Customer.class, 500);
			for(Order order1:ct2.getOrders()) {
				System.out.println("order1="+order1.getOrderName());
				break;
			}
			
		}

}
