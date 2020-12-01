package com.fjt.entitys;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

/**
 * jpa的测试实体类
 * @author Administrator
 *
 */
@Entity
@Table(name = "student")
public class Student {
	
	public Student() {
		
	}
	
	
	public Student(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	@Id
	@SequenceGenerator(name = "seq_st",sequenceName = "student_ID_SEQ")
	@GeneratedValue(generator = "seq_st",strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	/**
	 * @Basic 基本注解，不写就是默认有@Basic注解
	 */
	@Basic
	@Column(name = "name",length = 50,nullable = false)
	private String name;
	private String addr;
	/**
	 * @Temporal 精确时间
	 * TemporalType.DATE： 精确到年月日
	 * TemporalType.TIMESTAMP：精确到时分秒
	 */
	@Temporal(TemporalType.DATE)
	private Date birth;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	private Integer age;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Override
	public String toString() {
		return "Student [name=" + name + ", addr=" + addr + "]";
	}
	
	//工具方法. 不需要映射为数据表的一列. 加上@Transient就可以让这个方法不映射成数据库表的一列
	@Transient
	public String getInfo(){
		return "name: " + name + ", addr: " + addr;
	}
}
