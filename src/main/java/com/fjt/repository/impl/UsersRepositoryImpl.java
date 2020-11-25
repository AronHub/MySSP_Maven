package com.fjt.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.fjt.entitys.User;
import com.fjt.repository.custom.PageCustom;
import com.fjt.repository.custom.UserReporsCustom;
import com.fjt.service.UserService;

/**
 * 这边是自定义接口的实现类，这边主要实现类的名称（前缀是：UsersRepository）
 * @author posdev
 *
 */
public class UsersRepositoryImpl implements UserReporsCustom,PageCustom{

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Autowired
	private UserService userservice;
	
	@Override
	public void contAll() {
		// TODO Auto-generated method stub
		System.out.println("这边是操作数据库的代码");
	}

	@Override
	public Page<User> HiberPage(Pageable pageable, String userName,
			String telp) {
		Map<String,Object> param=new HashMap<String, Object>();
		String jpql="select us  from User us where 1=1 ";
		if(userName!=null&&userName!=""){
			jpql+="and us.name like :userName";
			param.put("userName","%"+userName+"%");
		}
		if(telp!=null&&telp!=""){
			jpql+=" and us.telep like :telp";
			param.put("telp","%"+telp+"%"); 
		}
		
		// TODO Auto-generated method stub
	    Query query=entitymanager.createQuery(jpql);
	    //setFirstResult表示从第几条开始。
		query.setFirstResult(pageable.getOffset());
		//setMaxResults表示取几条记录。
		query.setMaxResults(pageable.getPageSize());
		
		for(Map.Entry<String,Object> entry:param.entrySet()){
			String key=entry.getKey();
			Object value=entry.getValue();
			//System.out.println("key="+key+"value="+value);
			query.setParameter(key, value);
		}
		@SuppressWarnings("unchecked")
	    List<User> content=query.getResultList();
		List<User> users=userservice.findAllUser();
		int total=users.size();
	    Page<User> pge=new PageImpl<User>(content,pageable,total); 
		return pge;
	}

}
