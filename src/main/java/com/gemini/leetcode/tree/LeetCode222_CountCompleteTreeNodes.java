package com.gemini.leetcode.tree;

/**
 * com.gemini.leetcode.tree.LeetCode222_CountCompleteTreeNodes
 * 222. 完全二叉树的节点个数
 * 给出一个完全二叉树，求出该树的节点个数。
 * <p>
 * 说明：
 * <p>
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * 1
 * / \
 * 2   3
 * / \  /
 * 4  5 6
 * <p>
 * 输出: 6
 *
 * @author zhanghailin
 */
public class LeetCode222_CountCompleteTreeNodes {

    // 最简单解法，递归，计算左右子树的节点个数
    public int countNodes(TreeNode root) {
        return root != null ? 1 + countNodes(root.right) + countNodes(root.left) : 0;
    }

    // Return tree depth in O(d) time.
    public int computeDepth(TreeNode node) {
        int d = 0;
        while (node.left != null) {
            node = node.left;
            ++d;
        }
        return d;
    }

    // 最后一层节点全部靠左，用二分查找即可确认最后一层有多少个节点
    public boolean exists(int idx, int d, TreeNode node) {
        int left = 0, right = (int) Math.pow(2, d) - 1;
        int pivot;
        for (int i = 0; i < d; ++i) {
            pivot = left + (right - left) / 2;
            if (idx <= pivot) {
                node = node.left;
                right = pivot;
            } else {
                node = node.right;
                left = pivot + 1;
            }
        }
        return node != null;
    }

    public int countNodes2(TreeNode root) {
        // if the tree is empty
        if (root == null) return 0;

        int d = computeDepth(root);
        // if the tree contains 1 node
        if (d == 0) return 1;


        int left = 1, right = (int) Math.pow(2, d) - 1;
        int pivot;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            if (exists(pivot, d, root)) left = pivot + 1;
            else right = pivot - 1;
        }

        // The tree contains 2**d - 1 nodes on the first (d - 1) levels
        // and left nodes on the last level.
        return (int) Math.pow(2, d) - 1 + left;
    }

    //再来回顾一下满二叉的节点个数怎么计算，如果满二叉树的层数为h，则总节点数为：2^h - 1.
    //那么我们来对root节点的左右子树进行高度统计，分别记为left和right,有以下两种结果：
    //
    //left == right。这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。所以左子树的节点总数我们可以直接得到，是2^left - 1，加上当前这个root节点，则正好是2^left。再对右子树进行递归统计。
    //left != right。说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。同理，右子树节点+root节点，总数为2^right。再对左子树进行递归查找。
    // 简单地说，left == right,左子树是满二叉树；left !=right，右子树是满二叉树
    //
    public int countNodes3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countLevel(root.left);
        int right = countLevel(root.right);
        if (left == right) {
            return countNodes3(root.right) + (1 << left);
        } else {
            return countNodes3(root.left) + (1 << right);
        }
    }

    private int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }


}
