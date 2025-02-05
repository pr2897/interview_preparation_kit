package org.learning.dsa.utils;

public class ArrayUtils {
    // TC: O(1)
    // SC: O(1)
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // TC: O(N)
    // SC: O(1)
    public static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }
}
