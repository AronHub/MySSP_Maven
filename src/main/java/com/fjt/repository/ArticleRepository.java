package com.fjt.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fjt.entitys.Article;


//自定义接口需要继承JpaRepository<实体类的类型,实体类中的主键的类型>, JpaSpecificationExecutor<实体类的类型>
//JpaSpecificationExecutor<Article>不能单独使用，需要配合spring data jpa中的其他接口一起使用。
public interface ArticleRepository extends JpaRepository<Article, Integer>,
		JpaSpecificationExecutor<Article> {

	

}
