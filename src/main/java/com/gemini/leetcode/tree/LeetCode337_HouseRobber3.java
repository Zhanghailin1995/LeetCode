package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

/**
 * com.gemini.leetcode.tree.LeetCode337_HouseRobber3
 * <p>
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3,null,3,null,1]
 * <p>
 * 3
 * / \
 * 2   3
 * \   \
 * 3   1
 * <p>
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 * <p>
 * 输入: [3,4,5,1,3,null,1]
 * <p>
 * 3
 * / \
 * 4   5
 * / \   \
 * 1   3   1
 * <p>
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * @author zhanghailin
 */
public class LeetCode337_HouseRobber3 {

    // dp解法，找状态转移方程
    // 当前节点可以偷的钱的解法 max node.val + node.left.left.val + node.left.right.val + node.right.left.val + node.right.right.val , node.left.val + node.right.val
    public int rob(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }

        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }

        return Math.max(money, rob(root.left) + rob(root.right));

    }

    // 记忆优化
    /*public int rob1(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robInternal(root, memo);
    }

    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) return 0;
        if (memo.containsKey(root)) return memo.get(root);
        int money = root.val;

        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }*/

    //用后序遍历，先把子树算完并存储到子结点上，这样就可以直接调用子树结果，减少重复计算
    //太屌了，学怪，dp加树，我感觉难度已经非常高了
    public int rob1(TreeNode root) {
        if(root==null){
            return 0;
        }
        robInternal(root);
        return root.val;
    }

    public void robInternal(TreeNode root) {
        if (root.left!=null){
            robInternal(root.left);
        }
        if (root.right!=null){
            robInternal(root.right);
        }
        int a = root.val;
        int b = 0;

        if (root.left != null) {
            b += root.left.val;
            if (root.left.left!=null) {
                a += root.left.left.val;
            }
            if (root.left.right!=null) {
                a += root.left.right.val;
            }
        }
        if (root.right != null) {
            b += root.right.val;
            if (root.right.left!=null) {
                a += root.right.left.val;
            }
            if (root.right.right!=null) {
                a += root.right.right.val;
            }
        }
        root.val = Math.max(a, b);
    }

    public int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        postorder(root);
        return root.val;
    }

    //用后序遍历，先把子树算完并存储到子结点上，这样就可以直接调用子树结果，减少重复计算
    public void postorder(TreeNode root) {
        if (root.left != null) {
            postorder(root.left);
        }
        if (root.right != null) {
            postorder(root.right);
        }
        int res1 = 0;
        int res2 = root.val;
        if (root.left != null) {
            res1 = res1 + root.left.val;
            if (root.left.left != null) {
                res2 = res2 + root.left.left.val;
            }
            if (root.left.right != null) {
                res2 = res2 + root.left.right.val;
            }
        }
        if (root.right != null) {
            res1 = res1 + root.right.val;
            if (root.right.left != null) {
                res2 = res2 + root.right.left.val;
            }
            if (root.right.right != null) {
                res2 = res2 + root.right.right.val;
            }
        }
        root.val = Math.max(res1, res2);
    }


}
