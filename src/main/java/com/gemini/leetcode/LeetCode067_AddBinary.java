package com.gemini.leetcode;

/**
 * com.gemini.leetcode.LeetCode067_AddBinary
 *
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 *
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 *
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * @author zhanghailin
 */
public class LeetCode067_AddBinary {

    //把 a 和 b 转换成整型数字 x 和 y，在接下来的过程中，x 保存结果，y 保存进位。
    //当进位不为 0 时
    //计算当前 x 和 y 的无进位相加结果：answer = x ^ y
    //计算当前 x 和 y 的进位：carry = (x & y) << 1
    //完成本次循环，更新 x = answer，y = carry
    //
    //作者：LeetCode-Solution
    //链接：https://leetcode-cn.com/problems/add-binary/solution/er-jin-zhi-qiu-he-by-leetcode-solution/
    //来源：力扣（LeetCode）
    //著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int carry = 0; // 进位
        for(int i = a.length() - 1, j = b.length() - 1;i >= 0 || j >= 0; i--, j--) {
            int sum = carry;
            sum += i >= 0 ? a.charAt(i) - '0' : 0;
            sum += j >= 0 ? b.charAt(j) - '0' : 0;
            ans.append(sum % 2); // 结果为1或者0
            carry = sum / 2; // 判断是否进位
        }
        ans.append(carry == 1 ? carry : "");
        return ans.reverse().toString(); // 反转
    }
}
