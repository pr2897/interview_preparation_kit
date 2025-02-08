package org.learning.dsa.binary_search;

import java.util.Arrays;
import java.util.Collections;

public class Problems {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6};

        System.out.println(SingleElementInSortedArray.optimal(nums));

        System.out.println(SquareRoot.optimal(28));

        System.out.println(NthRoot.optimal(9, 512));

        System.out.println(SmallestDivisor.optimal(new int[]{1, 2, 3, 4, 5}, 8));

        int[] arr = new int[] {7, 7, 7, 7, 13, 11, 12, 7};
        System.out.println(MinimumDayToMakeMBouquets.brute(arr.length, arr, 3, 2));
        System.out.println(MinimumDayToMakeMBouquets.optimal(arr.length, arr, 3, 2));

        System.out.println(AggressiveCows.brute(new int[]{4, 2, 1, 3, 6}, 2));

        System.out.println(BookAllocation.brute(new int[]{12, 34, 67, 90}, 2));
        System.out.println(BookAllocation.optimal(new int[]{12, 34, 67, 90}, 2));
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

    // https://takeuforward.org/plus/dsa/binary-search/logic-building/single-element-in-sorted-array
    static class SingleElementInSortedArray {
        public static int optimal(int[] nums) {
            int n = nums.length, low = 0, high = n - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                boolean isUnique = true;
                if (mid > 0) isUnique &= nums[mid] != nums[mid-1];
                if (mid < n -1 ) isUnique &= nums[mid] != nums[mid+1];
                if (isUnique) return nums[mid];

                if (mid % 2 == 0) {
                    if (mid > 0 && nums[mid] == nums[mid+1]) low = mid + 1;
                    else high = mid - 1;
                } else {
                    if (mid < n - 1 && nums[mid] == nums[mid-1]) low = mid + 1;
                    else high = mid - 1;
                }
            }

            return 0;
        }
    }

    // https://takeuforward.org/plus/dsa/binary-search/on-answers/find-square-root-of-a-number
    static class SquareRoot {
        private static long optimal(long n) {
            if(n <= 0) return 0;
            long low = 1, high = n/2, res = 1;

            while (low <= high) {
                long mid = low + (high - low) / 2;

                if (mid * mid == n) return mid;
                else if (mid*mid < n) {
                    res = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            return res;
        }
    }

    // https://takeuforward.org/plus/dsa/binary-search/on-answers/find-nth-root-of-a-number
    static class NthRoot {
        public static int optimal(int N, int M) {
            int l = 1, r = M;
            while(l <= r) {
                int mid = (l+r)/2;
                int calc = func(mid, N,M);
                if(calc == 1) return mid;
                else if(calc ==2) r = mid-1;
                else l = mid+1;
            }
            return -1;
        }

        private static int func(int mid, int n, int m) {
            long ans = 1;
            for(int i=1;i<=n;i++) {
                ans *= mid;
                if (ans > m) return 2;
            }
            if(ans == m) return 1;
            return 0;
        }
    }

    // https://takeuforward.org/plus/dsa/binary-search/on-answers/find-the-smallest-divisor
    static class SmallestDivisor {
        public static int optimal(int[] nums, int limit) {
            int low = 1, high = nums[0];
            for (int c: nums) {
                if (c > high) high = c;
            }

            int res = high;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (cost(nums, mid) <= limit) {
                    res = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return res;
        }

        private static int cost(int[] nums, int x) {
            int sum = 0;
            for (int c: nums) {
                sum += (int) Math.ceil((double) c / (double) x);
            }
            return sum;
        }
    }

    // https://takeuforward.org/plus/dsa/binary-search/on-answers/minimum-days-to-make-m-bouquets
    static class MinimumDayToMakeMBouquets {

        // TC: O(N*N
        // SC: O(1)
        public static int brute(int n, int[] nums, int k, int m) {
            int max = nums[0];
            int min = nums[0];

            for (int c: nums) {
                if (c > max) max = c;
                if (c < min) min = c;
            }

            for (int day=min; day <= max; day++) {
                int count = 0;
                int bouquets = 0;

                for (int c: nums) {
                    if (day >= c) count++;
                    else {
                        bouquets += count / k;
                        count = 0;
                    }
                }

                bouquets += count / k;
                if (bouquets >= m)
                    return day;
            }


            return -1;
        }

        // TC: O(N* logN)
        // SC: O(1)
        public static int optimal(int n, int[] nums, int k, int m) {
            int high = nums[0];
            int low = nums[0];

            for (int c: nums) {
                if (c > high) high = c;
                if (c < low) low = c;
            }

            int ans = -1;

            while (low <= high) {
                int day = low + (high - low) / 2;
                int count = 0;
                int bouquets = 0;

                for (int c: nums) {
                    if (day >= c) count++;
                    else {
                        bouquets += count / k;
                        count = 0;
                    }
                }
                bouquets += count / k;

                if (bouquets >= m) {
                    ans = day;
                    high = day - 1;
                } else {
                    low = day + 1;
                }
            }

            return ans;
        }
    }

    // https://takeuforward.org/plus/dsa/binary-search/faqs/aggressive-cows
    static class AggressiveCows {

        // TC :  O(N*logN)
        // SC: O(1)
        public static int brute(int[] nums, int k) {
            Arrays.sort(nums);
            int maxi = nums[0], mini = nums[0];
            for (int c: nums) {
                if (c > maxi) maxi = c;
                if (c < mini) mini = c;
            }



            int dist = 1;
            for (; dist <= maxi-mini ; dist++) {
                if(isCowPlaced(nums, dist, k)) continue;
                else break;
            }

            return dist - 1;
        }

        // TC: O(N*logN)
        // SC: O(1)
        public static int optimal(int[] nums, int k) {
            Arrays.sort(nums);
            int maxi = nums[0], mini = nums[0];
            for (int c: nums) {
                maxi = Math.max(maxi, c);
                mini = Math.min(mini, c);
            }

            int low = 0, high = maxi - mini;

            while (low <= high) {
                int mid = low + (high - low ) / 2;
                if (isCowPlaced(nums, mid, k)) low = mid + 1;
                else high = mid - 1;
            }

            return high;
        }

        private static boolean isCowPlaced(int[] nums, int dist, int k) {
            int cnt = 0;
            int lastPlaced = -1;
            for (int c: nums) {
                if (lastPlaced == -1 || c - lastPlaced >= dist){
                    cnt++;
                    lastPlaced = c;
                }

                if (cnt == k) return true;
            }

            return false;
        }
    }

    // https://takeuforward.org/plus/dsa/binary-search/faqs/book-allocation-problem
    static class BookAllocation {

        // TC: O(N * (sum - max))
        // SC: O(1)
        public static int brute(int[] nums, int m) {
            int n = nums.length;
            if (n < m) return -1;

            int low = nums[0], high = 0;
            for (int c: nums) {
                low = Math.max(low, c);
                high += c;
            }

            // Linear search for minimum maximum pages
            for (int page = low ; page <= high ; page++) {
                if (countStudents(nums, page) == m) {
                    return page;
                }
            }

            return low;
        }

        // TC: O(N) * O(log (sum - max))
        // SC: O(1)
        public static int optimal(int[] nums, int m) {
            int n = nums.length;
            if (n < m) return -1;

            int low = nums[0], high = 0;
            for (int c: nums) {
                low = Math.max(low, c);
                high += c;
            }

            while (low <= high) {
                int page = low + (high - low) / 2;

                int studentAllocated = countStudents(nums, page);
                if (studentAllocated <= m) high = page - 1;
                else low = page + 1;
            }

            return low;
        }

        private static int countStudents(int[] nums, int pageLimit) {
            int n = nums.length;

            int students = 1;
            int pagesStudent = 0;

            for (int pageInCurrentBook: nums) {
                if (pagesStudent + pageInCurrentBook <= pageLimit) {
                    pagesStudent += pageInCurrentBook;
                }
                else {
                    students++;
                    pagesStudent = pageInCurrentBook;
                }
            }

            return students;
        }
    }
}
