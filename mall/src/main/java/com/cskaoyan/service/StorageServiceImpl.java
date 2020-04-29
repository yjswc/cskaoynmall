package com.cskaoyan.service;

import com.cskaoyan.bean.system.Storage;
import com.cskaoyan.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 11:12
 * @Version: 1.0
 */
@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    StorageMapper storageMapper;

    @Override
    public String createStorage(MultipartFile file) throws IOException {
        Storage storage = new Storage();
        String filename = file.getOriginalFilename();
        storage.setName(filename);
        Integer begin = filename.indexOf(".");
        Integer last = filename.length();
        String suffix = filename.substring(begin, last);
        String type = file.getContentType();
        String uuid = UUID.randomUUID().toString();
        String key = Integer.toHexString((uuid + "-" + filename).hashCode());
        filename = key + suffix;
        StringBuffer basePath = new StringBuffer("/upload/");
        for (char c : key.toCharArray()) {
            basePath.append(c).append('/');
        }
        String path = basePath.toString() + filename;
        File s = new File(ResourceUtils.getURL("classpath:").getPath() + "static" + path);
        if (!s.getParentFile().exists()) s.getParentFile().mkdirs();
        file.transferTo(s);
        storage.setKey(key);
        storage.setType(type);
        storage.setUrl("http://localhost:8080" + path);
        storage.setSize((int) file.getSize());
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storageMapper.insertSelective(storage);

        return storage.getUrl();
    }
}
