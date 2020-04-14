package com.gemini.learning;

import java.util.Arrays;

/**
 * com.gemini.learning.PrintPermutations
 * <p>
 * 打印数组全排列，递归、回溯的思想
 *
 * @author zhanghailin
 */
public class PrintPermutations {

    public void printPermutations(int[] data, int k) {
        int len = data.length;
        if (k == len) {
            System.out.println(Arrays.toString(data));
        }
        for (int i = k; i < len; i++) {
            if (!needSwap(data, k, i)) {
                continue;
            }
            swap(data, k, i);
            printPermutations(data, k + 1);
            swap(data, i, k);
        }
    }

    // 没看懂这个判断的含义
    //0---index---i---len-1
    //
    //考虑将num[index]与num[i]交换，若在index--i-1中已经存在num[i]，则num[i]不应该与num[index]交换，因为交换依次为按顺序交换，
    //i取值从index一直到i,若这中间出现过num[i]，说明num[index]已经被交换过，若再次交换便会出现重复。
    //————————————————
    //例如 num = [1 2 2 3 4] ：
    //
    //index=1   i的变化范围从1---4，当i=2时，此时num[i]=2,而在index=0---i-1=1之间num[1]=2,说明num[1]已经出现在num[0]位置上，若num[2]再次与num[0]交换，则会出现重复排列。
    //————————————————
    //例如abb，第一个数与后面两个数交换得bab，bba。
    //然后abb中第二个数和第三个数相同，就不用交换了。但是对bab，第二个数和第三个数不 同，则需要交换，得到bba。由于这里的bba和开始第一个数与第三个数交换的结果相同了，因此这个方法不行。
    //换种思维，对abb，第一个数a与第二个数b交换得到bab，然后考虑第一个数与第三个数交换，此时由于第三个数等于第二个数，所以第一个数就不再用与第三个数交换了。
    //再考虑bab，它的第二个数与第三个数交换可以解决bba。此时全排列生成完毕！
    //比较难理解
    private boolean needSwap(int[] data, int i, int j) {
        for (int m = i; m < j; m++) {
            if (data[m] == data[j]) {
                System.out.println(Arrays.toString(data));
                System.out.println(String.format("i:%d,m:%d,j:%d", i, m, j));
                return false;
            }
        }
        return true;
    }

    // 这是错误的方法
    private boolean needSwap1(int[] data, int i, int j) {
        if (i == j) return true;
        if (data[i] == data[j]) {
            System.out.println(Arrays.toString(data));
            System.out.println(String.format("i:%d,j:%d", i, j));
            return false;
        }
        return true;
    }

    private void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        PrintPermutations printPermutations = new PrintPermutations();
        int[] arr = {1, 2, 2, 3};
        printPermutations.printPermutations(arr, 0);

    }
}
