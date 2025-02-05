package org.learning.dsa.arrays;

import org.learning.dsa.utils.ArrayUtils;

import java.util.*;

public class Problems {
    public static void main(String[] args) {
        int[] nums1 = new int[] {0, 2, 7, 8, 0, 0, 0};
        int[] nums2 = new int[] {-7, -3, -1};

        MergeTwoSortedArray.optimal(nums1,4, nums2, nums2.length);
        System.out.println(Arrays.toString(nums1));
    }

    // https://takeuforward.org/plus/dsa/arrays/logic-building/move-zeros-to-end
    static class MoveZerosToEnd {

        // TC: O(N)
        // SC: O(N)
        public static void brute(int[] nums) {
            int[] tmp = new int[nums.length];
            int k = 0;
            // copy non zero element to tmp array
            for (int c : nums) {
                if (c != 0) tmp[k++] = c;
            }

            // copy all elements from tmp array to original array.
            if (k >= 0) System.arraycopy(tmp, 0, nums, 0, k);

            // fill the rest with zero.
            for (int i = k; i < nums.length; i++) nums[i] = 0;
        }

        // TC: O(N)
        // SC: O(1)
        public static void optimal(int[] nums) {
            int k = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    nums[k] = nums[i];
                    k++;
                }
            }

            while (k < nums.length) {
                nums[k] = 0;
                k++;
            }
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/fundamentals/left-rotate-array
    static class RotateArray {
        // TC: O(K+ (N-K) + N) = O(2*N) = O(N)
        // SC: O(1)
        private static void leftRotate(int[] arr, int k) {
            ArrayUtils.reverse(arr, 0, k - 1);
            ArrayUtils.reverse(arr, k, arr.length - 1);
            ArrayUtils.reverse(arr, 0, arr.length - 1);
        }

