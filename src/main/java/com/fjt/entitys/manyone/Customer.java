package com.fjt.entitys.manyone;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * @Cacheable 二级缓存注解
 *
 * 客户实体类 ：一的一方
 */

@Cacheable(value = true)
@Table(name="JPA_CUTOMERS")
@Entity
public class Customer {

	private Integer id;
	private String lastName;

	private String email;
	private int age;
	
	private Date createdTime;
	private Date birth;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(String lastName, int age) {
		super();
		this.lastName = lastName;
		this.age = age;
	}



	private Set<Order> orders = new HashSet<Order>();

	
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_custom")
	@SequenceGenerator(name = "seq_custom",sequenceName = "seq_custom")
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="LAST_NAME",length=50,nullable=false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Temporal(TemporalType.DATE)
	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}
	
	
	/**
	 * 单向 1-n 的关联关系步骤：
	 * 使用 @OneToMany 来映射 1-n 的关联关系
	 * 使用@JoinColumn(name="CUSTOMER_ID") 来映射外键列的名称
	 * 
	 * 
	 * 双向1-n的关联关系步骤：
	 * 1的一方不维护关系，所以 在 1 的一端的 @OneToMany 中使用 mappedBy 属性，表明是被维护端
	 * 同时不写@JoinColumn 注解了
	 * @return
	 * 
	 * 
	 *   //可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
	 *   //可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略. 	
	 *   //注意: 若在 1 的一端的 @OneToMany 中使用 mappedBy 属性, 则 @OneToMany 端就不能再使用 @JoinColumn 属性了. 

     *   mappedBy="值" ： 这个值 指的是维护端中定义的本类对象名称
     *   
     *   
     *   
     *   @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)是集合缓存 
	 */
	
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.REMOVE},mappedBy="customer")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)  
	public Set<Order> getOrders() {
		return orders;
	}
	
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	//工具方法. 不需要映射为数据表的一列. 
	@Transient
	public String getInfo(){
		return "lastName: " + lastName + ", email: " + email;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", lastName=" + lastName + ", email="
				+ email + ", age=" + age + ", createdTime=" + createdTime
				+ ", birth=" + birth + "]";
	}

}
