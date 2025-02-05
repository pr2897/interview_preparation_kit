package org.learning.dsa.binary_search;

public class Algorithm {
    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,3,5,9,12};
        int target = 9;

        System.out.println(BinarySearch.recursiveBinarySearch(nums, 0, nums.length-1, target));
        System.out.println(BinarySearch.iterativeBinarySearch(nums, target));

        int[] arr = new int[] {1,2, 2, 3};
        int x = 2;

        System.out.println(BinarySearch.lower_bound(arr,x));
    }

    static class BinarySearch {
        // TC: O(logN)
        // SC: O(1) + recursion stack
        public static int recursiveBinarySearch(int[] nums, int low, int high, int target) {
            if (low > high) return -1;
            int mid = low + (high-low)/2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) return recursiveBinarySearch(nums, low, mid-1, target);
            else return recursiveBinarySearch(nums, mid+1, high, target);
        }

        // TC: O(logN)
        // SC: O(1)
        public static int iterativeBinarySearch(int[] nums, int target) {
            int low = 0, high = nums.length - 1;

            while (low <= high) {
                int mid = low + (high-low)/2;
                if (nums[mid] == target) return mid;
                else if (nums[mid] > target) high = mid-1;
                else low = mid+1;
            }

            return -1;
        }

        /** The lower bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than or equal to a given key i.e. x.
            If no such index is found, return the size of the array.
         * @param nums
         * @param target
         * @return
         */
        public static int lower_bound(int[] nums, int x) {
            int n = nums.length;
            int low = 0, high = n-1;

            int res = -1;

            while (low <= high) {
                int  mid = low + (high-low)/2;
                if (nums[mid] >= x) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid+1;
                }
            }

            if (res == -1) return n;
            return res;
        }

        /**
         * The upper bound algorithm finds the first or the smallest index in a sorted array where the value at that index is greater than a given key i.e. x.
         * If no such index is found, return the size of the array.
         * @param nums
         * @param x
         * @return
         */
        public static int upper_bound(int[] nums, int x) {
            int n = nums.length;
            int low = 0, high = n - 1;
            int res = -1;

            while (low <= high) {
                int mid = low + (high-low)/2;

                if (nums[mid] > x) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            if (res == -1) return n;
            return res;
        }
    }
}
