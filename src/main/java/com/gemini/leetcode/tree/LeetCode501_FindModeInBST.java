package com.gemini.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * com.gemini.leetcode.tree.LeetCode501_FindModeInBST
 * <p>
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * <p>
 * 假定 BST 有如下定义：
 * <p>
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 * <p>
 * 1
 * \
 * 2
 * /
 * 2
 * 返回[2].
 * <p>
 * 提示：如果众数超过1个，不需考虑输出顺序
 * <p>
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-mode-in-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode501_FindModeInBST {

    int max = 0;
    List<Integer> modes = new LinkedList<>();
    int currentValue;
    int count;

    public int[] findMode(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        currentValue = root.val;
        count = 0;

        helper(root);
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }
        return result;
    }

    public void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.left);
        if (currentValue == root.val) {
            count++;
        } else {
            count = 1;
            currentValue = root.val;
        }

        if (count == max) {
            modes.add(currentValue);
        } else if (count > max) {
            max = count;
            modes.clear();
            modes.add(currentValue);
        }

        helper(root.right);

    }

}
