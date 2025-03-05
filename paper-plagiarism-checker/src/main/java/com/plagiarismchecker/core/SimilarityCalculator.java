package com.plagiarismchecker.core;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SimilarityCalculator {
    // 计算文本哈希值
    public static String computeHash(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(text.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    // 比较两个文本的哈希值
    public static double compareHashes(String hash1, String hash2) {
        int diff = 0;
        for (int i = 0; i < hash1.length(); i++) {
            if (hash1.charAt(i) != hash2.charAt(i)) {
                diff++;
            }
        }
        // 计算相似度
        return 1.0 - ((double) diff / hash1.length());
    }

    // 计算文本相似度
    public static double calculateSimilarity(String text1, String text2) throws NoSuchAlgorithmException {
        String hash1 = computeHash(text1);
        String hash2 = computeHash(text2);
        return compareHashes(hash1, hash2);
    }
}