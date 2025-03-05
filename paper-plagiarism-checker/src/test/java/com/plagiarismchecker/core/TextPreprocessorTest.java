package com.plagiarismchecker.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TextPreprocessorTest {

    private TextPreprocessor textPreprocessor;

    @BeforeEach
    public void setUp() {
        textPreprocessor = new TextPreprocessor();
    }

    @Test
    public void testTokenize() {
        // 测试用例1：正常输入
        String text1 = "Hello, World!";
        List<String> expected1 = Arrays.asList("hello", "world");
        List<String> actual1 = textPreprocessor.tokenize(text1);
        assertEquals(expected1, actual1);

        // 测试用例2：空字符串
        String text2 = "";
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> {
            textPreprocessor.tokenize(text2);
        });
        assertEquals("Input text cannot be null or empty", exception2.getMessage());

        // 测试用例3：null 输入
        String text3 = null;
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> {
            textPreprocessor.tokenize(text3);
        });
        assertEquals("Input text cannot be null or empty", exception3.getMessage());

        // 测试用例4：仅包含停用词
        String text4 = "the and is in at which on for with a an";
        List<String> expected4 = Arrays.asList();
        List<String> actual4 = textPreprocessor.tokenize(text4);
        assertEquals(expected4, actual4);
    }

    @Test
    public void testRemoveStopWords() {
        // 测试用例1：正常输入
        List<String> words1 = Arrays.asList("hello", "the", "world", "and");
        List<String> expected1 = Arrays.asList("hello", "world");
        List<String> actual1 = textPreprocessor.removeStopWords(words1);
        assertEquals(expected1, actual1);

        // 测试用例2：仅包含停用词
        List<String> words2 = Arrays.asList("the", "and", "is", "in", "at");
        List<String> expected2 = Arrays.asList();
        List<String> actual2 = textPreprocessor.removeStopWords(words2);
        assertEquals(expected2, actual2);

        // 测试用例3：不包含停用词
        List<String> words3 = Arrays.asList("hello", "world");
        List<String> expected3 = Arrays.asList("hello", "world");
        List<String> actual3 = textPreprocessor.removeStopWords(words3);
        assertEquals(expected3, actual3);

        // 测试用例4：空列表
        List<String> words4 = Arrays.asList();
        List<String> expected4 = Arrays.asList();
        List<String> actual4 = textPreprocessor.removeStopWords(words4);
        assertEquals(expected4, actual4);
    }

    @Test
    public void testTokenizeWithNullInput() {
        TextPreprocessor preprocessor = new TextPreprocessor();
        try {
            preprocessor.tokenize(null);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Input text cannot be null or empty", e.getMessage());
        }
    }


    @Test
    public void testExtractFeaturesWithEmptyInput() {
        TextPreprocessor preprocessor = new TextPreprocessor();
        List<String> features = preprocessor.extractFeatures("");
        assertTrue(features.isEmpty());
    }

}
