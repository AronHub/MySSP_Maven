package com.fjt.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import com.fjt.entitys.User;

import com.fjt.repository.custom.UserReporsCustom;
import com.fjt.service.UserService;

/**
 * 这边是自定义接口的实现类，
 * 
 * （自定义Repository接口实现类名字：接口名+Impl ，注意这边实现类不能写成UserReporsCustomImpl 否则会报错）
 * (实现类写的其实就是jpa的技术)
 * @author posdev
 *
 */
public class UsersRepositoryImpl implements UserReporsCustom{

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Autowired
	private UserService userservice;
	
	
	/**
	 * jpa分页查询方法
	 */
	@Override
	public Page<User> HiberPage(Pageable pageable, String userName,
			String telp) {
		Map<String,Object> param=new HashMap<String, Object>();
		String jpql="select us  from User us where 1=1 ";
		String jpql_count="select count(id) from User where 1=1 ";
		
		if(StringUtils.isNotBlank(userName)){
			jpql+="and us.name like :userName";
			jpql_count+="and us.name like :userName";
		}
		if(StringUtils.isNotBlank(telp)){
			jpql+=" and us.telep like :telp";
			jpql_count+=" and us.telep like :telp";		 
		}
		
	    Query query=entitymanager.createQuery(jpql);
	    Query query_count=entitymanager.createQuery(jpql_count);
	     
	    if(StringUtils.isNotBlank(userName)){
	    	query.setParameter("userName", "%"+userName+"%");
	    	query_count.setParameter("userName", "%"+userName+"%");
		}
		if(StringUtils.isNotBlank(telp)){
			query.setParameter("telp", "%"+telp+"%");
	    	query_count.setParameter("telp", "%"+telp+"%");
		}
	    
		System.out.println("pageable.getOffset()="+pageable.getOffset());
		System.out.println("pageable.getPageSize()="+pageable.getPageSize());
	    //setFirstResult表示从第几条开始。
		query.setFirstResult(pageable.getOffset());
		//setMaxResults表示取几条记录。
		query.setMaxResults(pageable.getPageSize());
		
	
	    Long total=(Long) query_count.getSingleResult();
	    List<User> content=query.getResultList();
	    Page<User> page=new PageImpl<User>(content,pageable,total); 
		return page;
	}

	@Override
	public User findUserById(Integer userid) {
		// TODO Auto-generated method stub
		return this.entitymanager.find(User.class, userid);
	}

}
