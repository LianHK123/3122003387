package com.plagiarismchecker.core;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public class TextPreprocessorTest {

    @Test
    public void testTokenize() {
        String text = "Hello, world!";
        List<String> tokens = TextPreprocessor.tokenize(text);
        assertEquals(2, tokens.size());  // "hello" 和 "world"
        assertTrue(tokens.contains("hello"));
        assertTrue(tokens.contains("world"));
    }

    @Test
    public void testRemoveStopWords() {
        List<String> words = List.of("the", "quick", "brown", "fox");
        List<String> cleanedWords = TextPreprocessor.removeStopWords(words);
        assertEquals(3, cleanedWords.size());  // "quick", "brown", "fox"
        assertFalse(cleanedWords.contains("the"));
    }

    @Test
    public void testExtractFeatures() {
        String text = "The quick brown fox";
        List<String> features = TextPreprocessor.extractFeatures(text);
        assertEquals(3, features.size());  // "quick", "brown", "fox"
        assertFalse(features.contains("the"));
    }

    @Test
    void testTokenize_EmptyText() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TextPreprocessor.tokenize("");
        });
        assertEquals("Input text cannot be null or empty", exception.getMessage());
    }

    @Test
    void testTokenize_NullText() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TextPreprocessor.tokenize(null);
        });
        assertEquals("Input text cannot be null or empty", exception.getMessage());
    }


}
