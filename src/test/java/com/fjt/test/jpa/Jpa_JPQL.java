package com.fjt.test.jpa;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.ejb.QueryHints;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fjt.entitys.Student;

/**
 * jpa的jpql测试类(待测试)
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class Jpa_JPQL {

	@PersistenceContext
	private EntityManager entityManager;

	//可以使用 JPQL 完成 UPDATE 和 DELETE 操作. 
	@Test
	@Transactional
	@Rollback(false)
	public void testExecuteUpdate(){
		String jpql = "UPDATE Student c SET c.name = ? WHERE c.id = ?";
		
		Query query = entityManager.createQuery(jpql).
				setParameter(1, "大神WR").setParameter(2, 200);
		
		
		query.executeUpdate();
	}
	
	//使用 jpql 内建的函数
	@Test
	public void testJpqlFunction(){
		String jpql = "SELECT lower(c.name) FROM Student c where c.id=:id";
		// 命名查询
		String name = (String) entityManager.createQuery(jpql)
				.setParameter("id", 200).getSingleResult();
		System.out.println(name);
	}
		
	@Test
	public void testSubQuery(){
		//查询所有 Customer 的 lastName 为 YY 的 Order
		String jpql = "SELECT o FROM Student o "
				+ "WHERE o.name = (SELECT name FROM Student c WHERE c.id = ?)";
		// 位置查询
		Query query = entityManager.createQuery(jpql).setParameter(1, 200);
		List<Student> orders = query.getResultList();
		System.out.println(orders.toString());
	}
		
	
	    
	/* *//**
			* JPQL 的关联查询同 HQL 的关联查询. 
			*//*
				@Test
				public void testLeftOuterJoinFetch(){
				String jpql = "FROM Customer c LEFT OUTER JOIN FETCH c.orders WHERE c.id = ?";
				
				Customer customer = 
				(Customer) entityManager.createQuery(jpql).setParameter(1, 12).getSingleResult();
				System.out.println(customer.getLastName());
				System.out.println(customer.getOrders().size());
				
				
				}*/

	// 查询 order 数量大于 2 的那些 Customer
	@Test
	public void testGroupBy() {
		String jpql = "select o.name from Student o " + "group by o.name " + " having count(o.id)>2 ";
		List<String> list = entityManager.createQuery(jpql).getResultList();
		for (String st : list) {
			System.out.println("st=" + st);
		}

	}

	// 排序查询
	@Test
	public void testOrderBy() {
		// 占位符的索引是从 1 开始
		String jpql = "from Student a where a.age> ? order by a.age desc";
		Query query = entityManager.createQuery(jpql);
		query.setParameter(1, 100);
		List<Student> list = query.getResultList();
		System.out.println("list=" + list);
	}

	/*	//使用 hibernate 的查询缓存. 
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
		}*/

	// createNativeQuery 适用于本地 SQL
	@Test
	public void testNativeQuery() {
		String sql = "select age from student where id= ?";
		Query query = entityManager.createNativeQuery(sql);
		query.setParameter(1, 3000);
		try {
			//getSingleResult方法，如果查询不出来数据，会报错
			Object age = query.getSingleResult();
			System.out.println("age=" + age);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	/*	//createNamedQuery 适用于在实体类前使用 @NamedQuery 标记的查询语句
		@Test
		public void testNamedQuery(){
			Query query = entityManager.createNamedQuery("testNamedQuery").setParameter(1, 3);
			Customer customer = (Customer) query.getSingleResult();
			
			System.out.println(customer);
		}*/

	// 默认情况下, 若只查询部分属性, 则将返回 Object[] 类型的结果. 或者 Object[] 类型的 List.
	// 也可以在实体类中创建对应的构造器, 然后再 JPQL 语句中利用对应的构造器返回实体类的对象.
	@Test
	public void testPartlyProperties() {
		/*String jpql = "SELECT new Customer(c.lastName, c.age) FROM Customer c WHERE c.id > ?";
		List result = entityManager.createQuery(jpql).setParameter(1, 1).getResultList();
		
		System.out.println(result);*/
		String jpql = "select new Student(a.name,a.age) from Student a  where a.age> ?";
		List<Student> resList = entityManager.createQuery(jpql).setParameter(1, 100).getResultList();

		System.out.println("resultList=" + resList);

	}

	@Test
	public void testHelloJPQL() {
		String jpql = "from Student s where s.age> ?";
		Query query2 = entityManager.createQuery(jpql);

		// 占位符的索引是从 1 开始
		query2.setParameter(1, 10);
		List<Student> students = query2.getResultList();

		System.out.println("students=" + students.toString());

	}
	
	//jpql分页查询
	@Test
	public void testPageJPQL() {
		String jpql = "from Student s order by s.id ";
		Query query2 = entityManager.createQuery(jpql);
        //起始索引
		query2.setFirstResult(0);
		//每页查询的条数
		query2.setMaxResults(2);
		
		List<Student> students = query2.getResultList();

		System.out.println("students=" + students.toString());

	}

}
