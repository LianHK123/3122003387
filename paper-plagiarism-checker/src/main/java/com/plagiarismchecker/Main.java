package com.plagiarismchecker;

import com.plagiarismchecker.core.SimilarityCalculator;
import com.plagiarismchecker.core.TextPreprocessor;
import com.plagiarismchecker.utils.FileHandlerUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java -jar main.jar [原文文件] [抄袭版论文的文件] [答案文件]");
            return;
        }

        String originalFilePath = args[0];
        String plagiarizedFilePath = args[1];
        String resultFilePath = args[2];

        try {
            // 读取文件
            String originalText = FileHandlerUtil.readFile(originalFilePath);
            String plagiarizedText = FileHandlerUtil.readFile(plagiarizedFilePath);

            // 文本预处理
            String processedOriginalText = String.join(" ", TextPreprocessor.extractFeatures(originalText));
            String processedPlagiarizedText = String.join(" ", TextPreprocessor.extractFeatures(plagiarizedText));

            // 计算相似度
            double similarity = SimilarityCalculator.calculateSimilarity(processedOriginalText, processedPlagiarizedText);

            // 输出结果
            String result = "文本相似度: " + similarity * 100 + "%";
            FileHandlerUtil.writeFile(resultFilePath, result);

            System.out.println(result);

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
