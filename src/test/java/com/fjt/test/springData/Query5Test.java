package com.fjt.test.springData;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fjt.entitys.User;
import com.fjt.repository.ArticleRepository;
import com.fjt.repository.UsersRepository;

/**
 * 
 * @ClassName: 自定义 Repository 接口
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author fujiantao
 * @date 2019年11月25日
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class Query5Test {

	@Autowired
	private UsersRepository usersRepository;

	/**
	* 需求：根据用户 ID 查询数据 */
	@Test
	public void test1() {
		User users = this.usersRepository.findUserById(1);
		System.out.println(users.toString());
	}
}
