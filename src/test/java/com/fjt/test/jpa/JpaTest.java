package com.fjt.test.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjt.entitys.Article;

/**
 * jpa测试类
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class JpaTest {
	
	@PersistenceContext
	private EntityManager entityManager;
	

	@Test
	public void test() {
		entityManager.find(Article.class, 700);
	}

}
