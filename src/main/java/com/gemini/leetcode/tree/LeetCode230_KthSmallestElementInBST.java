package com.gemini.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * com.gemini.leetcode.tree.LeetCode230_KthSmallestElementInBST
 * <p>
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 *    2
 * Output: 1
 * Example 2:
 * <p>
 * Input: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * Output: 3
 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode230_KthSmallestElementInBST {

    List<Integer> inorder = new ArrayList<>();

    public int kthSmallest(TreeNode root, int k) {
        inorder(root, k);
        return inorder.get(k - 1);
    }

    private void inorder(TreeNode root, int k) {
        if (root == null || inorder.size() == k) {
            return;
        }
        inorder(root.left, k);
        inorder.add(root.val);
        if (inorder.size() == k) return;
        inorder(root.right, k);
    }

    // 从上面的解法中提炼，其实就是中序遍历，但是我并不需要遍历完成，只要找到遍历到第K个数之后就可以结束递归了

    int num = 0;
    int res;

    public int kthSmallest2(TreeNode root, int k) {
        inorderTraversal(root, k);
        return res;
    }

    private void inorderTraversal(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, k);
        num++;
        if (num == k) {
            res = node.val;
            return;
        }
        inorderTraversal(node.right, k);
    }

}
