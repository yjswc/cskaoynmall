package com.cskaoyan.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: Li Qing
 * @Create: 2020/4/29 11:12
 * @Version: 1.0
 */
public interface StorageService {
    String createStorage(MultipartFile file) throws IOException;
}
