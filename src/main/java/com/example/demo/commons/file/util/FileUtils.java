package com.example.demo.commons.file.util;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc:
 * @author: create by SunHJ
 * @date:2018/6/14 11:43
 */
@Slf4j
@Component
public class FileUtils {
    public static void main(String[] args) throws IOException {
        File fromFile = new File("C:/tmp/2016-08-28_a.jpg");
//        System.out.println(isPic(fromFile));
    }

    /**
     * 根据路径复制文件
     *
     * @param fileFromPath
     * @param fileToPath
     * @throws IOException
     */
    public String copyFile(String fileFromPath, String fileToPath) throws IOException {
        File fromFile = new File(fileFromPath);
        File toFile = new File(fileToPath);
        copyFile(fromFile, toFile);
        return fileToPath;
    }

    /**
     * 将目标File复制到指定目录
     *
     * @param fromFile
     * @param toPath
     */
    public static String copyFile(File fromFile, String toPath) throws IOException {
        String relativePath = LocalDate.now()+File.separator+fromFile.getName();
        String path = toPath+File.separator+relativePath;
        File toFile = new File(path);
        return relativePath;
    }

    public String copyFile(MultipartFile file, String toPath) throws IOException {
        String relativePath = LocalDate.now()+File.separator+file.getOriginalFilename();
        File convFile = new File(toPath + File.separator + relativePath);
        File tempFile = null;
        tempFile = File.createTempFile("tmp",null);
        file.transferTo(tempFile);
        copyFile(tempFile,convFile);
        tempFile.deleteOnExit();
        return relativePath;
    }

    /**
     * 复制文件
     *
     * @param fromFile
     * @param toFile
     * @throws IOException
     */
    public  String copyFile(File fromFile, File toFile) throws IOException {
        Files.createParentDirs(toFile); //检测目录文件夹是否存在
        Files.copy(fromFile, toFile);
        return toFile.getAbsolutePath();
    }


    /**
     * 判断文件是否为图片格式
     *
     * @param file
     * @return
     */
    public boolean isPic(File file) {
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.gif|.GIF)$";
        Optional<File> fileOpt = Optional.ofNullable(file);
        Pattern pattern = Pattern.compile(reg);
        return fileOpt.map(isMatch -> pattern.matcher(file.getName()).find())
                .orElse(false);
    }
    public boolean isPic(MultipartFile file){
        String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.gif|.GIF)$";
        Optional<MultipartFile> fileOpt = Optional.ofNullable(file);
        Pattern pattern = Pattern.compile(reg);
        return fileOpt.map(isMatch -> pattern.matcher(file.getOriginalFilename()).find())
                .orElse(false);
    }

    /**
     * 根据原路径产生图片的压缩路径
     * @param path
     * @return
     */
    public String createPicThumbPath(String path){
      return path.substring(0,path.lastIndexOf(File.separator))+File.separator+"thumb"+File.separator+path.substring(path.lastIndexOf(File.separator)+1,path.length());
    }

    /**
     * 判断文件是否为excel文件
     *
     * @param file
     * @return
     */
    public boolean isExcel(File file) {
        String reg = ".+(.xls|.XLS|.xlsx|.XLSX)$";
        Optional<File> fileOpt = Optional.ofNullable(file);
        Pattern pattern = Pattern.compile(reg);
        return fileOpt.map(isMatch -> pattern.matcher(file.getName()).find())
                .orElse(false);
    }
    public boolean isExcel(MultipartFile file) {
        String reg = ".+(.xls|.XLS|.xlsx|.XLSX)$";
        Optional<MultipartFile> fileOpt = Optional.ofNullable(file);
        Pattern pattern = Pattern.compile(reg);
        return fileOpt.map(isMatch -> pattern.matcher(file.getOriginalFilename()).find())
                .orElse(false);
    }

    /**
     * 传入的文件是否匹配相应的后缀名
     *
     * @param file
     * @param suffix
     * @return
     */
    public boolean isMatchSuffix(File file, String suffix) {
        Optional<File> fileOpt = Optional.ofNullable(file);
        return fileOpt.map(isMatch -> getSuffix(file).equalsIgnoreCase(suffix))
                .orElse(false);
    }

    /**
     * 获取文件的后缀名
     *
     * @param file
     * @return
     */
    public String getSuffix(File file) {
        Optional<File> fileOpt = Optional.ofNullable(file);
        return fileOpt.map(fileSuffix -> file.getName().substring(file.getName().lastIndexOf(".") + 1, file.getName().length()))
                .orElse("");
    }

    public File covertMultipartFileToFile(MultipartFile file) throws IOException {
        File tempFile = null;
        tempFile = File.createTempFile("tmp",null);
        file.transferTo(tempFile);
        tempFile.deleteOnExit();
        return tempFile;

    }
}
