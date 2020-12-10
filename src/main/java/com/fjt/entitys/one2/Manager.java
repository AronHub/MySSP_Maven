package com.fjt.entitys.one2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 基于外键的一对一
 * 
 * 管理员实体类
 * @author Administrator
 *
 */
@Table(name="JPA_MANAGERS")
@Entity
@SequenceGenerator(name = "seq_manage",sequenceName = "seq_manage")
public class Manager {

	private Integer id;
	private String mgrName;
	
	private Department dept;

	@GeneratedValue(generator = "seq_manage",strategy = GenerationType.SEQUENCE)
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="MGR_NAME")
	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}

	//对于不维护关联关系, 没有外键的一方, 使用 @OneToOne 来进行映射, 建议设置 mappedBy属性
	//@OneToOne(mappedBy="mgr") 中mappedBy的值是维护类中定义本类的对象名称。
	@OneToOne(mappedBy="mgr")
	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}
}
