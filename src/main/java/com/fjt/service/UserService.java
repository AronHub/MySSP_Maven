package com.fjt.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.fjt.entitys.User;



public interface UserService {
	//报表上传
	public void reportUp(MultipartFile file);
	//根据用户名查询
	public User findUser(String  username,String password);
	
	//根据id查询用户
	public User finOne(int id);
	
	
	//自定义的分页查询实现
	public Map<String,Object> findPage2(Pageable pageable,String userName,String telp);
	
	
	//查询所有用户
	public List<User> findAllUser();
	
	//添加
	public void save(User user);
	
	//删除
	public void delet(int id);
	
	
}
