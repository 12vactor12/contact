package com.service.impl;

import com.dao.AddressBookDao;
import com.entity.AddressBookEntity;
import com.entity.view.AddressBookView;
import com.service.AddressBookService;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.entity.vo.AddressBookVO;

@Service("contactService")
public class AddressBookServiceImpl extends ServiceImpl<AddressBookDao, AddressBookEntity> implements AddressBookService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AddressBookEntity> page = this.selectPage(
                new Query<AddressBookEntity>(params).getPage(),
                new EntityWrapper<AddressBookEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<AddressBookEntity> wrapper) {
		  Page<AddressBookView> page =new Query<AddressBookView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

	@Override
	public PageUtils queryCollectPage(Map<String, Object> params, Wrapper<AddressBookEntity> wrapper) {
		Page<AddressBookView> page =new Query<AddressBookView>(params).getPage();
		page.setRecords(baseMapper.selectCollectListView(page,wrapper));
		PageUtils pageUtil = new PageUtils(page);
		return pageUtil;
	}

	@Override
	public void doAss(Long[] ids, Long id) {
		for (Long cid:ids){
			baseMapper.insertAss(id,cid);
		}
	}

	@Override
	public void saveBatch(List<AddressBookEntity> list) {
		for (AddressBookEntity addressBookEntity:list){
			baseMapper.insert(addressBookEntity);
		}
	}

	@Override
	public void doCollect(Long id) {
		baseMapper.doCollect(id);
	}

	@Override
	public List<AddressBookVO> selectListVO(Wrapper<AddressBookEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public AddressBookVO selectVO(Wrapper<AddressBookEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<AddressBookView> selectListView(Wrapper<AddressBookEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public AddressBookView selectView(Wrapper<AddressBookEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

}
