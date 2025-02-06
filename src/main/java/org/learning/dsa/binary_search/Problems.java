package org.learning.dsa.binary_search;

public class Problems {
    public static void main(String[] args) {
        int[] nums = new int[] {4, 5, 6, 7, 0, 1, 2, 3};

        System.out.println(MinimumInSortedArray.optimal(nums));
    }

    // https://takeuforward.org/plus/dsa/binary-search/logic-building/floor-and-ceil-in-sorted-array
    static class FloorCeil {
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

    // https://takeuforward.org/plus/dsa/binary-search/logic-building/first-and-last-occurrence
    static class FirstAndLastOccurrence {
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

    // https://takeuforward.org/plus/dsa/binary-search/logic-building/search-in-rotated-sorted-array-i
    static class SearchInRotatedSortedArray {
        public static int optimal(int[] nums, int k) {
            int n = nums.length;
            int low = 0, high = n - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == k) return mid;
                if (nums[low] <= nums[mid]) { // left sorted
                    if (nums[mid] >= k && nums[low] <= k) {
                        high = mid - 1;
                    }
                    else {
                        low = mid + 1;
                    }
                } else {  // right sorted
                    if (nums[mid] <= k && k <= nums[high]) {
                        low = mid + 1;
                    }
                    else {
                        high = mid - 1;
                    }
                }
            }

            return -1;
        }
    }

    // https://takeuforward.org/plus/dsa/binary-search/logic-building/search-in-rotated-sorted-array-2
    static class SearchInRotatedSortedArrayDuplicate {
        private static int optimal(int[] nums, int k) {
            int n = nums.length;
            int low = 0, high = n - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] == k) return mid;

                if (nums[mid] == nums[low] && nums[mid] == nums[high]) {
                    low += 1;
                    high -= 1;
                    continue;
                }

                if (nums[low] <= nums[mid]) { // left sorted
                    if (nums[mid] >= k && nums[low] <= k) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {  // right sorted
                    if (nums[mid] <= k && k <= nums[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }

            return -1;
        }
    }

    // https://takeuforward.org/plus/dsa/binary-search/logic-building/find-minimum-in-rotated-sorted-array
    static class MinimumInSortedArray {
        public static int optimal(int[] nums) {
            int n = nums.length;
            int ans = nums[0];

            int low = 0, high = n - 1;

            while (low <= high) {
                int mid = low + (high - low ) / 2;

                ans = Math.min(ans, nums[mid]);
                if (nums[low] <= nums[mid]) { // left sorted
                    ans = Math.min(ans, nums[low]);
                    low = mid + 1;
                } else { // right sorted
                    high = mid - 1;
                }
            }

            return ans;
        }
    }

    // https://takeuforward.org/plus/dsa/binary-search/logic-building/find-out-how-many-times-the-array-is-rotated
    static class CountNumberOfTimesSortedArrayIsRotated {
        public static int optimal(int[] nums) {
            int n = nums.length;
            int ans = nums[0];

            int low = 0, high = n - 1, index = 0;

            while (low <= high) {
                int mid = low + (high - low ) / 2;

                if(ans > nums[mid]) {
                    ans = nums[mid];
                    index = mid;
                }

                if (nums[low] <= nums[mid]) { // left sorted
                    if(nums[low] < ans) {
                        ans = nums[low];
                        index = low;
                    }
                    low = mid + 1;
                } else { // right sorted
                    high = mid - 1;
                }
            }

            return index;
        }
    }

}
