package com.gemini.learning;

/**
 * com.gemini.learning.Trie
 *
 * @author zhanghailin
 */
public class Trie {

    private TrieNode root = new TrieNode('/'); // 存储无意义字符

    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; i++) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public boolean find(char[] pattern) {
        TrieNode p = root;
        for (int i = 0; i < pattern.length; i++) {
            int index = pattern[i] - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
        }
        return p.isEndingChar;
    }

}

class TrieNode {
    char data;
    TrieNode[] children = new TrieNode[26];
    boolean isEndingChar = false;

    public TrieNode(char data) {
        this.data = data;
    }
}
