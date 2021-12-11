package com.gemini.leetbook.primary;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * com.gemini.leetbook.primary.IntersectOfTwoArrays2
 * <p>
 * 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * <p>
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 * <p>
 * <p>
 * 说明：
 * <p>
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * <p>
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @author zhanghailin
 */
public class IntersectOfTwoArrays2 {

    public void intersect(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }

        Set<Integer> set2 = new HashSet<>();
        for (int i : nums2) {
            set2.add(i);
        }

        set1.retainAll(set2);

        System.out.println(Arrays.toString(set1.toArray()));


    }

    public static void main(String[] args) {
        int[] num1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};
        IntersectOfTwoArrays2 intersectOfTwoArrays2 = new IntersectOfTwoArrays2();
        intersectOfTwoArrays2.intersect(num1, nums2);
    }
}
