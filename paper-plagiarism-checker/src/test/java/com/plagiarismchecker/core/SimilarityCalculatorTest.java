package com.plagiarismchecker.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimilarityCalculatorTest {

    private SimilarityCalculator similarityCalculator;

    @BeforeEach
    public void setUp() {
        similarityCalculator = new SimilarityCalculator();
    }

    @Test
    public void testCalculateCosineSimilarity() {
        // 测试用例1：完全相同的词频向量
        Map<String, Integer> tf1 = new HashMap<>();
        tf1.put("a", 1);
        tf1.put("b", 2);

        Map<String, Integer> tf2 = new HashMap<>();
        tf2.put("a", 1);
        tf2.put("b", 2);

        double expected = 1.0;
        double actual = similarityCalculator.calculateCosineSimilarity(tf1, tf2);
        assertEquals(expected, actual, 0.0001);

        // 测试用例2：完全不同的词频向量
        Map<String, Integer> tf3 = new HashMap<>();
        tf3.put("c", 1);
        tf3.put("d", 2);

        double expected2 = 0.0;
        double actual2 = similarityCalculator.calculateCosineSimilarity(tf1, tf3);
        assertEquals(expected2, actual2, 0.0001);

        // 测试用例3：部分相同的词频向量
        Map<String, Integer> tf4 = new HashMap<>();
        tf4.put("a", 1);
        tf4.put("e", 2);

        double expected3 = 0.2; // 调整预期结果
        double actual3 = similarityCalculator.calculateCosineSimilarity(tf1, tf4);
        assertEquals(expected3, actual3, 0.0001);
    }


    @Test
    public void testCalculateSimilarity() {
        String text1 = "这是一个测试文件";
        String text2 = "这是一个测试文件";
        double expected = 1.0;
        double actual = similarityCalculator.calculateSimilarity(text1, text2);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        assertEquals(expected, actual, 0.001);
    }

    @Test
    public void testCalculateSimilarityWithNullText() {
        SimilarityCalculator.calculateSimilarity(null, "some text");
    }

}
