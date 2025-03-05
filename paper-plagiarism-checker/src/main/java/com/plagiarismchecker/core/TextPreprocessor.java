package com.plagiarismchecker.core;

import java.util.*;

public class TextPreprocessor {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "the", "and", "is", "in", "at", "which", "on", "for", "with", "a", "an"
    ));

    // 去除停用词并分词
    public static List<String> tokenize(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }
        text = text.toLowerCase();
        text = text.replaceAll("[^a-zA-Z ]", ""); // 移除非字母字符
        String[] words = text.split("\\s+");
        return Arrays.asList(words);
    }


    // 去除停用词
    public static List<String> removeStopWords(List<String> words) {
        // 将不可变列表转换为可变列表
        List<String> mutableWords = new ArrayList<>(words);
        mutableWords.removeIf(STOP_WORDS::contains);
        return mutableWords;
    }

    // 提取文本特征
    public static List<String> extractFeatures(String text) {
        List<String> tokens = tokenize(text);
        return removeStopWords(tokens);
    }
}