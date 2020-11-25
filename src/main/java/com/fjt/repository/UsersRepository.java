package com.fjt.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.fjt.entitys.User;
import com.fjt.repository.custom.PageCustom;
import com.fjt.repository.custom.UserReporsCustom;

/**
    * 持久层
 * @author 1
 *
 */
//JpaRepository接口提供了分页实现的方法findAll(Pageable pageable)
public interface UsersRepository extends JpaRepository<User, Integer>,UserReporsCustom,PageCustom{

	@Query("select us from User us where us.name=:username and us.pssword=:password ")
	public User findUser(@Param("username")String  username ,@Param("password")String password);
	
	@Query(value="select us from User us")
	public List<User> findAllUser();

	public void contAll(); 
	
	
	
	
	
	
	
	
	
	
//	@Query(value=" select t1.*,rownum rw from (select us from User us)t1 where rownum<=(pageRows*pageNow) ",nativeQuery=true)
//	public List<User> findPage(@Param("pageRows")int pageRows,@Param("pageNow")int pageNow);
    
//	@Query(value="select t1.*,rownum rw from (select us from User us)t1 where rownum<=3",nativeQuery=true)
//	public List<User> findPageUser();

	
}
