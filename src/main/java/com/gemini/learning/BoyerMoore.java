package com.gemini.learning;

/**
 * com.gemini.learning.BoyerMoore
 *
 * @author zhanghailin
 */
public class BoyerMoore {

    private static final int SIZE = 256;

    // char[] b 是需要查找的子串
    private void generateBC(char[] b, int[] bc) {
        for (int i = 0; i < SIZE; ++i) {
            bc[i] = -1;
        }
        int len = b.length;
        for (int i = 0; i < len; i++) {
            int ascii = (int) b[i];
            bc[ascii] = i;
        }
    }

    // char[] a 是主串，char[] b是需要查找的子串
    public int bm(char[] a, char[] b) {
        int[] bc = new int[SIZE]; // 记录模式串中每个字符最后出现的位置
        generateBC(b, bc);// 构建坏字符哈希表
        int i = 0, n = a.length, m = b.length; // i表示主串与模式串对齐的第一个字符
        while (i <= n - m) {
            int j;
            for (j = m - 1; j >= 0; --j) { // 模式串从后往前匹配
                if (a[i + j] != b[j]) break; // 坏字符对应模式串中的下标是j
            }
            if (j < 0) {
                return i; // 匹配成功，返回主串与模式串第一个匹配的字符的位置
            }
            // 这里等同于将模式串往后滑动j-bc[(int)a[i+j]]位
            i = i + (j - bc[(int) a[i + j]]);
        }
        return -1;
    }

    public static void main(String[] args) {
        int i;
        for (i = 0; i <= 5; i++) {
            System.out.println(i);
        }
        System.out.println(i);
    }
}
