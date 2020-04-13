package com.gemini.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * com.gemini.leetcode.LeetCode003_LengthOfLongestSubstr
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode003_LengthOfLongestSubstr {

    public int lengthOfLongestSubstr(String s) {
        int len = s.length();
        if (len == 0) return 0;
        int ans = 1;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (s.substring(i, j).indexOf(s.charAt(j)) > -1) {
                    break;
                } else {
                    ans = Math.max(j - i + 1, ans);
                }
            }
        }

        return ans;
    }

    // 暂时没看懂
    public int lengthOfLongestSubstr2(String s) {
        int i, count = 0, max = 0, start = 0, len = s.length();
        int[] index = new int[128]; // 数组hash表
        for (i = 0; i < len; i++) {
            if (index[s.charAt(i)] > start) {
                count = i - start;
                max = max > count ? max : count;
                start = index[s.charAt(i)];
            }
            index[s.charAt(i)] = i + 1;
        }
        count = i - start;
        return max > count ? max : count;
    }

    public int lengthOfLongestSubstr3(String s) {
        int len = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        for (int j = 0, i = 0; j < len; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i); //
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        LeetCode003_LengthOfLongestSubstr lengthOfLongestSubstr = new LeetCode003_LengthOfLongestSubstr();
        System.out.println(lengthOfLongestSubstr.lengthOfLongestSubstr3(" "));
    }
}
