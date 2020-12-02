package com.icore.winvaz.javase.basic.io;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @Deciption 带有行号的读取
 * @Author wdq
 * @Create 2019-08-18 16:19
 */
public class LineNumberReaderTest {

    @Test
    public void test() {
        LineNumberReader lineNumberReader = null;
        try {
            lineNumberReader = new LineNumberReader(new FileReader("/Users/wdq/Desktop/writer.txt"));
            // 设置行号
            //lineNumberReader.setLineNumber(100);
            String line = null;
            while ((line = lineNumberReader.readLine()) != null) {
                // 获取行号的方法
                System.out.println("第" + lineNumberReader.getLineNumber() + "行 " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
