package org.learning.dsa.arrays;

import org.learning.dsa.utils.ArrayUtils;

public class Algorithms {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 1, 1, 2, 0, 1, 0, 2, 2, 0};
    }

    public static void dutchNationalFlag(int[] arr) {
        int n = arr.length;
        int low = 0, mid = 0, high = n - 1;
        while (mid <= high) {
            if (arr[mid] == 0) {
                ArrayUtils.swap(arr, low, mid);
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                mid++;
            } else {
                ArrayUtils.swap(arr, mid, high);
                high--;
            }
        }
    }

    public static int kadaneAlgorithm(int[] nums) {
        int maxi = Integer.MIN_VALUE;

        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum > maxi) maxi = sum;

            if (sum < 0) sum = 0;
        }

        return maxi;
    }

    public static int knothMorisMajorityElement(int[] nums) {
        int major = nums[0];
        int cnt = 1;

        for (int i = 1; i < nums.length; i++) {
            if (cnt == 0) {
                major = nums[i];
                cnt++;
                continue;
            }

            if (nums[i] == major) {
                cnt++;
            } else {
                cnt--;
            }

        }
        return major;
    }

}
