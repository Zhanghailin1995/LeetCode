package com.gemini.leetcode;

import java.util.HashMap;
import java.util.Stack;

/**
 * com.gemini.leetcode.LeetCode020_BracketsIsValid
 *
 * @author zhanghailin
 */
public class LeetCode020_BracketsIsValid {

    // Hash table that takes care of the mappings.
    private HashMap<Character, Character> mappings = new HashMap<Character, Character>() {
        {
            put(')', '(');
            put('}', '{');
            put(']', '[');
        }
    };


    public boolean isValid(String s) {
        int len = s.length();
        if (len == 0) return true;
        if (len == 1) return false;
        if ((len & 1) != 0) return false;
        // Initialize a stack to be used in the algorithm.
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {
                // 遇到右括号，从栈中pop 出 top元素，判断TOP元素是否与右括号匹配，匹配则继续，否则直接返回false
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != mappings.get(c)) {
                    return false;
                }
            } else {
                // 将左括号压入栈
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        if (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            return isValid2(s.replace("()", "").replace("[]", "").replace("{}", ""));
        } else {
            return "".equals(s);
        }
    }

    public static void main(String[] args) {
        //B%C，要满足C=2n 14%4 等价于 14&(22-1)
        System.out.println(1023 & 1);
    }
}
