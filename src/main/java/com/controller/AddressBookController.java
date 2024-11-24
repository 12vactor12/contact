package com.controller;


import java.util.Arrays;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.entity.AddressBookEntity;

import com.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;


/**
 * 地址簿
 * 后端接口
 */
@RestController
@RequestMapping("/ab")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;
    


    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, AddressBookEntity contact,
                  HttpServletRequest request){
        EntityWrapper<AddressBookEntity> ew = new EntityWrapper<AddressBookEntity>();
		PageUtils page = addressBookService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, contact), params), params));

        return R.ok().put("data", page);
    }

    /**
     * 后端列表-关联查询
     */
    @RequestMapping("/collectPage")
    public R collectPage(@RequestParam Map<String, Object> params, AddressBookEntity addressBookEntity,
                  HttpServletRequest request){
        EntityWrapper<AddressBookEntity> ew = new EntityWrapper<AddressBookEntity>();
        PageUtils page = addressBookService.queryCollectPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, addressBookEntity), params).ne("id",params.get("fromId")), params));

        return R.ok().put("data", page);
    }


	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        AddressBookEntity contact = addressBookService.selectById(id);
        return R.ok().put("data", contact);
    }

    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AddressBookEntity contact, HttpServletRequest request){
        addressBookService.insert(contact);
        return R.ok();
    }
    


    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AddressBookEntity contact, HttpServletRequest request){
        addressBookService.updateById(contact);//全部更新
        return R.ok();
    }
    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        addressBookService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

    /**
     * 收藏
     */
    @RequestMapping("/doCollect")
    public R update(@RequestBody Long id){
        addressBookService.doCollect(id);//全部更新
        return R.ok();
    }

    /**
     * 关联
     */
    @RequestMapping("/ass")
    public R update(@RequestBody Long[] ids,@RequestParam Long fromId){
        addressBookService.doAss(ids,fromId);//全部更新
        return R.ok();
    }

}
