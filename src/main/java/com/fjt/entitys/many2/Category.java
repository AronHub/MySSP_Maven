package com.fjt.entitys.many2;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 双向多对多
 * 
 * 种类实体类
 * @author Administrator
 *
 */
@Table(name="JPA_CATEGORIES")
@Entity
public class Category {

	private Integer id;
	private String categoryName;
	
	private Set<Item> items = new HashSet<Item>();

	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="CATEGORY_NAME")
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
    //mappedBy指定不维护关系。
	//@ManyToMany(mappedBy="categories")的值指的是维护类定义本类的对象名称
	@ManyToMany(mappedBy="categories")
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
}
