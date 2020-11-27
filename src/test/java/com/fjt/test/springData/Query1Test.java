package com.fjt.test.springData;

import java.util.ArrayList;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjt.entitys.Article;
import com.fjt.repository.ArticleRepository;


/**
 * 
     * @ClassName: 使用父接口方法查询 (推荐使用)
     * @Description: TODO(这里用一句话描述这个类的作用)
     * @author fujiantao
     * @date 2019年11月29日
     *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class Query1Test {

	@Autowired
	private ArticleRepository articleRepository;

	//根据主键查询
	@Test
	public void testFindId() {
		//根据一个主键查询
		Article article=articleRepository.findOne(652);
		System.out.println(article.toString());

		
		//根据多个主键查询
		List<Integer> list = new ArrayList<>();
		list.add(652);
		list.add(653);
		List<Article> articles = articleRepository.findAll(list);
		for (Article article2 : articles) {
			System.out.println(article2);
		}
	}
	
	
	//查询所有
	@Test
	public void testFindAll() {
		List<Article> articles = articleRepository.findAll();
		for (Article article : articles) {
			System.out.println(article);
		}
	}

	//查询所有--单列排序
	@Test
	public void testFindAllWithSort() {
		//按照aid倒序排列
		//方式一
		//Sort sort = Sort.by(Sort.Order.desc("aid"));

		//方式二
		//Sort：该对象封装了排序规则以及指定的排序字段（这边的字段指的是对象的属性）
		//derection:排序规则
		//properties:指定做排序的对象属性。
		Sort sort = new Sort(Direction.DESC, "aid");//aid这边是对象的属性名称

		List<Article> articles = articleRepository.findAll(sort);
		
		for (Article article : articles) {
			System.out.println(article);
		}
	}

	//查询所有--多列排序
	@Test
	public void testFindAllWithSort2() {
		Order order = new Order(Direction.DESC, "author");
		Order order2 = new Order(Direction.ASC, "aid");
 
	    //安装order先排序，然后是order2排序
		Sort sort=new Sort(order,order2);
		
	    //Sort sort = Sort.by(order, order2);

	    List<Article> articles = articleRepository.findAll(sort); 
	    for (Article article : articles) { 
	    	
	    	System.out.println(article);
	    	
	    }
		 
	}

	//查询所有--分页
	@Test
	public void testFindAllWithPage() {
		//处理分页条件
		//page   当前是第几页(从0开始)    size  每页大小
		//Pageable pageable = PageRequest.of(0, 2);
		
		//下面这种写法也行
		Pageable pageable2 = new PageRequest(0, 2);

		Page<Article> page = articleRepository.findAll(pageable2);

		//总记录数  总页数  每页多少
		System.out.println("总记录数:" + page.getTotalElements());
		System.out.println("总页数:" + page.getTotalPages());
		System.out.println("每页多少:" + page.getSize());
		//当前页的元素
		List<Article> content = page.getContent();
		for (Article article : content) {
			System.out.println(article);
		}
	}

	//查询所有--分页+排序
	@Test
	public void testFindAllWithPageAndPage() {

		//按照aid倒序排列
		//Sort sort = Sort.by(Sort.Order.desc("aid"));
		Sort sort =new Sort(Direction.DESC, "aid");

		//处理分页条件
		//page   当前是第几页(从0开始)    size  每页大小
		Pageable pageable =new  PageRequest(0, 2, sort);
		Page<Article> page = articleRepository.findAll(pageable);

		//总记录数  总页数  每页多少
		System.out.println("总记录数:" + page.getTotalElements());
		System.out.println("总页数:" + page.getTotalPages());
		System.out.println("每页多少:" + page.getSize());
		//当前页的元素
		List<Article> content = page.getContent();
		for (Article article : content) {
			System.out.println(article);
		}
	
		
		
		}


	
}
