package com.example.demo.commons.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @desc:
 * @author: create by SunHJ
 * @date:2018/6/14 15:05
 */
public interface FileService {

    /**
     * 上传文件到指定目录
     * @param file
     * @param destPath
     * @return
     */
    String uploadFileToPath(MultipartFile file,String destPath);

    /**
     * 上传文件
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);

    /**
     * 上传带后缀名的文件
     * @param file
     * @param suffix
     * @return
     */
    String uploadFileWithSuffix(MultipartFile file,String suffix);

    /**
     * 根据模块上传到不同的文件夹
     * @param file
     * @param moduleName
     * @return
     */
    String uploadFileWithModuleName(MultipartFile file,String moduleName);


    /**
     * 上传图片文件
     * @param file
     * @return
     */
    String uploadPic(MultipartFile file);

    /**
     * 上传Excel
     * @param file
     * @return
     */
    String uploadExcel(MultipartFile file);



}