        // TC: O(K+ (N-K) + N) = O(2*N) = O(N)
        // SC: O(1)
        private static void rightRotate(int[] arr, int k) {
            int lastK = arr.length - k;
            ArrayUtils.reverse(arr, lastK, arr.length - 1);
            ArrayUtils.reverse(arr, 0, lastK - 1);
            ArrayUtils.reverse(arr, 0, arr.length - 1);
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/logic-building/remove-duplicates-from-sorted-array
    static class RemoveDuplicatesFromArray {
        // TC: O(N)
        // SC: O(1)
        public static int optimal(int[] nums) {
            int n = nums.length;
            int ptr = -1;

            for (int i = 0; i < n; i++) {
                if (ptr == -1 || nums[i] != nums[ptr]) {
                    nums[++ptr] = nums[i];
                }
            }

            return ptr + 1;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/logic-building/union-of-two-sorted-arrays
    static class UnionArray {
        // TC: O((M+N)log(M+N)) ≈ (k*log(K))
        // SC: O(M+N)
        public static int[] brute(int[] nums1, int[] nums2) {
            Set<Integer> st = new TreeSet<>();
            for (int c : nums1) st.add(c);
            for (int c : nums2) st.add(c);

            return st.stream().mapToInt(Integer::intValue).toArray();
        }

        public static int[] optimal(int[] nums1, int[] nums2) {
            List<Integer> unionList = new ArrayList<>();
            int idx1 = 0, n1 = nums1.length, idx2 = 0, n2 = nums2.length;

            while (idx1 < n1 && idx2 < n2) {
                if (nums1[idx1] <= nums2[idx2]) {
                    if (unionList.isEmpty() || unionList.getLast() != nums1[idx1]) {
                        unionList.add(nums1[idx1]);
                    }
                    idx1++;
                } else {
                    if (unionList.isEmpty() || unionList.getLast() != nums2[idx2]) {
                        unionList.add(nums2[idx2]);
                    }
                    idx2++;
                }
            }

            // copy left over from array 1.
            while (idx1 < n1) {
                if (unionList.isEmpty() || unionList.getLast() != nums1[idx1]) {
                    unionList.add(nums1[idx1]);
                }
                idx1++;
            }

            // copy left over from array 2
            while (idx2 < n2) {
                if (unionList.isEmpty() || unionList.getLast() != nums2[idx2]) {
                    unionList.add(nums2[idx2]);
                }
                idx2++;
            }


            // convert to correct format.
            int[] result = new int[unionList.size()];
            int k = 0;
            for (int c : unionList) result[k++] = c;
            return result;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/logic-building/intersection-of-two-sorted-arrays
    static class IntersectionArray {
        // TC: O(M+N)
        // SC: O(M+N)
        public static int[] optimal(int[] nums1, int[] num2) {
            List<Integer> intersectionsList = new ArrayList<>();
            int i = 0, n = nums1.length, j = 0, m = num2.length;

            while (i < n && j < m) {
                if (nums1[i] < num2[j]) {
                    i++;
                } else if (nums1[i] > num2[j]) {
                    j++;
                } else {
                    intersectionsList.add(nums1[i]);
                    i++;
                    j++;
                }
            }

            int[] res = new int[intersectionsList.size()];
            int k = 0;
            for (int c : intersectionsList) res[k++] = c;
            return res;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-medium/leaders-in-an-array
    static class LeadersInArray {
        // TC: O(N)
        // SC: O(N)
        public static ArrayList<Integer> optimal(int[] nums) {
            ArrayList<Integer> leaders = new ArrayList<>();
            int lastLeader = nums[nums.length - 1];
            leaders.add(0, lastLeader);

            for (int i = nums.length - 2; i >= 0; i--) {
                if (nums[i] > lastLeader) {
                    lastLeader = nums[i];
                    leaders.add(0, lastLeader);
                }
            }
            return leaders;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-medium/print-the-matrix-in-spiral-manner
    static class SpiralMatrix {
        // TC: O(M*N)
        // SC: O(M*N)
        public static List<Integer> optimal(int[][] matrix) {
            List<Integer> res = new ArrayList<>();

            int m = matrix.length, n = matrix[0].length;
            int left = 0, right = n - 1, top = 0, bottom = m - 1;

            while (left <= right && top <= bottom) {
                // left to right
                for (int i = left; i <= right; i++) res.add(matrix[top][i]);
                top++;

                // right top to bottom
                for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
                right--;


                // right to left
                if (top <= bottom) { // NOTE: IMPORTANT CONDITION
                    for (int i = right; i >= left; i--) res.add(matrix[bottom][i]);
                    bottom--;
                }

                // bottom to up
                if (left <= right) { // NOTE: IMPORTANT CONDITION
                    for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
                    left++;
                }
            }

            return res;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-medium/rearrange-array-elements-by-sign
    static class RearrangeArrayElements {

        // TC: O(N)
        // SC: O(N)
        public static int[] optimal(int[] nums) {
            int[] res = new int[nums.length];

            int posIdx = 0, negIdx = 1;
            for (int c : nums) {
                if (c < 0) {
                    res[negIdx] = c;
                    negIdx += 2;
                } else {
                    res[posIdx] = c;
                    posIdx += 2;
                }
            }
            return res;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-medium/pascal's-triangle
    static class PascalTriangle {

        // pattern 1: for given row R and col C, find the element at position (r,c) in pascal triangle.
        // TC: O(C)
        // SC: O(1)
        public static int elementInPascalTriangle(int r, int c) {
            return nCr(r - 1, c - 1);
        }


        // pattern 2: print nth row of pascal triangle.
        // TC: O(N*R)
        // SC: O(1)
        public static List<Integer> pascalTriangleForNthRow(int N) {
            List<Integer> row = new ArrayList<>();

            for (int c = 1; c <= N; c++) {
                row.add(nCr(N - 1, c - 1));
            }

            return row;
        }

        // pattern 3: generate Pascal triangle upto N rows
        // TC: O(N*N)
        // SC: O(N*N)
        public static List<List<Integer>> pascalTriangle(int n) {
            List<List<Integer>> triangle = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j <= i; j++) {
                    row.add(nCr(i, j));
                }
                triangle.add(row);
            }

            return triangle;
        }


        private static int nCr(int n, int r) {
            if (r > n - r) {
                r = n - r;
            }

            long res = 1;
            for (int i = 0; i < r; i++) {
                res *= n - i;
                res /= i + 1;
            }

            return (int) res;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-medium/rotate-matrix-by-90-degrees
    static class RotateMatrixBy90Degree {
        // TC: O(M*N)
        // SC: O(M*N)
        public static void brute(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] tmp = new int[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    tmp[j][n - i - 1] = matrix[i][j]; // note: iterate indexes on pen and paper for pattern.
                }
            }

            for (int i = 0; i < m; i++) {
                System.arraycopy(tmp[i], 0, matrix[i], 0, n);
            }
        }


        // TC: O(M*N)
        // SC: O(1)
        public static void optimal(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            // transpose matrix
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (i < j) swap(matrix, i, j);
                }
            }

            // reverse each row.
            for (int[] row : matrix) ArrayUtils.reverse(row, 0, row.length - 1);
        }

        private static void swap(int[][] matrix, int row, int col) {
            int tmp = matrix[row][col];
            matrix[row][col] = matrix[col][row];
            matrix[col][row] = tmp;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-medium/two-sum
    static class TwoSum {

        // TC: O(N*N)
        // SC: O(1)
        public static int[] brute(int[] arr, int target) {
            int[] result = new int[]{-1, -1};

            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (i != j && arr[i] + arr[j] == target) {
                        result[0] = i;
                        result[1] = j;
                        return result;
                    }
                }
            }
            return result;
        }

        // TC: O(N* logN)
        // SC: O(N)
        public static int[] better(int[] arr, int target) {
            int[] result = new int[]{-1, -1};
            Map<Integer, Integer> mp = new HashMap<>();

            for (int i = 0; i < arr.length; i++) {
                int required = target - arr[i];
                if (mp.containsKey(required)) {
                    result[0] = i;
                    result[1] = mp.get(required);
                    break;
                }

                mp.put(arr[i], i);
            }

            Arrays.sort(result);
            return result;
        }

        // TC: O(N*logN)
        // SC: O(1)
        public static int[] optimal(int[] arr, int target) {
            int n = arr.length;
            int[][] newArr = new int[n][2];
            for (int i = 0; i < n; i++) {
                newArr[i][0] = arr[i];
                newArr[i][1] = i;
            }

            Arrays.sort(newArr, (first, second) -> first[0] - second[0]);

            int[] res = new int[]{-1, -1};
            int low = 0, high = newArr.length - 1;
            while (low < high) {
                int sum = newArr[low][0] + newArr[high][0];
                if (sum == target) {
                    res[0] = newArr[low][1];
                    res[1] = newArr[high][1];
                    Arrays.sort(res);
                    break;
                } else if (sum < target) {
                    low++;
                } else {
                    high--;
                }
            }

            return res;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-medium/3-sum
    static class ThreeSum {
        // TC: O(N*N*N)
        // SC: O(1)
        public List<List<Integer>> brute(int[] nums) {
            Set<List<Integer>> result = new HashSet<>();
            Arrays.sort(nums);

            for (int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            List<Integer> items = new ArrayList<>(List.of(nums[i], nums[j], nums[k]));
                            result.add(items);
                        }
                    }
                }
            }

            return new ArrayList<>(result);
        }

        // TC: O(N*N)
        // SC: O(N)
        public List<List<Integer>> better(int[] nums) {
            int n = nums.length;
            Set<List<Integer>> triplets = new HashSet<>();
            Map<Integer, Integer> mp = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                mp.put(nums[i], i);
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (i == j) continue;
                    int required = -(nums[i] + nums[j]);
                    int targetKey = mp.getOrDefault(required, -1);
                    if (targetKey != -1 && targetKey != i && targetKey != j) {
                        List<Integer> tmp = new ArrayList<>(List.of(nums[i], nums[j], required));
                        tmp.sort((a, b) -> a - b);
                        triplets.add(tmp);
                    }
                }
            }

            return new ArrayList<>(triplets);
        }

        // TC: O(NlogN) + O(N*N)
        // SC: O(1)
        public List<List<Integer>> optimal(int[] nums) {
            // List to store the triplets that sum up to target
            List<List<Integer>> ans = new ArrayList<>();

            int n = nums.length;

            // Sort the input array nums
            Arrays.sort(nums);

            // Iterate through the array to find triplets
            for (int i = 0; i < n; i++) {
                // Skip duplicates
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                // Two pointers approach
                int j = i + 1;
                int k = n - 1;

                while (j < k) {
                    int sum = nums[i] + nums[j] + nums[k];

                    if (sum < 0) {
                        j++;
                    } else if (sum > 0) {
                        k--;
                    } else {
                        // Found a triplet that sums up to target
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        ans.add(temp);

                        // Skip duplicates
                        j++;
                        k--;
                        while (j < k && nums[j] == nums[j - 1]) j++;
                        while (j < k && nums[k] == nums[k + 1]) k--;
                    }
                }
            }

            return ans;

        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-medium/4-sum
    static class FourSum {

        // TC: O(N^4)
        // SC: O(1)
        public static List<List<Integer>> brute(int[] nums, int target) {
            int n = nums.length;
            Set<List<Integer>> quartets = new HashSet<>();

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        for (int l = k + 1; l < n; l++) {
                            if (nums[i] + nums[j] + nums[k] + nums[l] == target) {
                                List<Integer> tmp = new ArrayList<>();
                                tmp.addAll(List.of(nums[i], nums[j], nums[k], nums[l]));
                                tmp.sort((a, b) -> a - b);
                                quartets.add(tmp);
                            }
                        }
                    }
                }
            }

            return new ArrayList<>(quartets);
        }

        // TC: o(n^3) * log(m) // m is unique quartets
        // SC: O(n) + O(m) // m is unique quartets
        public static List<List<Integer>> better(int[] nums, int target) {
            int n = nums.length;
            Set<List<Integer>> quartets = new HashSet<>();
            Map<Integer, Integer> mp = new HashMap<>();

            for (int i = 0; i < nums.length; i++) {
                mp.put(nums[i], i);
            }

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        int required = target - (nums[i] + nums[j] + nums[k]);
                        int targetIdx = mp.getOrDefault(required, -1);
                        if (targetIdx != -1 && targetIdx != i && targetIdx != j && targetIdx != k) {
                            List<Integer> tmp = new ArrayList<>();
                            tmp.addAll(List.of(nums[i], nums[j], nums[k], nums[targetIdx]));
                            tmp.sort((a, b) -> a - b);
                            quartets.add(tmp);
                        }
                    }
                }
            }

            return new ArrayList<>(quartets);
        }


        // TC: O(N^3)
        // SC: O(1)
        public static List<List<Integer>> optimal(int[] nums, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            int n = nums.length;
            Arrays.sort(nums);

            for (int i = 0; i < n; i++) {
                if (i > 0 && nums[i] == nums[i - 1]) continue;

                for (int j = i + 1; j < n; j++) {
                    if (j != i + 1 && nums[j] == nums[j - 1]) continue;

                    int k = j + 1;
                    int l = n - 1;

                    while (k < l) {
                        int sum = nums[i] + nums[j] + nums[k] + nums[l];
                        if (sum > target) {
                            l--;
                        } else if (sum < target) {
                            k++;
                        } else {
                            List<Integer> tmp = new ArrayList<>(List.of(nums[i], nums[j], nums[k], nums[l]));
                            ans.add(tmp);

                            k++;
                            l--;
                            while (k < l && nums[k] == nums[k - 1]) k++;
                            while (k < l && nums[l] == nums[l + 1]) l--;
                        }
                    }
                }
            }

            return ans;
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-hard/count-inversions
    static class CountInversion {

        // TC: O(N * logN)
        // SC: O(N)
        public static long optimal(int[] nums) {
            int[] count = new int[]{0};
            mergeSort(nums, 0, nums.length-1, count);
            return count[0];
        }

        private static void mergeSort(int[] arr, int low, int high, int []count) {
            if (low >= high) return;

            int mid = low + (high-low)/2;
            mergeSort(arr, low, mid, count);
            mergeSort(arr, mid+1, high, count);
            merge(arr, low, mid, high, count);
        }

        private static void merge(int[] arr, int low, int mid, int high, int[] count) {
            int[] tmp = new int[high-low+1];
            int k = 0;

            int l = low, r = mid+1;

            while (l <= mid && r <= high) {
                if (arr[l] <= arr[r]) {
                    tmp[k] = arr[l];
                    l++;
                } else { // right is smaller
                    count[0] += mid - l + 1;
                    tmp[k] = arr[r];
                    r++;
                }

                k++;
            }

            while (l <= mid) {
                tmp[k] = arr[l];
                l++;
                k++;
            }

            while (r <= high) {
                tmp[k] = arr[r];
                r++;
                k++;
            }

            for (int i=0;i<tmp.length;i++) {
                arr[low+i] = tmp[i];
            }
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-hard/reverse-pairs
    static class ReversePairs {
        // TC: O(N^2)
        // SC: O(1)
        public static int bruteForce(int[] nums) {
            int count = 0;
            for(int i=0;i<nums.length;i++) {
                for(int j=i+1;j<nums.length;j++) {
                    if(nums[i] > 2 * nums[j]) {
                        count++;
                    }
                }
            }

            return count;
        }

        // TC: O(N*logN)
        // SC: O(N)
        public static int optimal(int[] nums) {
            int[] count = new int[] {0};
            mergeSort(nums, 0, nums.length-1, count);
            return count[0];
        }

        private static void mergeSort(int[] arr, int low, int high, int []count) {
            if (low >= high) return;

            int mid = low + (high-low)/2;
            mergeSort(arr, low, mid, count);
            mergeSort(arr, mid+1, high, count);
            merge(arr, low, mid, high, count);
        }

        private static void merge(int[] arr, int low, int mid, int high, int[] count) {
            int[] tmp = new int[high-low+1];
            int k = 0;

            int l = low, r = mid+1;

            for (int i=low, right = mid+1; i<=mid;i++) {
                while (right <= high && arr[i] >2 * arr[right]) right++;

                count[0] += (right - (mid+1));
            }

            while (l <= mid && r <= high) {
                if (arr[l] <= arr[r]) {
                    tmp[k] = arr[l];
                    l++;
                } else { // right is smaller
                    tmp[k] = arr[r];
                    r++;
                }

                k++;
            }

            while (l <= mid) {
                tmp[k] = arr[l];
                l++;
                k++;
            }

            while (r <= high) {
                tmp[k] = arr[r];
                r++;
                k++;
            }

            for (int i=0;i<tmp.length;i++) {
                arr[low+i] = tmp[i];
            }
        }
    }

    // https://takeuforward.org/plus/dsa/arrays/faqs-hard/maximum-product-subarray-in-an-array
    static class MaximumProductSubarray {

        // TC: O(N*N)
        // SC: O(1)
        public static int brute(int[] nums ) {
            long maxi = Long.MIN_VALUE;

            for(int i=0;i<nums.length;i++) {
                long prod = 1l;
                for(int j=i;j<nums.length;j++) {
                    prod *= nums[j];
                    maxi = Math.max(maxi, prod);
                    if(prod == 0) break;
                }
            }

            return (int) maxi;
        }

        // TC: O(N)
        // SC: O(1)
        public static int optimal(int[] nums) {
            int maxi = Integer.MIN_VALUE;
            int n = nums.length;
            int preMax = 1, postMax = 1;

            for (int i=0;i<n;i++) {
                if (preMax == 0) preMax = 1;
                if (postMax == 0) postMax = 1;

                preMax *= nums[i];
                postMax *= nums[n-1-i];

                maxi = Math.max(maxi, Math.max(preMax, postMax));
            }

            return maxi;
        }
    }

    static class MergeTwoSortedArray {

        // TC: O(M+N)
        // SC: O(1)
        public static void optimal(int[] nums1, int m, int[] nums2, int n) {
            int k = m + n - 1;
            m--;
            n--;

            while(m >= 0 && n >= 0) {
                if(nums1[m] >= nums2[n]) {
                    nums1[k] = nums1[m];
                    m--;
                } else {
                    nums1[k] = nums2[n];
                    n--;
                }

                k--;
            }

            while(m >= 0) {
                nums1[k] = nums1[m];
                m--;
                k--;
            }

            while(n >= 0) {
                nums1[k] = nums2[n];
                n--;
                k--;
            }
        }
    }
}



