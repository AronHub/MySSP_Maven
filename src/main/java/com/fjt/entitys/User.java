package com.fjt.entitys;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 实体类（与users表对应）
 * @author 1
 *
 */
@Entity
@Table(name="users")
public class User implements  Serializable{
	
	private static final long serialVersionUID = 1L;
	 
    @Id
    @GeneratedValue
    @Column(name="usr_id")
	private int id;
	private String name;
	private String pssword;
	private String telep;
	private String addr;
	
	public String getPssword() {
		return pssword;
	}
	public void setPssword(String pssword) {
		this.pssword = pssword;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelep() {
		return telep;
	}
	public void setTelep(String telep) {
		this.telep = telep;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", pssword=" + pssword + ", telep=" + telep + ", addr=" + addr
				+ "]";
	}
	
	
	
}
