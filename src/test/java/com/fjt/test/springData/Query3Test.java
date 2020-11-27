package com.fjt.test.springData;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjt.entitys.Article;
import com.fjt.repository.ArticleRepository;



/**
 * 
     * @ClassName: JPQL查询 (使用的比较少，了解)
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年11月29日
     *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class Query3Test {

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	public void testFindByCondition1() {
		List<Article> articles = articleRepository.findByCondition1("数码宝贝3", "天神兽");
		for (Article article : articles) {
			System.out.println(article);
		}
	}

	@Test
	public void testFindByCondition2() {
		List<Article> articles = articleRepository.findByCondition2("数码宝贝3", "天神兽");
		for (Article article : articles) {
			System.out.println(article);
		}
	}

	@Test
	public void testFindByCondition4() {
		List<Article> articles = articleRepository.findByCondition4("数码宝贝");
		for (Article article : articles) {
			System.out.println(article);
		}
	}

	@Test
	public void testFindByCondition5() {
		//Pageable pageable = PageRequest.of(0, 3);
		
		  Pageable pageable=new PageRequest(0, 1); 
		  List<Article> articles = articleRepository.
				  findByCondition5("数码宝贝",pageable); 
		  for (Article article :articles) { 
			  System.out.println(article); 
			  
		  }
		 
	}

	@Test
	public void testFindByCondition6() {
		List<Integer> list = new ArrayList<>();
		list.add(29);
		list.add(30);

		List<Article> articles = articleRepository.findByCondition6(list);
		for (Article article : articles) {
			System.out.println(article);
		}
	}

	@Test
	public void testFindByCondition7() {
		Article articleParam = new Article();
		articleParam.setTitle("数码宝贝");
		articleParam.setAuthor("暴龙兽");
		//spring data jpa 1.5.2 使用SPEL表达式查询有问题
		/*
		 * List<Article> articles = articleRepository.findByCondition7(articleParam);
		 * for (Article article : articles) { System.out.println(article); }
		 */
	}

	@Test
	public void testFindByCondition8() {
		List<Article> articles = articleRepository.findByCondition8("数码宝贝", "暴龙兽");
		for (Article article : articles) {
			System.out.println(article);
		}
	}

}
