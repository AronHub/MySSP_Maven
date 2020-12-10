package com.fjt.entitys.one2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * 基于外键的一对一
 * 
 * 部门实体类
 * 
 * @author Administrator
 *
 */
@Table(name="JPA_DEPARTMENTS")
@Entity
@SequenceGenerator(name = "seq_department",sequenceName = "seq_department")
public class Department {

	private Integer id;
	private String deptName;
	
	private Manager mgr;

	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_department")
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="DEPT_NAME")
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	//使用 @OneToOne 来映射 1-1 关联关系。
	//若需要在当前数据表中添加主键则需要使用 @JoinColumn 来进行映射. 注意, 1-1 关联关系, 所以需要添加 unique=true
	//@JoinColumn(name="MGR_ID", unique=true)中的name是数据库表中的外键名称
	@JoinColumn(name="MGR_ID", unique=true)
	@OneToOne(fetch=FetchType.LAZY)
	public Manager getMgr() {
		return mgr;
	}

	public void setMgr(Manager mgr) {
		this.mgr = mgr;
	}
}
