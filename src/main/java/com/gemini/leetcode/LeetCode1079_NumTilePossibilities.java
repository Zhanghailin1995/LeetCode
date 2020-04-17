package com.gemini.leetcode;

import java.util.Arrays;

/**
 * com.gemini.leetcode.LeetCode1079_NumTilePossibilities
 * <p>
 * 你有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。
 * <p>
 * 注意：本题中，每个活字字模只能使用一次。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："AAB"
 * 输出：8
 * 解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
 * 示例 2：
 * <p>
 * 输入："AAABBC"
 * 输出：188
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= tiles.length <= 7
 * tiles 由大写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-tile-possibilities
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode1079_NumTilePossibilities {

    private int ans;

    private boolean[] visited;

    public int numTilePossibilities(String tiles) {
        char[] ch = tiles.toCharArray();
        visited = new boolean[ch.length];
        Arrays.sort(ch);
        backtrack(ch, 0);
        return ans;
    }

    // 不加这个len也可以,效果类似,最后一定是visited[] 全部为true的时候还是回溯此时len == length,等于提前加了判断
    public void backtrack(char[] ch, int len) {
        if (len == ch.length) return;
        for (int i = 0; i < ch.length; i++) {
            if (visited[i]) continue;

            // 剪枝
            if (i > 0 && ch[i] == ch[i - 1] && !visited[i - 1]) continue;

            visited[i] = true;
            ans++;
            backtrack(ch, len + 1);
            visited[i] = false;
        }

    }


    public static void main(String[] args) {
        LeetCode1079_NumTilePossibilities leetCode1079_numTilePossibilities = new LeetCode1079_NumTilePossibilities();
        System.out.println(leetCode1079_numTilePossibilities.numTilePossibilities("AAB"));
    }

}
