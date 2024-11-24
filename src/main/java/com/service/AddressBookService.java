package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.entity.AddressBookEntity;
import com.entity.view.AddressBookView;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;
import com.entity.vo.AddressBookVO;
import org.apache.ibatis.annotations.Param;



/**
 * 通讯录
 */
public interface AddressBookService extends IService<AddressBookEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<AddressBookVO> selectListVO(Wrapper<AddressBookEntity> wrapper);
   	
   	AddressBookVO selectVO(@Param("ew") Wrapper<AddressBookEntity> wrapper);
   	
   	List<AddressBookView> selectListView(Wrapper<AddressBookEntity> wrapper);

	AddressBookView selectView(@Param("ew") Wrapper<AddressBookEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<AddressBookEntity> wrapper);

	   void doCollect(Long id);

	PageUtils queryCollectPage(Map<String, Object> params,Wrapper<AddressBookEntity> wrapper);

	void doAss(Long[] ids,Long id);

	void saveBatch(List<AddressBookEntity> list);
   	
}

