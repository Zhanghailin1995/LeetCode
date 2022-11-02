package com.gemini.leetcode.tree;

import com.gemini.support.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * com.gemini.leetcode.tree.LeetCode437_PathSum3
 *
 * @author zhanghailin
 */
//437. 路径总和 III
//给定一个二叉树，它的每个结点都存放着一个整数值。
//
//找出路径和等于给定数值的路径总数。
//
//路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
//
//二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
//
//示例：
//
//root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
//
//      10
//     /  \
//    5   -3
//   / \    \
//  3   2   11
// / \   \
//3  -2   1
//
//返回 3。和等于 8 的路径有:
//
//1.  5 -> 3
//2.  5 -> 2 -> 1
//3.  -3 -> 11
public class LeetCode437_PathSum3 {

    int ans = 0;

    // 这种题目难度是简单，我也是佛了，做不来，思路是抄的评论里一个c++的思路
    // 双重递归，先遍历每个节点，在以每个节点为根查找路径
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        sum(root, sum);
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return ans;
    }

    public void sum(TreeNode root, int sum) {
        if (root == null) return;
        sum -= root.val;
        if (sum == 0) {
            ans++;
        }
        sum(root.left, sum);
        sum(root.right, sum);
    }

    // 下面介绍o(n)解法，prefix sum 前缀和
    // 前缀和怎么应用呢？
    //
    //如果两个数的前缀总和是相同的，那么这些节点之间的元素总和为零。进一步扩展相同的想法，如果前缀总和currSum，在节点A和节点B处相差target，则位于节点A和节点B之间的元素之和是target。
    //

    // // key是前缀和, value是大小为key的前缀和出现的次数
    private Map<Integer, Integer> prefixSumCount;
    private int target;

    public int pathSum2(TreeNode root, int sum) {
        prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        this.target = sum;
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }
        // 当前路径上的和
        int curPathSum = preSum + root.val;
        int curRes = 0;
        // 如果在前缀路径和中发现有值为curPathSum-target的（可能会>1，即多条前缀路径）
        // 那么路径和为curPathSum的路径的最后一个节点的下一个节点到当前节点的和等于target（画个图就知道了）
        //          root->  o 10               prefixSumCount有 10:1 15:1
        //                  |                     curPathSum  为  18
        //                  o 5              curPathSum-target 为 18-8 = 10
        //                 /              因此路径和为10的路径的最后一个节点（10）的下一个节
        //       cur->    o 3             点（5）到cur（3）为一条满足和为target（8）的路径
        curRes += prefixSumCount.getOrDefault(curPathSum - target, 0);
        // 进入左右子树前，先把 本路径的和 的计数器更新（+1）（这个路径会是左右子树的前缀）
        prefixSumCount.put(curPathSum, prefixSumCount.getOrDefault(curPathSum, 0) + 1);
        curRes += dfs(root.left, curPathSum);
        curRes += dfs(root.right, curPathSum);
        // 左右子树遍历完后，把 本路径的和 的计数器更新（-1）（因为这个路径不可能是别的路径的前缀了）
        // 回溯样板代码，状态回复 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.replace(curPathSum, prefixSumCount.get(curPathSum) - 1);
        return curRes;
    }


    public static void main(String[] args) {
        TreeCodec treeCodec = new TreeCodec();
        TreeNode treeNode = treeCodec.deserialize("1,-2,-3,null,null,null,null");
        LeetCode437_PathSum3 pathSum3 = new LeetCode437_PathSum3();
        pathSum3.pathSum2(treeNode,-1);
    }
}
