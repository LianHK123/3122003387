package com.plagiarismchecker.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandlerUtil {
    // 读取文件内容
    public static String readFile(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }

    // 将文本写入文件
    public static void writeFile(String filePath, String content) throws IOException {
        Files.write(Paths.get(filePath), content.getBytes());
    }
}
