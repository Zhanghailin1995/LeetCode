package com.gemini.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * com.gemini.leetcode.tree.LeetCode094_InOrderTraversal
 * <p>
 * 给定一个二叉树，返回它的中序 遍历。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * <p>
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * 前序遍历是指，对于树中的任意节点来说，先打印这个节点，然后再打印它的左子树，最后打印它的右子树。
 * 中序遍历是指，对于树中的任意节点来说，先打印它的左子树，然后再打印它本身，最后打印它的右子树。
 * 后序遍历是指，对于树中的任意节点来说，先打印它的左子树，然后再打印它的右子树，最后打印这个节点本身。
 *
 * @author zhanghailin
 */
public class LeetCode094_InOrderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    public void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);

    }

    // 找当前节点的左子树的最右子节点，并将最右子节点指向当前节点
    // 当前节点没有左子节点的时候记录当前节点，当前节点的前置节点没有右子节点时记录当前节点
    public List<Integer> morrisInOrderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode cur = root;
        List<Integer> res = new ArrayList<>();
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    res.add(cur.val);
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        LeetCode094_InOrderTraversal inOrderTraversal = new LeetCode094_InOrderTraversal();
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
        TreeNode root = Tool.createTree(array);
        List<Integer> list = inOrderTraversal.morrisInOrderTraversal(root);
        System.out.println(list.toString());
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                '}';
    }
}

class Tool {
    /* * 构建完全二叉树 */
    public static TreeNode createTree(int[] array) {
        List<TreeNode> nodeList = new LinkedList<>();

        for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
            nodeList.add(new TreeNode(array[nodeIndex]));
        }
        for (int parentIndex = 0; parentIndex <= array.length / 2 - 1; parentIndex++) {
            nodeList.get(parentIndex).left = nodeList.get(parentIndex * 2 + 1);
            //防止是非完全二叉树
            if ((parentIndex * 2 + 2) < array.length) {
                nodeList.get(parentIndex).right = nodeList.get(parentIndex * 2 + 2);
            }
        }

        return nodeList.get(0);
    }
}
