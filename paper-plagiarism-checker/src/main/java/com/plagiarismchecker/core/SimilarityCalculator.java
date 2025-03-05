package com.plagiarismchecker.core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimilarityCalculator {
    // 计算词频向量
    private static Map<String, Integer> computeTermFrequency(List<String> words) {
        Map<String, Integer> termFrequency = new HashMap<>();
        for (String word : words) {
            termFrequency.put(word, termFrequency.getOrDefault(word, 0) + 1);
        }
        return termFrequency;
    }

    // 计算余弦相似度
    public static double calculateCosineSimilarity(Map<String, Integer> tf1, Map<String, Integer> tf2) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        // 计算点积和向量的模
        for (String term : tf1.keySet()) {
            int freqA = tf1.get(term);
            int freqB = tf2.getOrDefault(term, 0);
            dotProduct += freqA * freqB;
            normA += freqA * freqA;
        }

        for (String term : tf2.keySet()) {
            int freqB = tf2.get(term);
            normB += freqB * freqB;
        }

        // 计算余弦相似度
        if (normA == 0.0 || normB == 0.0) {
            return 0.0;
        }

        return dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
    }

    // 计算文本相似度
    public static double calculateSimilarity(String text1, String text2) {
        TextPreprocessor textPreprocessor = new TextPreprocessor();
        List<String> tokens1 = textPreprocessor.extractFeatures(text1);
        List<String> tokens2 = textPreprocessor.extractFeatures(text2);

        Map<String, Integer> tf1 = computeTermFrequency(tokens1);
        Map<String, Integer> tf2 = computeTermFrequency(tokens2);

        return calculateCosineSimilarity(tf1, tf2);
    }
}
