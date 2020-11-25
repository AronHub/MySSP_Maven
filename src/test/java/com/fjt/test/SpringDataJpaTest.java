package com.fjt.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fjt.entitys.Article;
import com.fjt.repository.ArticleRepository;
import com.fjt.repository.UsersRepository;


/**
 * spring 的测试类
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class SpringDataJpaTest {

	@Autowired
	private ArticleRepository articleRepository;

	
	  //保存操作	  
	  @Test 
	  public void testSave() { 
		Article atArticle=new Article();
		atArticle.setAuthor("涛涛");
		atArticle.setCreateTime(new Date());
		atArticle.setTitle("这是一个很吊的人");
		//保存
		articleRepository.save(atArticle);
		
	  }
	  //删除操作方式一
	  @Test
	  public void testDelet1() {
		  articleRepository.delete(250); 
	  }
	  //删除操作方式二
	  @Test
	  public void testDelet2() {
		  Article article=articleRepository.findOne(250);
		  articleRepository.delete(article);	  
	  }
	  
	  //修改方式一(推荐使用)
	  @Test 
	  public void testUpdate1() {
		  //先查询出对应的记录
		  Article article=articleRepository.findOne(250);
		  article.setAuthor("双子");
		  //Spingdata Jpa的保存和修改使用的都是save方法
	      //关键来看传入的实体是否有主键
		  //---如果有主键,代表要修改
		  //---如果没有主键,代表要保存
		  articleRepository.save(article);
	  }
	  
	  //修改方式二(推荐使用，这种方式需要使用事务)
	  @Transactional //在测试类中对于事务提交默认是回滚，在业务层就不是默认回滚。
	  @Rollback(false) //所以这边取消自动回滚。
	  @Test 
	  public void testUpdate2() {
		  //先查询出对应的记录
		  Article article=articleRepository.findOne(250);
		  article.setAuthor("韦小宝");
		  
	  }
	  
	  ////修改方式三(不推荐使用)
	  @Test 
	  public void testUpdate3() {
		  Article article=new Article();
		  article.setAid(50);
		  article.setAuthor("哈哈");
		  articleRepository.save(article);
		  
	  }
	  
	  //根据主键查询
	  @Test
	  public void testGetById() {
		  Article article=articleRepository.findOne(50);
		  System.out.println("article="+article);
	  }
	  
	  //查询所有
	  @Test
	  public void testGetAll() {
		  List<Article> articles=new ArrayList<Article>();
		  articles=articleRepository.findAll();
		  System.out.println("articles="+articles.toString());
		  
	  }
	  
	 

	
}
