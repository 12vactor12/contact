package com.entity.view;

import com.baomidou.mybatisplus.annotations.TableName;
import com.entity.AddressBookEntity;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
 

/**
 * 通讯录
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("address_book")
public class AddressBookView extends AddressBookEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public AddressBookView(){
	}
 
 	public AddressBookView(AddressBookEntity addressBookEntity){
 	try {
			BeanUtils.copyProperties(this, addressBookEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}

	private String coltype;

	public String getColtype() {
		return coltype;
	}

	public void setColtype(String coltype) {
		this.coltype = coltype;
	}
}
