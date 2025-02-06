package org.learning.dsa.binary_search;

public class Problems {
    public static void main(String[] args) {
        int[] nums = new int[] {3, 4, 4, 7, 8, 10};
        int x = 5;

        System.out.println(FloorCeil.optimal(nums, x));
    }

    private static class FloorCeil {
        public static int[] optimal(int[] nums, int x) {
            int[] result = new int[] {-1,-1};
            int n = nums.length;
            int low = 0, high = n - 1;

            // floor <= x
            while (low <= high) {
                int mid = low + (high-low)/2;
                int curr = nums[mid];
                if (curr > x) {
                    high = mid - 1;
                } else {
                    result[0] = curr;
                    low = mid+1;
                }
            }

            low = 0;
            high = n - 1;

            // ceil >= x
            while (low <= high) {
                int mid = low + (high-low)/2;
                int curr = nums[mid];
                if (curr >= x) {
                    result[1] = curr;
                    high = mid - 1;
                } else {
                    low = mid+1;
                }
            }

            return result;
        }
    }

    private static class FirstAndLastOccurrence {
        public static int[] optimal(int[] nums, int x) {
            int[] result = new int[] {-1,-1};
            int n = nums.length;
            int low = 0, high = n - 1;

            // first
            while (low <= high) {
                int mid = low + (high-low)/2;
                int curr = nums[mid];
                if (curr >= x) {
                    if (curr == x) result[0] = mid;
                    high = mid - 1;
                } else {
                    low = mid+1;
                }
            }

            low = 0;
            high = n - 1;

            // last
            while (low <= high) {
                int mid = low + (high-low)/2;
                int curr = nums[mid];
                if (curr == x) {
                    result[1] = mid;
                    low = mid+1;
                } else if (curr < x) {
                    low = mid+1;
                } else {
                    high = mid - 1;
                }
            }

            return result;
        }
    }
}
