package org.learning.dsa.sorting;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] input = new int[]{3,1,2,4,1,5,2,6,4};
        quickSort(input, 0, input.length-1);
        System.out.println(Arrays.toString(input));
    }

    // TC: O(N*logN)
    // SC: O(1)
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pIdx = findPartition(arr, low, high);
            quickSort(arr, low, pIdx-1);
            quickSort(arr, pIdx+1, high);
        }
    }

    // find the partition index at place the element at correct place.
    private static int findPartition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low, j = high;

        while (i < j) {
            // find the first element greater than pivot from left
            while (arr[i] <= pivot && i < high) i++; // i < high: because i post i++ can go out of low-high boundary

            // find the first element smaller than pivot from right
            while (arr[j] > pivot && j > low) j--; // j > low: likewise, j post j-- can go out of low-high boundary

            // if didn't cross each other swap.
            if (i < j) swap(arr, i, j);
        }

        // partition element is at low, put the index at partition at correct place.
        swap(arr, low, j);

        // j always points to partition index.
        return j;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
