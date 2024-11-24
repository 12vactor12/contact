package com.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import com.entity.AddressBookEntity;
import com.entity.view.AddressBookView;
import org.apache.ibatis.annotations.Param;
import com.entity.vo.AddressBookVO;



/**
 * 通讯录
 */
public interface AddressBookDao extends BaseMapper<AddressBookEntity> {
	
	List<AddressBookVO> selectListVO(@Param("ew") Wrapper<AddressBookEntity> wrapper);
	
	AddressBookVO selectVO(@Param("ew") Wrapper<AddressBookEntity> wrapper);
	
	List<AddressBookView> selectListView(@Param("ew") Wrapper<AddressBookEntity> wrapper);

	List<AddressBookView> selectListView(Pagination page, @Param("ew") Wrapper<AddressBookEntity> wrapper);

	AddressBookView selectView(@Param("ew") Wrapper<AddressBookEntity> wrapper);

	void doCollect(Long id);

	List<AddressBookView> selectCollectListView(Pagination page, @Param("ew") Wrapper<AddressBookEntity> wrapper);

	void insertAss(@Param("param1")Long id1,@Param("param2")Long id2);

}
