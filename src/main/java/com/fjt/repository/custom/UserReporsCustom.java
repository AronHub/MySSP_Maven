package com.fjt.repository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fjt.entitys.User;

/**
 * 这边是自定义方法的接口
 * 
 * 有些方法的功能需要自己去实现，所以需要把方法抽取出来，放在自定义方法的接口上，
 * 然后在创建一个实现类（UserReposImpl），在UserReposImpl实现自己想要实现的功能
 * @author posdev
 *
 */
public interface UserReporsCustom {
    
	public User findUserById(Integer userid);
	
	public Page<User> HiberPage(Pageable pageable, String userName,
			String telp);
}
