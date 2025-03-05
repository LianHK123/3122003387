package com.plagiarismchecker.core;

import java.util.*;

public class TextPreprocessor {

    private static final Set<String> STOP_WORDS = new HashSet<>(Arrays.asList(
            "the", "and", "is", "in", "at", "which", "on", "for", "with", "a", "an",
            "我", "你", "他", "她", "它", "我们", "你们", "他们", "她们", "它们",
            "的", "了", "是", "在", "不", "一", "有", "和", "就", "都", "不", "一个", "上",
            "也", "很", "到", "说", "要", "去", "能", "会", "着", "没有", "看", "好", "自己",
            "这", "那", "有", "很", "只", "对", "也", "着", "就", "不", "要", "都", "是", "在",
            "一个", "上", "也", "很", "到", "说", "去", "能", "会", "着", "没有", "看", "好", "自己"
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