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
 * 这是增删改的详细测试操作
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class SpringDataJpaCUDTest {

	@Autowired
	private ArticleRepository articleRepository;

	//保存一个
	@Test
	public void testSave() {
		Article article=new Article();
		article.setAuthor("天神兽3");
		article.setCreateTime(new Date());
		article.setTitle("数码宝贝3");
		articleRepository.save(article);
	}

	//保存多个
	@Test
	public void testSaveALL() {
		Article article=new Article();
		article.setAuthor("天神兽");
		article.setCreateTime(new Date());
		article.setTitle("数码宝贝");
		
		Article article2=new Article();
		article2.setAuthor("暴龙兽");
		article2.setCreateTime(new Date());
		article2.setTitle("数码宝贝");
		
		List<Article> articles=new ArrayList<Article>();
		articles.add(article);
		articles.add(article2);
		
		articleRepository.save(articles);
		
	}
	

	//删除一个
	@Test
	public void testDelete() {
		Article article=new Article();
		article.setAid(250);
		articleRepository.delete(article);		
	}
	
	//删除所有
	@Test
	public void testDeleteALL() {
		//一条语句搞定删除
//		articleRepository.deleteAllInBatch();
		//先执行查询语句，然后执行一个一个的删除语句
		articleRepository.deleteAll();
	}
	

	
}
