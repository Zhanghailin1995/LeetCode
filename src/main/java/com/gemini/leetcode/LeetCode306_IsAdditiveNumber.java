package com.gemini.leetcode;

/**
 * com.gemini.leetcode.LeetCode306_IsAdditiveNumber
 *
 * 累加数是一个字符串，组成它的数字可以形成累加序列。
 *
 * 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 *
 * 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。
 *
 * 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 *
 * 示例 1:
 *
 * 输入: "112358"
 * 输出: true
 * 解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * 示例 2:
 *
 * 输入: "199100199"
 * 输出: true
 * 解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199
 * 进阶:
 * 你如何处理一个溢出的过大的整数输入?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/additive-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhanghailin
 */
public class LeetCode306_IsAdditiveNumber {


    // TODO 重做
    public boolean isAdditiveNumber(String num) {
        return backtrack2(num, 0, 0, 0, 0);
        //第一个0：从num的0号位置开始计算
        //第二个0：前面两个数的总和初始为0
        //第三个0：前面一个数的值初始为0
        //第四个0：当前搜索的数字是第几个数（需要先找到前两个再对第三个及以后进行分析，所以需要这个k）

    }

    // 说实话，很难懂，但是还是尝试解释一下，我感觉这个跟回溯不太一样
    // 一个数如果是累加数，那么假设最后一个累加值 为 f(n) = f(n-1)+f(n-2)
    // 那么其实 截去f(n) 这一段，剩下的也应该是累加数，排除这个累加序列只有三个累加值的情况
    // 输入 199100199
    // f(0) = 1, f(1) = 9 1+9 = 10 ,但是 后面的str并不是10,那么f(2) 完后延伸一位 99 ,计算 f(1) +f(2) = f(3) = 100,很好，往后递归调用
    private boolean backtrack(String num, int index, long preSum, long preNum, int k) {
        if (k > 2 && index >= num.length()) return true;//index是当前数字的开始处
        for (int len = 1; len + index <= num.length(); len++) {//len是当前数字的长度
            long f = isSum(preSum, num, index, index + len - 1, k);//preSum是之前二者的和
            if (f >= 0) {
                if (backtrack(num, index + len, f + preNum, f, k + 1)) return true;
            }
        }
        return false;
    }

    private long isSum(long sum, String num, int l, int h, int k) {
        if (num.charAt(l) == '0' && l < h) return -1;
        long s = 0;
        while (l <= h) {
            s = s * 10 + num.charAt(l) - '0';
            l++;
        }//num的从l到h的这一段字符串对应的数值

        if (k < 2) return s;
        return sum == s ? s : -1;
    }


    private boolean backtrack2(String s, int p, long pre1, long pre2, int deep) {
        if (deep > 2 && p >= s.length()) return true;
        for (int i = 1; i + p <= s.length(); i++) {
            String sub = s.substring(p, p + i);
            if (sub.charAt(0) == '0' && sub.length() > 1) return false;
            long num = parseLong(sub, i);
            if (deep < 2 || num == (pre1 + pre2)) {
                if (backtrack2(s, p + i, pre2, num, deep + 1)) return true;
            }
        }
        return false;
    }

    private long parseLong(String str, int len) {
        long s = 0;
        for (int i = 0; i < len; i++) {
            s = s * 10 + str.charAt(i) - '0';
        }
        return s;
    }

    public static void main(String[] args) {
        LeetCode306_IsAdditiveNumber isAdditiveNumber = new LeetCode306_IsAdditiveNumber();
        System.out.println(isAdditiveNumber.isAdditiveNumber("199100199"));
        //System.out.println("111".substring(2, 3));
    }
}
