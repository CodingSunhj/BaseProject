package com.example.demo.commons.file.service.impl;

import com.example.demo.commons.file.service.FileService;
import com.example.demo.commons.file.util.FileUtils;
import com.example.demo.enums.ResultCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.utils.StringUtil;
import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @desc:
 * @author: create by SunHJ
 * @date:2018/6/14 18:02
 */
@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FileUtils fileUtils;

    @Value("${web.upload.file.path}")
    private String filePath;

    @Value("${web.upload.pic.path}")
    private String picPath;

    @Value("${web.upload.excel.path}")
    private String excelPath;

    @Value("${web.upload.pic.thumbWidth}")
    private int picThumbWidth;

    @Value("${web.upload.pic.thumbHeight}")
    private int picThumbHeight;

    @Value("${web.upload.pic.quality}")
    private float picQuality = 0.5f;

    @Value("${web.upload.pic.scale}")
    private float picScale = 0.25f;

    @Override
    public String uploadFileToPath(MultipartFile file, String destPath) {
        try {
            return fileUtils.copyFile(file,destPath);
        }catch (FileNotFoundException e){
            log.error("上传文件异常:{}",e);
            throw new BusinessException("文件未找到或拒绝访问目标文件夹");
        }catch (IOException e) {
            log.error("上传文件异常:{}",e);
        }
        return "";
    }

    @Override
    public String uploadFile(MultipartFile file) {
        if(StringUtil.isNotEmpty(filePath)){
            try {
                return fileUtils.copyFile(file,filePath);
            } catch (IOException e) {
                e.printStackTrace();
                log.error("图片文件上传异常:{}",e);
            }
        }else{
            throw new BusinessException(ResultCode.FILE_UPLOAD_PATH_NOT_SETTING);
        }
        return "";
    }

    @Override
    public String uploadFileWithSuffix(MultipartFile file, String suffix) {
        return null;
    }

    @Override
    public String uploadFileWithModuleName(MultipartFile file, String moduleName) {
        return null;
    }

    @Override
    public String uploadPic(MultipartFile file) {
        String path = StringUtil.isNotEmpty(picPath)?picPath:StringUtil.isNotEmpty(filePath)?filePath+ File.separator+"pic":"";
        if(StringUtil.isEmpty(path)){
            throw new BusinessException(ResultCode.FILE_UPLOAD_PATH_NOT_SETTING);
        }
        if(!fileUtils.isPic(file)){
            throw new BusinessException(ResultCode.PIC_FORMAT_ERROR);
        }
        try {
            String orignPath = path+File.separator+fileUtils.copyFile(file,path);
            String thumbPath = fileUtils.createPicThumbPath(orignPath);
            Files.createParentDirs(new File(thumbPath));
            //对图片配置参数进行判断
            if(picThumbWidth != 0|| picThumbHeight!=0){
                if(picThumbHeight!=0 && picThumbWidth!=0){
                    Thumbnails.of(orignPath).size(picThumbWidth,picThumbHeight).outputQuality(picQuality).toFile(thumbPath);
                }else{
                    int d = picThumbWidth==0?picThumbHeight:picThumbWidth;
                    Thumbnails.of(orignPath).size(d,d).outputQuality(picQuality).toFile(thumbPath);
                }
            }else{
                Thumbnails.of(orignPath).scale(picScale).outputQuality(picQuality).toFile(thumbPath);
            }

            return thumbPath.substring(path.length()+1);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("图片文件上传异常:{}",e);
        }
        return "";
    }

    @Override
    public String uploadExcel(MultipartFile file) {
        String path = StringUtil.isNotEmpty(excelPath)?excelPath:StringUtil.isNotEmpty(filePath)?filePath+ File.separator+"excel":"";
        if(StringUtil.isEmpty(path)){
            throw new BusinessException(ResultCode.FILE_UPLOAD_PATH_NOT_SETTING);
        }
        if(!fileUtils.isExcel(file)){
            throw new BusinessException(ResultCode.PIC_FORMAT_ERROR);
        }
        try {
            return fileUtils.copyFile(file,path);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("图片文件上传异常:{}",e);
        }
        return "";
    }
}
