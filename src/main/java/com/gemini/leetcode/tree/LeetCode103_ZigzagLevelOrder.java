package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

import java.util.*;

/**
 * com.gemini.leetcode.tree.LeetCode103_ZigzagLevelOrder
 *
 * @author zhanghailin
 */
public class LeetCode103_ZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        // 记录层数的奇偶性
        int cnt = 0;
        while (!q.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            int len = q.size();
            for (int i = 0; i < len; i++) {
                TreeNode node = q.poll();
                if (node == null) continue;
                // 这里将层数的奇偶性做下判断
                // 如果是偶数层就正向存储（以 0 为起始层）
                if (cnt % 2 == 0) {
                    tmp.add(node.val);
                } else {
                    // 奇数层则反向存储
                    tmp.add(0, node.val);
                }

                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            cnt++;
            ans.add(tmp);
        }
        return ans;


    }

    public static void main(String[] args) {
        LeetCode103_ZigzagLevelOrder zigzagLevelOrder = new LeetCode103_ZigzagLevelOrder();
        int[] arr = {1,2,3,4,5,6,7};
        zigzagLevelOrder.zigzagLevelOrder(Tool.createTree(arr));
    }
}
