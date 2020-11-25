package com.fjt.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjt.repository.ArticleRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class SpringDataJpaCUDTest {

	@Autowired
	private ArticleRepository articleRepository;

	//保存
	

	//保存多个
	

	//删除
	

	
}
