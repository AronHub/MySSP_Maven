package com.fjt.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import com.fjt.entitys.User;
import com.fjt.repository.UsersRepository;
import com.fjt.service.UserService;
import com.fjt.util.UserReporUp;

/**
    *       用户表的业务层
 * @author 1
 *
 */
@Service
@Transactional
public class UserSeviceImpl implements UserService{

	@Autowired
	private UsersRepository userRepos;
	
	@Override
	public User findUser(String  username,String password) {
		// TODO Auto-generated method stub
		return userRepos.findUser(username,password);
	}

	@Override
	
	public void save(User user) {
		// TODO Auto-generated method stub
		userRepos.save(user);
	}

	

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userRepos.findAllUser();
	}

	

	@Override
	public Map<String, Object> findPage2(Pageable pageable, String userName,
			String telp) {
		// TODO Auto-generated method stub
	    
		Page<User> page=userRepos.HiberPage( pageable,  userName,
				 telp);
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("content",page.getContent());
		result.put("total", page.getTotalElements());
		
		return result;
	}

	@Override
	public void delet(int id) {
		// TODO Auto-generated method stub
		userRepos.delete(id);
	}

	@Override
	public User finOne(int id) {
		// TODO Auto-generated method stub
		return userRepos.findOne(id);
	}

	@Override
	public void reportUp(MultipartFile file) {
		// TODO Auto-generated method stub
		UserReporUp userReporUp=new UserReporUp();
		List<User> users=userReporUp.exreport(file);
	
	    if(users!=null){
	    	
	    	for(User user:users){
	    		this.save(user);
	    	}
	    }
	    
		
	}

	



}
