package com.plagiarismchecker.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class FileHandlerUtilTest {

    @Test
    public void testReadFile() throws IOException {
        String filePath = "src/main/resources/test.txt";  // 需要创建测试文件
        String content = FileHandlerUtil.readFile(filePath);
        assertNotNull(content);
        assertTrue(content.contains("Expected content"));  // 检查文件内容是否包含预期内容
    }

    @Test
    public void testWriteFile() throws IOException {
        String filePath = "src/output.txt";
        String content = "This is a test";
        FileHandlerUtil.writeFile(filePath, content);

        String writtenContent = FileHandlerUtil.readFile(filePath);
        assertEquals(content, writtenContent);  // 检查写入的内容是否与读取的内容一致
    }

    @Test
    public void testReadFile_FileNotFound() {
        String invalidFilePath = "invalid/path/to/file.txt";
        assertThrows(IOException.class, () -> {
            FileHandlerUtil.readFile(invalidFilePath);
        });
    }

    @Test
    public void testReadFileWithInvalidPath() {
        try {
            FileHandlerUtil.readFile("non_existent_file.txt");
            // 如果没有抛出异常，则测试失败
            fail("Expected NoSuchFileException to be thrown");
        } catch (IOException e) {
            // 预期的异常被抛出，测试通过
            assertTrue(true);
        }
    }

}
