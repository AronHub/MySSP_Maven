package com.fjt.entitys.manyone;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 订单实体类：多的一方
 * @author Administrator
 *
 */
@Table(name="JPA_ORDERS")
@Entity
@Cacheable(true)
public class Order {

	private Integer id;
	private String orderName;

	private Customer customer;

	@GeneratedValue(generator ="seq_order",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_order",sequenceName = "seq_order")
	@Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name="ORDER_NAME")
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * 映射单向/双向  n-1 的关联关系步骤：
	 * 使用 @ManyToOne 来映射多对一的关联关系
	 * 使用 @JoinColumn 来映射外键.
     * 
	 * @return
	 * 
	 *  //可使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
	 *  //@JoinColumn(name="CUSTOMER_ID")  name值指的是数据库表中的外键名称
	 */
	@JoinColumn(name="CUSTOMER_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}