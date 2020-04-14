package com.gemini.learning;

import java.util.Arrays;

/**
 * com.gemini.learning.PrintPermutations
 *
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
            swap(data, k, i);
            printPermutations(data, k + 1);
            swap(data, i, k);
        }
    }

    private void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        PrintPermutations printPermutations = new PrintPermutations();
        int[] arr = {1, 2, 3};
        printPermutations.printPermutations(arr, 0);
    }
}
