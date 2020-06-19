package com.gemini.leetcode.tree;

import java.util.*;

/**
 * com.gemini.leetcode.tree.LeetCode1028_RecoverTreeFromPreorderTraversal
 * <p>
 * 1028. 从先序遍历还原二叉树
 * 我们从二叉树的根节点 root 开始进行深度优先搜索。
 * <p>
 * 在遍历中的每个节点处，我们输出 D 条短划线（其中 D 是该节点的深度），然后输出该节点的值。（如果节点的深度为 D，则其直接子节点的深度为 D + 1。根节点的深度为 0）。
 * <p>
 * 如果节点只有一个子节点，那么保证该子节点为左子节点。
 * <p>
 * 给出遍历输出 S，还原树并返回其根节点 root。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入："1-2--3--4-5--6--7"
 * 输出：[1,2,5,3,4,6,7]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入："1-2--3---4-5--6---7"
 * 输出：[1,2,5,3,null,6,null,4,null,7]
 * 示例 3：
 * <p>
 * <p>
 * <p>
 * 输入："1-401--349---90--88"
 * 输出：[1,401,null,349,88,90]
 *  
 * <p>
 * 提示：
 * <p>
 * 原始树中的节点数介于 1 和 1000 之间。
 * 每个节点的值介于 1 和 10 ^ 9 之间。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/recover-a-tree-from-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode1028_RecoverTreeFromPreorderTraversal {

    public static TreeNode recoverFromPreorder(String S) {
        List<Integer[]> list = new ArrayList<>();
        int pos = 0;
        while (pos < S.length()) {
            int level = 0;
            while (S.charAt(pos) == '-') {
                ++level;
                ++pos;
            }
            int value = 0;
            while (pos < S.length() && Character.isDigit(S.charAt(pos))) {
                value = value * 10 + (S.charAt(pos) - '0');
                ++pos;
            }
            list.add(new Integer[]{level, value});
        }
        TreeNode root = new TreeNode(list.get(0)[1]);
        Map<Integer, TreeNode> d = new HashMap<>();
        d.put(0, root);
        for (int i = 1; i < list.size(); i++) {
            Integer[] t = list.get(i);
            TreeNode node = new TreeNode(t[1]);
            d.put(t[0], node);
            if (d.get(t[0] - 1).left != null) {
                d.get(t[0] - 1).right = node;
            } else {
                d.get(t[0] - 1).left = node;
            }
        }
        return root;
    }

    int idx;

    public TreeNode recoverFromPreorder2(String S) {
        idx = 0;
        return helper(S, 0);
    }

    public TreeNode helper(String S, int depth) {
        if (idx >= S.length()) return null;
        int curDepth = 0;
        int k = idx;
        while (k < S.length() && S.charAt(k) == '-') {
            curDepth++;
            k++;
        }
        if (curDepth != depth) return null;
        idx = k;
        int val = 0;
        while (idx < S.length() && Character.isDigit(S.charAt(idx))) {
            val = val * 10 + (S.charAt(idx) - '0');
            idx++;
        }
        TreeNode node = new TreeNode(val);
        node.left = helper(S, depth + 1);
        node.right = helper(S, depth + 1);
        return node;
    }

    public static void main(String[] args) {
        String S = "1-2--3--4-5--6--7";
        recoverFromPreorder(S);
        /*List<Integer[]> list = new ArrayList<>();
        int pos = 0;
        while (pos < S.length()) {
            int level = 0;
            while (S.charAt(pos) == '-') {
                ++level;
                ++pos;
            }
            int value = 0;
            while (pos < S.length() && Character.isDigit(S.charAt(pos))) {
                value = value * 10 + (S.charAt(pos) - '0');
                ++pos;
            }
            list.add(new Integer[]{level, value});
        }
        System.out.println(list);*/
    }

}
