package com.plagiarismchecker.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class SimilarityCalculatorTest {

    @Test
    public void testComputeHash() throws NoSuchAlgorithmException {
        String text = "Hello, world!";
        String hash = SimilarityCalculator.computeHash(text);
        assertNotNull(hash);
        assertEquals(64, hash.length());  // SHA-256 哈希值应为 64 个字符
    }

    @Test
    public void testCompareHashes() {
        String hash1 = "abcdef1234567890abcdef1234567890abcdef1234567890abcdef1234567890";
        String hash2 = "abcdef1234567890abcdef1234567890abcdef1234567890abcdef1234567891";
        double similarity = SimilarityCalculator.compareHashes(hash1, hash2);
        assertTrue(similarity < 1.0);  // 不同哈希值应返回较低的相似度
    }

    @Test
    public void testCalculateSimilarity() throws NoSuchAlgorithmException {
        String text1 = "Hello, world!";
        String text2 = "Hello, world!";
        double similarity = SimilarityCalculator.calculateSimilarity(text1, text2);
        assertEquals(1.0, similarity, 0.01);  // 相同文本的相似度应为 1.0

        String text3 = "Goodbye, world!";
        similarity = SimilarityCalculator.calculateSimilarity(text1, text3);
        assertTrue(similarity < 1.0);  // 不同文本的相似度应小于 1.0
    }
}
