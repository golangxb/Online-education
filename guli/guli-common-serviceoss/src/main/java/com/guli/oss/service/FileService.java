package com.guli.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-28
 * Time : 16:32
 **/
public interface FileService {

    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
