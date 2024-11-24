package com.entity.vo;

import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;
 

/**
 *
 * 客户端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
public class AddressBookVO implements Serializable {
	private static final long serialVersionUID = 1L;

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
}
