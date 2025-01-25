package org.learning.dsa.sorting;

import java.util.Arrays;


public class MergeSort {
    public static void main(String[] args) {
        int[] input = new int[]{3,1,2,4,1,5,2,6,4};
        mergeSort(input, 0, input.length-1);
        System.out.println(Arrays.toString(input));
    }

    // TC: O(N * logN)
    // SC: O(N)
    private static void mergeSort(int[] ints, int start, int end) {
        if (start >= end) return;

        int mid = (start+end)/2;
        mergeSort(ints, start, mid);
        mergeSort(ints, mid+1, end);

        merge(ints, start, mid, end);
    }

    // merge the two partition array in sorted order.
    private static void merge(int[] ints, int start, int mid, int end) {
        int[] items = new int[end-start+1];
        int k = 0;

        int s = start, m = mid+1, e = end;
        while (s <= mid && m <= end) {
            if (ints[s] <= ints[m]) {
                items[k++] = ints[s++];
            } else {
                items[k++] = ints[m++];
            }
        }

        while (s <= mid) {
            items[k++] = ints[s++];
        }

        while (m <= end) {
            items[k++] = ints[m++];
        }

        for (int i=0;i<items.length;i++) {
            ints[start+i] = items[i];
        }
    }
}
