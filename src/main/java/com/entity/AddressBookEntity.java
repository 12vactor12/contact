package com.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;


/**
 * 地址簿
 * 数据库通用操作实体类（普通增删改查）
 */
@TableName("address_book")
public class AddressBookEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public AddressBookEntity() {
		
	}
	
	public AddressBookEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 主键id
	 */
	@TableId
	private Long id;
	/**
	 * 姓名
	 */
					
	private String name;
	
	/**
	 * 电话号码
	 */
					
	private String phone;

	/**
	 * 邮件地址
	 */
	private String email;

	/**
	 * 社交媒体账号
	 */
	private String smaccounts;

	/**
	 * 家庭地址
	 */
	private String address;

	/**
	 * 收藏
	 */
	private String collect;

	/**
	 * 创建时间
	 */
	@TableField("crt_time")
	private String crtTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getSmaccounts() {
		return smaccounts;
	}

	public void setSmaccounts(String smaccounts) {
		this.smaccounts = smaccounts;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCollect() {
		return collect;
	}

	public void setCollect(String collect) {
		this.collect = collect;
	}

	public String getCrtTime() {
		return crtTime;
	}

	public void setCrtTime(String crtTime) {
		this.crtTime = crtTime;
	}
}
