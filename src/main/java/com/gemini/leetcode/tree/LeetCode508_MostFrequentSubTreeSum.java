package com.gemini.leetcode.tree;

import java.util.*;

/**
 * com.gemini.leetcode.tree.LeetCode508_MostFrequentSubTreeSum
 *
 * @author zhanghailin
 */
//508. 出现次数最多的子树元素和
//给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
//
//你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
//
//
//
//示例 1：
//输入:
//
//  5
// /  \
//2   -3
//返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
//
//示例 2：
//输入：
//
//  5
// /  \
//2   -5
//返回 [2]，只有 2 出现两次，-5 只出现 1 次。
//讲道理，第一次我没有看懂题目的意思，
//  5
// /  \
//2   -5
// 子树和 2、-5、2+5+-5 = 2，2出现两次，返回2
public class LeetCode508_MostFrequentSubTreeSum {

    Map<Integer, Integer> map = new HashMap<>();
    int max = 0;

    // 解题思路
    // 当前节点的值+其左子树的和+其右子树的和
    //
    // 那么我们就很容易的可以描述出其递归关系式，这里我们定义函数findSum
    //
    // 函数作用：计算当前子树的和
    // 输入：当前子树的根节点
    // 输出：当前子树的和
    // int sum = root.val + findSum(root.left) +findSum(root.right);
    //
    // 作者：ming-zhi-shan-you--m9RfkvKDad
    // 链接：https://leetcode-cn.com/problems/most-frequent-subtree-sum/solution/508-chu-xian-ci-shu-zui-duo-de-zi-shu-yuan-su-he-b/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null)
            return new int[0];

        helper(root);
        // 然后求出map中value最大值对应的Key
        List<Integer> res = new LinkedList<>();
        for (Integer i : map.keySet()) {
            if (map.get(i) == max)
                res.add(i);
        }
        // List转数组
        int[] resArr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }
        return resArr;


    }

    public int helper(TreeNode root) {
        if (root == null)
            return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        // 当前节点为根的元素和
        int val = left + right + root.val;
        map.put(val, map.getOrDefault(val, 0) + 1);
        max = Math.max(max, map.get(val));
        return val;
    }

    int maxCount = 0;

    public int[] findFrequentTreeSum2(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<>();
        findFrequentTreeSum(root, map, res);
        int[] ints = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ints[i] = res.get(i);
        }
        return ints;
    }

    private int findFrequentTreeSum(TreeNode root, HashMap<Integer, Integer> map, List<Integer> res) {
        if (root == null) return 0;
        int leftSum = findFrequentTreeSum(root.left, map, res);
        int rightSum = findFrequentTreeSum(root.right, map, res);
        int sum = leftSum + rightSum + root.val;
        int count = map.getOrDefault(sum, 0) + 1;
        if (count > maxCount) {
            maxCount = count;
            res.clear();
        }
        if (count == maxCount) {
            res.add(sum);
        }
        map.put(sum, count);
        return sum;
    }


}
