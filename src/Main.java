import com.plagiarismchecker.core.SimilarityCalculator;
import com.plagiarismchecker.core.TextPreprocessor;
import com.plagiarismchecker.utils.FileHandlerUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) {
        try {
            // 读取文件
            String originalText = FileHandlerUtil.readFile("src/org.txt");
            String plagiarizedText = FileHandlerUtil.readFile("src/org_add.txt");

            // 文本预处理
            String processedOriginalText = String.join(" ", TextPreprocessor.extractFeatures(originalText));
            String processedPlagiarizedText = String.join(" ", TextPreprocessor.extractFeatures(plagiarizedText));

            // 计算相似度
            double similarity = SimilarityCalculator.calculateSimilarity(processedOriginalText, processedPlagiarizedText);

            // 输出结果
            String result = "文本相似度: " + similarity * 100 + "%";
            FileHandlerUtil.writeFile("ans.txt", result);

            System.out.println(result);

        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
