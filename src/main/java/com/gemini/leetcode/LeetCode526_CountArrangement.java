package com.gemini.leetcode;

/**
 * com.gemini.leetcode.LeetCode526_CountArrangement
 * <p>
 * 假设有从 1 到 N 的 N 个整数，如果从这 N 个数字中成功构造出一个数组，使得数组的第 i 位 (1 <= i <= N) 满足如下两个条件中的一个，我们就称这个数组为一个优美的排列。条件：
 * <p>
 * 第 i 位的数字能被 i 整除
 * i 能被第 i 位上的数字整除
 * 现在给定一个整数 N，请问可以构造多少个优美的排列？
 * <p>
 * 示例1:
 * <p>
 * 输入: 2
 * 输出: 2
 * 解释:
 * <p>
 * 第 1 个优美的排列是 [1, 2]:
 * 第 1 个位置（i=1）上的数字是1，1能被 i（i=1）整除
 * 第 2 个位置（i=2）上的数字是2，2能被 i（i=2）整除
 * <p>
 * 第 2 个优美的排列是 [2, 1]:
 * 第 1 个位置（i=1）上的数字是2，2能被 i（i=1）整除
 * 第 2 个位置（i=2）上的数字是1，i（i=2）能被 1 整除
 * 说明:
 * <p>
 * N 是一个正整数，并且不会超过15。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/beautiful-arrangement
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode526_CountArrangement {

    int count = 0;
    int[] v = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    public int countArrangement(int N) {
        dfs(1, N);
        return count;
    }

    // 回溯加剪枝，剪枝的条件就是满足 v[k] % k == 0 || k % v[k] == 0 否则剪枝
    void dfs(int k, int N) {
        if (k > N) {
            count++;
            return;
        }
        for (int i = k; i <= N; i++) {
            swap(i, k);
            if (v[k] % k == 0 || k % v[k] == 0) {
                dfs(k + 1, N);
            }
            swap(k, i);
        }
    }

    private void swap(int i, int j) {
        int tmp = v[i];
        v[i] = v[j];
        v[j] = tmp;
    }

    public static void main(String[] args) {
        LeetCode526_CountArrangement countArrangement = new LeetCode526_CountArrangement();
        System.out.println(countArrangement.countArrangement(3));
    }
}
