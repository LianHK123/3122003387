package com.plagiarismchecker.core;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.common.Term;
import java.util.*;
import java.util.stream.Collectors;

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
    public List<String> tokenize(String text) {
        if (text == null || text.isEmpty()) {
            throw new IllegalArgumentException("Input text cannot be null or empty");
        }

        // 分词逻辑
        List<String> tokens = Arrays.asList(text.toLowerCase().split("\\W+"));

        // 去除停用词
        return removeStopWords(tokens);
    }

    // 去除停用词
    public static List<String> removeStopWords(List<String> words) {
        // 将不可变列表转换为可变列表
        List<String> mutableWords = new ArrayList<>(words);
        mutableWords.removeIf(STOP_WORDS::contains);
        return mutableWords;
    }

    // 使用 HanLP 进行中文分词
    public static List<String> extractFeatures(String text) {
        if (text == null || text.isEmpty()) {
            return Collections.emptyList();
        }

        // 使用 HanLP 的 NLP 分词模式
        List<Term> terms = HanLP.segment(text);

        // 提取词语并过滤单字及非内容词
        return terms.stream()
                .filter(term -> term.word.length() > 1 && isContentWord(term.nature.toString())) // 过滤单字及非内容词
                .map(term -> term.word) // 获取词语
                .collect(Collectors.toList());
    }

    // 判断是否为内容词
    private static boolean isContentWord(String nature) {
        return "n".equals(nature) || // 名词
                "v".equals(nature) || // 动词
                "a".equals(nature) || // 形容词
                "d".equals(nature);   // 副词
    }

    // 判断文本是否为中文
    private static boolean isChinese(String text) {
        for (char c : text.toCharArray()) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
                return true;
            }
        }
        return false;
    }
}
