package com.plagiarismchecker.core;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TextPreprocessor {
    // 去除停用词并分词
    public static List<String> tokenize(String text) {
        text = text.toLowerCase();
        text = text.replaceAll("[^a-zA-Z ]", ""); // 移除非字母字符
        String[] words = text.split("\\s+");
        return Arrays.asList(words);
    }

    // 去除停用词
    public static List<String> removeStopWords(List<String> words) {
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "is", "at", "which", "on", "for", "and"));
        words.removeIf(stopWords::contains);
        return words;
    }

    // 提取文本特征
    public static List<String> extractFeatures(String text) {
        List<String> tokens = tokenize(text);
        return removeStopWords(tokens);
    }
}
