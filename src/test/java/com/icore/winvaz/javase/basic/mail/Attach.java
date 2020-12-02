package com.icore.winvaz.javase.basic.mail;

import java.io.File;

/**
 * 附件类
 *
 * @Author wdq
 * @Create 2020/6/29 20:29
 * @Version 1.0.0
 */
public class Attach {

    private String cid;
    /**
     * 文件
     */
    private File file;
    /**
     * 文件名称
     */
    private String fileName;

    public Attach() {

    }

    public Attach(File file, String fileName) {
        super();
        this.file = file;
        this.fileName = fileName;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
