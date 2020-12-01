package com.fjt.test.jpa;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fjt.entitys.Article;
import com.fjt.entitys.Student;
import com.mysql.jdbc.jdbc2.optional.SuspendableXAConnection;

/**
 * jpa CRUD测试类
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class JpaCRUD {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	/**
	 * 保存操作
	 * 
	 * 类似于 hibernate 的 save 方法. 使对象由临时状态变为持久化状态. 
	 * 和 hibernate 的 save 方法的不同之处: 若对象有 id, 则不能执行 insert 操作, 而会抛出异常. 
	 */
    @Transactional //在测试类中对于事务提交默认是回滚，在业务层就不是默认回滚。
    @Rollback(false) //所以这边取消自动回滚。
    @Test
	public void testPersist() {
		Student st=new Student();
		st.setAddr("上海");
		st.setAge(110);
		st.setBirth(new Date());
		st.setCreateTime(new Date());
		st.setName("私服");
		
		entityManager.persist(st);
		System.out.println("执行保存成功！");
	}
    
    /**
     * 删除操作
     * 
     * 类似于 hibernate 中 Session 的 delete 方法. 把对象对应的记录从数据库中移除
	 * 但注意: 该方法只能移除 持久化 对象. 而 hibernate 的 delete 方法实际上还可以移除 游离对象.
     */
    @Transactional //在测试类中对于事务提交默认是回滚，在业务层就不是默认回滚。
    @Rollback(false) //所以这边取消自动回滚。
    @Test
    public void testDelete() {
        Student st=entityManager.find(Student.class, 250);
    	entityManager.remove(st);
    }
    
    
    
    
    /**
     * 
	 * 总的来说: 类似于 hibernate Session 的 saveOrUpdate 方法.
	 * 1. 若传入的是一个临时对象
	 *会创建一个新的对象, 把临时对象的属性复制到新的对象中, 然后对新的对象执行持久化操作. 所以
	 *新的对象中有 id, 但以前的临时对象中没有 id. 
	 */
	
	@Test
	@Transactional
	@Rollback(false)
	public void testMerge1(){
		Student st=new Student();
		st.setAddr("北京");
		st.setAge(110);
		st.setBirth(new Date());
		st.setCreateTime(new Date());
		st.setName("国服");
		
		Student st2 = entityManager.merge(st);
		
		System.out.println("customer#id:" + st.getId());
		System.out.println("customer2#id:" + st2.getId());
	}
	
	
	
    //若传入的是一个游离对象, 即传入的对象有 OID. 
	//1. 若在 EntityManager 缓存中没有该对象
	//2. 若在数据库中也没有对应的记录
	//3. JPA 会创建一个新的对象, 然后把当前游离对象的属性复制到新创建的对象中
	//4. 对新创建的对象执行 insert 操作. 
	@Test
	@Transactional
	@Rollback(false)
	public void testMerge2(){
		Student st=new Student();
		st.setAddr("上海");
		st.setAge(110);
		st.setBirth(new Date());
		st.setCreateTime(new Date());
		st.setName("私服");
		
		st.setId(100);
		
		Student st2 = entityManager.merge(st);
		
		System.out.println("customer#id:" + st.getId());
		System.out.println("customer2#id:" + st2.getId());
	}

	
	//若传入的是一个游离对象, 即传入的对象有 OID. 
	//1. 若在 EntityManager 缓存中没有该对象
	//2. 若在数据库中有对应的记录
	//3. JPA 会查询对应的记录, 然后返回该记录对一个的对象, 再然后会把游离对象的属性复制到查询到的对象中.
	//4. 对查询到的对象执行 update 操作. 
	@Test
	@Transactional
	@Rollback(false)
	public void testMerge3(){
		Student st=new Student();
		st.setAddr("上海11");	
		st.setBirth(new Date());
		st.setCreateTime(new Date());
		st.setName("私服11");
		
		st.setId(500);
		
		Student st2 = entityManager.merge(st);
		
		System.out.println(st == st2); //false
	}
	
	//若传入的是一个游离对象, 即传入的对象有 OID. 
	//1. 若在 EntityManager 缓存中有对应的对象
	//2. JPA 会把游离对象的属性复制到查询到EntityManager 缓存中的对象中.
	//3. EntityManager 缓存中的对象执行 UPDATE. 
	@Test
	@Transactional
	@Rollback(false)
	public void testMerge4(){
		Student st=new Student();
		st.setAddr("上海55");	
		st.setBirth(new Date());
		st.setAge(20);
		st.setCreateTime(new Date());
		st.setName("私服55");
		
		
		st.setId(500);
		Student st2 = entityManager.find(Student.class, 500);
		
		entityManager.merge(st);
		
		System.out.println(st == st2); //false
	}
	
	
	/**
	 * 更新操作(推荐使用这个方式)
	 * 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testMerge5(){
		
		Student st2 = entityManager.find(Student.class, 500);
		st2.setAge(129);
		//下面方法写不写都一样，都会执行更新操作
		//entityManager.merge(st2);

	}
	
	
	
    
    /**
     * 查询方法1
     * 类似于 hibernate 中 Session 的 get 方法
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testFind() {
    	Student st=entityManager.find(Student.class, 200);
    	System.out.println("-----------------");
    	System.out.println(st);
    }
    
    /**
     * 查询方法2
     * 类似于 hibernate 中 Session 的 load 方法
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testGetReference() {
    	Student st=entityManager.getReference(Student.class, 200);
    	System.out.println("st="+st.getClass().getName());
    	System.out.println("-----------------");
    	System.out.println("st="+st);
    }
    
    
    /**
	 * 同 hibernate 中 Session 的 flush 方法. 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testFlush(){
		Student st = entityManager.find(Student.class, 500);
		System.out.println(st);
		
		st.setAddr("迪拜");
		
		entityManager.flush();
		
		System.out.println("执行完成");
	}
    
	/**
	 * 同 hibernate 中 Session 的 refresh 方法. 
	 */
	@Test
	@Transactional
	@Rollback(false)
	public void testRefresh(){
		Student st = entityManager.find(Student.class, 500);
		st = entityManager.find(Student.class, 500);
		
		entityManager.refresh(st);
	}
    

}
