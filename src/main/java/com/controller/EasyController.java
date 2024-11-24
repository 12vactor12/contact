package com.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.entity.AddressBookEntity;
import com.service.AddressBookService;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
@RestController
@RequestMapping("/ei")
public class EasyController {
    @Autowired
    private AddressBookService addressBookService;

    /**
     * excel 导入
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<AddressBookEntity> addList = CollUtil.newArrayList();
        for (List<Object> row : list) {
            AddressBookEntity addressBookEntity = new AddressBookEntity();
            addressBookEntity.setName(row.get(0).toString());
            addressBookEntity.setPhone(row.get(1).toString());
            addressBookEntity.setEmail(row.get(2).toString());
            addressBookEntity.setSmaccounts(row.get(3).toString());
            addressBookEntity.setAddress(row.get(4).toString());
            addList.add(addressBookEntity);
        }
        addressBookService.saveBatch(addList);
        return true;
    }

}
