package com.fjt.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import com.fjt.entitys.Article;


//自定义接口需要继承JpaRepository<实体类的类型,实体类中的主键的类型>, JpaSpecificationExecutor<实体类的类型>
//JpaSpecificationExecutor<Article>不能单独使用，需要配合spring data jpa中的其他接口一起使用。
public interface ArticleRepository extends JpaRepository<Article, Integer>,
		JpaSpecificationExecutor<Article> {

	//--------------------------根据名称查询----------------------------------
	
	//根据标题查询
	List<Article> findByTitle(String title);
	
	//根据标题模糊查询
	List<Article> findByTitleLike(String title);
	
	//根据标题和作者查询
	List<Article> findByTitleAndAuthor(String title, String author);
	
	//根据ID范围查询 > < between in
	List<Article> findByAidIsLessThan(Integer aid);
	
	List<Article> findByAidBetween(Integer startAid, Integer endAid);
	
	List<Article> findByAidIn(List<Integer> aids);
	
	//根据创建时间之后查询
	List<Article> findByCreateTimeAfter(Date createTime);
	
	
	//--------------------------根据hpql查询----------------------------------
	
	  //@Query里的语句是jpql语句 //JPQL:类似于SQL语句,但是要使用实体类名代替表名,使用属性名代替字段名[面向对象查询]
	  //展示位置参数绑定[按照title和author查询] //占位符从1开始	  
	  @Query("from Article a where a.title = ?1 and a.author =?2") 
	  List<Article> findByCondition1(String title, String author);
	  
	  //展示名字参数绑定（@Param 里的名称 和:title 的名称一致）	  
	  @Query("from Article a where a.title = :title and a.author = :authors")
	  List<Article> findByCondition2(@Param("title") String title,
			  @Param("authors") String author);
	  
	  //展示like模糊查询	  
	  @Query("from Article a where a.title like %:title%") List<Article>
	  findByCondition3(@Param("title") String title);
	  
	  //展示排序查询	  
	  @Query("from Article a where a.title like %:title% order by a.aid desc ")
	  List<Article> findByCondition4(@Param("title") String title);
	  
	  //展示分页查询	  		
	  @Query("from Article a where a.title like %:title%") 
	  List<Article>  findByCondition5(@Param("title") String title,Pageable pageable);
		 
	  
	  //展示传入集合参数查询	  
	  @Query("from Article a where a.aid in :aids") 
	  List<Article> findByCondition6(@Param("aids") List<Integer> aids);
	  
	  //展示传入Bean进行查询（SPEL表达式查询）	  
//	  @Query("from Article a where a.title = :#{#article.title} and a.author = :#{#article.author}") 
//	  List<Article> findByCondition7(@Param("article") Article article);
		 
	  
	  //本地SQL查询（新增nativeQuery = true 就可以使用本地sql查询  这边value 写的是sql语句）  
	  @Query(value = "select * from article a where a.title = ?1 and a.author =?2",nativeQuery = true) 
	  List<Article> findByCondition8(String title, String author);
	 

	//@Modifying 当前语句是一个更新语句
	/*@Query("update Article set author = ? where aid = ?")
	@Modifying
	void updateArticleauthorByAid(String author, Integer aid);*/
	  
	  //------------------------查询缓存   -----------------------------
	  
	  /**
	   * 注意这边查询缓存除了要加上 @QueryHints注解，还得在Article类上加上@Cacheable(true)
	   * @param title
	   * @return
	   */
	  @Query("from Article a where a.title =?1")
	  @QueryHints({ @QueryHint(name = "org.hibernate.cacheable", value ="true") })	 
	  List<Article>  findArticles(String title); 
	  
	  

}
