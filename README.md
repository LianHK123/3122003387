
|Info|Detail|
|---|---|
|作业要求	|[🔗作业链接](https://edu.cnblogs.com/campus/gdgy/SoftwareEngineeringClassof2023/homework/13324)|
|所属课程	|[🔗班级链接](https://edu.cnblogs.com/campus/gdgy/SoftwareEngineeringClassof2023)|
|学号|3122003387|
|仓库|[🔗Github仓库](https://github.com/LianHK123/3122003387)|

## PSP2.1

|PSP2.1|Personal Software Process Stages|预估耗时（分钟）|实际耗时（分钟）|
|---|---|---|---|
|Planning|计划|||
|· Estimate|估计这个任务需要多少时间|80|90| 
|Development|开发|||
|· Analysis|需求分析 (包括学习新技术)|120|120|
|· Design Spec|生成设计文档|80|90|
|· Design Review|设计复审|40|30|
|· Coding Standard|代码规范 (为目前的开发制定合适的规范)|50|60|
|· Design|具体设计|120|120|
|· Coding|具体编码|120|120|
|· Code Review|代码复审|50|40|
|· Test|测试（自我测试，修改代码，提交修改）|120|100|
|Reporting|报告|||
|· Test Report|测试报告|90|80|
|· Size Measurement|计算工作量|40|30|
|· Postmortem & Process Improvement Plan|事后总结, 并提出过程改进计划|40|40|
|Total|合计|950|920|

## 设计与实现

### 项目结构

```
paper-plagiarism-checker/
├── pom.xml                        # Maven 构建文件
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── plagiarismchecker/
│   │   │           ├── Main.java  # 主入口文件
│   │   │           ├── core/      # 核心功能模块
│   │   │           │   ├── SimilarityCalculator.java  # 查重算法实现
│   │   │           │   └── TextPreprocessor.java     # 文本预处理模块
│   │   │           └── utils/     # 工具类模块
│   │   │               └── FileHandlerUtil.java  # 文件读写工具类
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── plagiarismchecker/
│       │           ├── core/          # 核心功能模块的单元测试
│       │           │   ├── SimilarityCalculatorTest.java  # SimilarityCalculator 模块测试
│       │           │   └── TextPreprocessorTest.java    # TextPreprocessor 模块测试
│       │           └── utils/         # 工具类模块的单元测试
│       │               └── FileHandlerUtilTest.java  # 文件读写工具类单元测试
└── data/
    ├── ans.txt                    # 答案输出文件（测试数据）
    ├── org.txt                    # 原文文件（测试数据）
    └── org_add.txt                # 抄袭版文件（测试数据）

```

### 关键函数流程

#### SimilarityCalculator相似度计算

![](https://github.com/LianHK123/3122003387/blob/main/Assets/SimilarityCalculator.png)

#### 文本处理TextPreprocessor
![](https://github.com/LianHK123/3122003387/blob/main/Assets/TextPreprocessor.png)

#### 文件处理FileHandlerUtil
![](https://github.com/LianHK123/3122003387/blob/main/Assets/FileHandlerUtil.png)


### 算法关键/独到之处

-   该检测抄袭算法的独到之处在于其高效的文本预处理和相似度计算方法。通过精细化的文本分词和去除停用词，减少了噪音，提高了后续特征提取和相似度计算的准确性。结合哈希算法与特征提取，能快速、准确地计算文本相似度，特别是在处理改写或格式变动的抄袭时，展现出较强的容错能力。此外，算法的模块化设计使得系统清晰、可扩展，优化了文件读写效率，适用于大规模文本处理。整体而言，该算法兼具高效性和准确性，是一个稳定、灵活的抄袭检测方案。

## 性能改进
 
从Jprofiler分析的结果来看：
![](https://github.com/LianHK123/3122003387/blob/main/Assets/MemoryAnalyze.png)
###  **内存使用情况分析（内存分配图）**
   - **内存分配情况**：根据图表，可以看到在程序运行过程中内存的分配情况保持稳定，这说明程序没有频繁的内存波动，也未发生大量的垃圾回收（GC）。绿色的内存条显示程序在内存中占用的稳定区域，基本没有出现内存泄漏的情况。
   - **内存占用**：内存占用非常稳定并且在一定范围内。这意味着大部分内存分配是合适的，且程序没有过度消耗内存。


###  **内存占用最多的类**
![](https://github.com/LianHK123/3122003387/blob/main/Assets/ClassOccu.png)
   - 从内存使用的类分析来看，`java.lang.String`、`java.lang.StringBuilder`是占用内存最多的类，分别占据了28%和16%的内存。
     - **优化建议**：如果`StringBuilder`用得比较频繁，可以考虑进一步优化其使用，尤其是避免频繁创建`String`和`StringBuilder`对象。对于大文本的处理，建议将字符串拼接操作转移到内存更高效的结构上，例如通过流式处理。
     - `java.lang.String`占用的内存也表明可能存在大量字符串的创建。考虑合并相似操作，避免重复字符串的创建。
  
### **内存泄漏与无效对象**
   - 从堆栈跟踪中没有发现内存泄漏的迹象。内存中的大部分对象使用量较高，说明系统在处理文本时保持了合理的内存分配。
   - 类似`java.util.HashMap$Node`、`java.util.concurrent.ConcurrentHashMap$Node`的内存占用可能与哈希存储的中间对象有关，如果不加以管理，会增加内存消耗。

###  **优化建议**
   - **字符串操作优化**：减少不必要的`String`和`StringBuilder`对象创建。尤其是在大规模数据处理时，合并或分批处理字符串。
   - **使用内存更高效的容器**：考虑对缓存的内容进行压缩或使用内存管理更高效的数据结构。
   - **文本处理优化**：在`TextPreprocessor`中，尽量避免创建重复的对象，尽量减少不必要的临时变量的生成。

## 单元测试
![](https://github.com/LianHK123/3122003387/blob/main/Assets/PassRate.png)
- 单元测试通过率75.75%，达到标准。



### SimilarityCalculator单元测试
![](https://github.com/LianHK123/3122003387/blob/main/Assets/SimilarityCalculatorTest.png)
### TextPreproces单元测试
![](https://github.com/LianHK123/3122003387/blob/main/Assets/TextPreprocesTest.png)
### FileHandlerUtil单元测试
![](https://github.com/LianHK123/3122003387/blob/main/Assets/FileHandlerUtilTest.png)
## 异常处理
### FileHandlerUtil 类
该类包含了文件读取和写入的方法。异常主要是 IOException，它可能在文件读取、写入过程中出现。

- 设计目标：
IOException：处理文件操作相关的错误，例如文件不存在、权限不足、磁盘空间不足等。
示例代码：
```
// 读取文件内容
public static String readFile(String filePath) throws IOException {
    return new String(Files.readAllBytes(Paths.get(filePath)));
}

```
- 异常场景：
当指定的文件路径不存在或无法访问时，会抛出 IOException。
单元测试示例：
```
@Test
public void testReadFile_FileNotFound() {
    String invalidFilePath = "invalid/path/to/file.txt";
    assertThrows(IOException.class, () -> {
        FileHandlerUtil.readFile(invalidFilePath);
    });
}
```

### TextPreprocessor 类
该类用于文本的预处理，包括分词、去停用词等。异常可能出现在输入文本为空或格式不正确的情况下。

- 设计目标：
IllegalArgumentException：输入的文本为空或格式不正确时抛出异常。
示例代码：
```
// 去除停用词并分词
public static List<String> tokenize(String text) {
    if (text == null || text.trim().isEmpty()) {
        throw new IllegalArgumentException("Text cannot be null or empty");
    }
    text = text.toLowerCase();
    text = text.replaceAll("[^a-zA-Z ]", ""); // 移除非字母字符
    String[] words = text.split("\\s+");
    return Arrays.asList(words);
}
```
- 异常场景：
当输入文本为 null 或空字符串时，抛出 IllegalArgumentException。
单元测试示例：
```
@Test
public void testTokenize_EmptyText() {
    String emptyText = "";
    assertThrows(IllegalArgumentException.class, () -> {
        TextPreprocessor.tokenize(emptyText);
    });
}
```

### SimilarityCalculator 类
该类包含计算文本相似度的方法。异常主要是 NoSuchAlgorithmException，这是因为哈希算法无法找到时抛出的异常。

- 设计目标：
NoSuchAlgorithmException：计算哈希值时使用的算法不可用。
示例代码：
```
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
```
- 异常场景：
如果指定的哈希算法（如 SHA-256）不可用，会抛出 NoSuchAlgorithmException。
单元测试示例：
```
@Test
public void testComputeHash_InvalidAlgorithm() {
    String text = "Hello World";
    assertThrows(NoSuchAlgorithmException.class, () -> {
        SimilarityCalculator.computeHash(text);
    });
}
```


