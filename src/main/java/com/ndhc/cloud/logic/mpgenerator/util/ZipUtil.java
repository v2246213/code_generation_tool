package com.ndhc.cloud.logic.mpgenerator.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author yangnian
 * @datc 2018/9/2 19:03
 */
public class ZipUtil {/**
 * 打包成zip包
 */
public static void generateZip(OutputStream os, List<File> files) throws Exception {
    ZipOutputStream out = null;
    try {
        byte[] buffer = new byte[1024];
        //生成的ZIP文件名为Demo.zip
        out = new ZipOutputStream(os);
        //需要同时下载的两个文件result.txt ，source.txt
        for (File file : files) {
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(file.getName()));
            int len;
            //读入需要下载的文件的内容，打包到zip文件
            while ((len = fis.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.closeEntry();
            fis.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (out != null) {
            out.close();
        }
    }
}
}
