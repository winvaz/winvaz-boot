package com.icore.winvaz.javase.basic.io;

import org.junit.jupiter.api.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @Deciption TODO
 * @Author wdq
 * @Create 2021/1/21 11:03
 * @Version 1.0.0
 */
public class ZipTest {
    private static final String EXT = ".zip";
    private static final String BASE_DIR = "";

    // 符号"/"用来作为目录标识判断符号
    // 使用这个会更好 File.separator
    private static final String PATH = "/";
    private static final int BUFFER = 1024;


    @Test
    public void test() throws Exception {
        // 压缩文件
        compress(System.getProperty("user.dir") + File.separator + "ran.txt");
        // 压缩路径
        // compress("/Users/wdq/Documents/python_notes");
    }

    private static void compress(String srcPath) throws Exception {
        File srcFile = new File(srcPath);
        buildZip(srcFile);
    }

    /**
     * 压缩
     *
     * @param srcFile
     * @throws
     * @author wdq
     * @create 2021/1/21 11:09
     * @Return void
     */
    private static void buildZip(File srcFile) throws Exception {
        String name = srcFile.getName();
        String basePath = srcFile.getParent();
        String destPath = basePath + name + EXT;
        buildFile(srcFile, destPath);
    }

    /**
     *
     * @author wdq
     * @create 2021/1/21 11:23
     * @param srcFile 源路径
     * @param destPath 目标文件路径
     * @Return void
     * @exception
     */
    private static void buildFile(File srcFile, String destPath) throws Exception {
        checkZip(srcFile, new File(destPath));
    }

    /**
     *
     * @author wdq
     * @create 2021/1/21 11:19
     * @param srcFile 源路径
     * @param destFile 目标路径
     * @Return void
     * @exception 
     */
    private static void checkZip(File srcFile, File destFile) throws Exception {
        // 对输出文件做CRC32校验
        try (CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(destFile), new CRC32());
             ZipOutputStream zos = new ZipOutputStream(cos)) {
            zip(srcFile, zos, BASE_DIR);
        }
    }

    /**
     *
     * @author wdq
     * @create 2021/1/21 11:20
     * @param srcFile 源路径
     * @param zos
     * @param baseDir 压缩包内相对路径
     * @Return void
     * @exception 
     */
    private static void zip(File srcFile, ZipOutputStream zos, String baseDir) throws IOException {
        if (srcFile.isDirectory()) {
            zipDir(srcFile, zos, baseDir);
        } else {
            zipFile(srcFile, zos, baseDir);
        }
    }

    /**
     * 压缩文件
     * @author wdq
     * @create 2021/1/21 11:38
     * @param srcFile
     * @param zos
     * @param baseDir
     * @Return void
     * @exception
     */
    private static void zipFile(File srcFile, ZipOutputStream zos, String baseDir) throws IOException {
        /**
         * 压缩包内文件名定义
         * <pre>
         * 如果有多级目录，那么这里就需要给出包含目录的文件名
         * 如果用WinRAR打开压缩包，中文名将显示为乱码
         * </pre>
         */
        ZipEntry entry = new ZipEntry(baseDir + srcFile.getName());
        zos.putNextEntry(entry);

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFile));
        int count;
        byte[] data = new byte[BUFFER];
        while ((count = bis.read(data, 0, BUFFER)) != -1) {
            zos.write(data, 0, count);
        }
        bis.close();
        zos.closeEntry();
    }

    /**
     * 压缩目录
     * @author wdq
     * @create 2021/1/21 11:30
     * @param srcFile
     * @param zos
     * @param baseDir
     * @Return void
     * @exception
     */
    private static void zipDir(File srcFile, ZipOutputStream zos, String baseDir) throws IOException {
        File[] files = srcFile.listFiles();
        // 构建空目录
        if (files.length < 1) {
            ZipEntry entry = new ZipEntry(baseDir);
            zos.putNextEntry(entry);
            zos.closeEntry();
        }
        for (File file : files) {
            // 递归压缩
            zip(file, zos, baseDir + srcFile.getName() + File.separator);
        }
    }
}
