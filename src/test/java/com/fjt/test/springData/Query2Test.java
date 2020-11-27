package com.fjt.test.springData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjt.entitys.Article;
import com.fjt.repository.ArticleRepository;

/**
 * 
     * @ClassName: 方法命名规则查询 （不常用，了解）
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年11月29日
     *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class Query2Test {

	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Test
	public void testFindByTitle() {
	    List<Article> articles = articleRepository.findByTitle("数码宝贝");
	    for (Article article : articles) {
	        System.out.println(article);
	    }
	}
	
	@Test
	public void testFindByTitleLike() {
	    List<Article> articles = articleRepository.findByTitleLike("%数码宝贝%");
	    for (Article article : articles) {
	        System.out.println(article);
	    }
	}
	
	@Test
	public void testFindByTitleAndAuthor() {
	    List<Article> articles = articleRepository.findByTitleAndAuthor("数码宝贝", "暴龙兽");
	    for (Article article : articles) {
	        System.out.println(article);
	    }
	}
	
	@Test
	public void testFindByAidIsLessThan() {
	    List<Article> articles = articleRepository.findByAidIsLessThan(700);
	    for (Article article : articles) {
	        System.out.println(article);
	    }
	}
	
	@Test
	public void testFindByAidBetween() {
	    List<Article> articles = articleRepository.findByAidBetween(652, 700);
	    for (Article article : articles) {
	        System.out.println(article);
	    }
	}
	
	@Test
	public void testFindByAidIn() {
	    ArrayList<Integer> list = new ArrayList<>();
	    list.add(29);
	    list.add(30);
	    List<Article> articles = articleRepository.findByAidIn(list);
	    for (Article article : articles) {
	        System.out.println(article);
	    }
	}
	
	@Test
	public void testFindByCreateTimeAfter() {
	    List<Article> articles = articleRepository.findByCreateTimeAfter(new Date());
	    for (Article article : articles) {
	        System.out.println(article);
	    }
	}
	

}
