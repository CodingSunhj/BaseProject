package com.example;

import com.example.demo.commons.mapper.IdWorker;
import com.example.demo.commons.mapper.SimpleGenId;

import java.io.*;
import java.util.Random;

/**
 * @desc:
 * @author: create by SunHJ
 * @date:2018/6/13 9:49
 */
public class Test {
    public static String getRandomChar() {
        String str = "";
        int highCode;
        int lowCode;

        Random random = new Random();

        highCode = (176 + Math.abs(random.nextInt(39))); //B0 + 0~39(16~55) 一级汉字所占区
        lowCode = (161 + Math.abs(random.nextInt(93))); //A1 + 0~93 每区有94个汉字

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(highCode)).byteValue();
        b[1] = (Integer.valueOf(lowCode)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str;
    }



    public static void main(String[] args) throws IOException {
        File file = new File("C:/tmp/user.sql");
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos,"utf-8"));


        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        IdWorker idWorker = new IdWorker(1,1,1);
        for(int j = 0;j<2500000;j++){
            int number = new Random().nextInt(3)+2;
            StringBuilder name = new StringBuilder();
            for (int i = 0;i<number;i++){
                name.append(getRandomChar());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO `user` VALUES ('").append(idWorker.nextId()).append("',");
            sb.append("'").append(name.toString()).append("',");
            sb.append("'").append(new Random().nextInt(90)).append("',");
            sb.append("'").append((new Random().nextInt(2))==0?"男":"女").append("',");
            sb.append("'").append(str.substring(new Random().nextInt(26),new Random().nextInt(26)+35)).append("');");
//            System.out.println(sb.toString());
            bw.write(sb.toString());
            bw.newLine();
        }
        bw.close();


    }
}
