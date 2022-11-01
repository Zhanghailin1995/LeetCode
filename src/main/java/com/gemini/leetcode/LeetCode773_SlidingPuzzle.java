package com.gemini.leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 773. 滑动谜题
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 *
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 *
 * 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：board = [[1,2,3],[4,0,5]]
 * 输出：1
 * 解释：交换 0 和 5 ，1 步完成
 * 示例 2:
 *
 *
 *
 * 输入：board = [[1,2,3],[5,4,0]]
 * 输出：-1
 * 解释：没有办法完成谜板
 * 示例 3:
 *
 *
 *
 * 输入：board = [[4,1,2],[5,0,3]]
 * 输出：5
 * 解释：
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 *
 *
 * 提示：
 *
 * board.length == 2
 * board[i].length == 3
 * 0 <= board[i][j] <= 5
 * board[i][j] 中每个值都 不同
 * 通过次数31,623提交次数45,011
 */
public class LeetCode773_SlidingPuzzle {

    // 本质是运用BFS的思想
    // 遍历当前状态下，移动一格后的状态，如果达到了目标则结束，如果没有达到，记录下当前状态移动一格后可能达到的所有状态，再继续遍历下去
    // 为了方便计算，将二维的方格简化为一维字符串，并且记录每个字符在二维状态下相邻的字符的坐标（注意是坐标，不是相邻的字符本身）
    // 这些相邻的坐标就是我们可移动的选择，即从当前状态到下一状态可以变更的途经
    // 这个题目的难度在于如何把这种格子的移动抽象为可以进行计算的算法，并且可以想到运用BFS思想进行解题
    public int slidingPuzzle(int[][] board) {
        int m  = board.length, n = board[0].length;
        StringBuilder sb = new StringBuilder();
        String target = "123450";
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();

        // 记录一维字符串的每个位置可以移动的位置
        int[][] neighbor = new int[][]{{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

        // BFS
        Queue<String> queue = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        queue.offer(start);

        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (target.equals(cur)) {
                    return step;
                }
                // 找到数字0的索引
                int idx = 0;
                for (; cur.charAt(idx) != '0'; idx++) ;
                // 将数字0 和相邻的数字交换位置
                for (int adj : neighbor[idx]) {
                    String new_board = swap(cur.toCharArray(), adj, idx);
                    // 防止重复计算
                    if (!visited.contains(new_board)) {
                        queue.offer(new_board);
                        visited.add(new_board);
                    }
                }
            }
            step++;
        }
        return 0;
    }

    private String swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
