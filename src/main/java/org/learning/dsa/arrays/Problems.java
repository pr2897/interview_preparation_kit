package org.learning.dsa.arrays;

import com.sun.source.tree.BreakTree;

import javax.swing.plaf.PanelUI;
import java.util.*;

public class Problems {
    public static void main(String[] args) {

       int[] nums = new int[] {1,6,2,10,3};
       int[] target = new int[]{7,5,12,11,61};
       int[][] response = new int[][]{{0,1}, {2,4}, {2,3}, {0,3}, {-1,-1}};

        System.out.printf("%10s | %10s | %10s\n", "target", "expected", "actual");
       for (int i =0; i< target.length; i++) {
           System.out.printf("%10s | %10s | %10s\n", target[i], Arrays.toString(response[i]), Arrays.toString(TwoSum.optimal(nums, target[i])));
       }

    }

    static class MoveZerosToEnd {

        // TC: O(N)
        // SC: O(N)
        public static void brute(int[] nums) {
            int[] tmp = new int[nums.length];
            int k=0;
            // copy non zero element to tmp array
            for (int c: nums) {
                if (c != 0) tmp[k++] = c;
            }

            // copy all elements from tmp array to original array.
            for (int i=0;i<k;i++) nums[i] = tmp[i];

            // fill the rest with zero.
            for (int i=k;i<nums.length;i++) nums[i] = 0;
        }

        // TC: O(N)
        // SC: O(1)
        public static void optimal(int[] nums) {
            int k = 0;
            for (int i=0;i<nums.length;i++) {
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

    static class RotateArray {
        // TC: O(K+ (N-K) + N) = O(2*N) = O(N)
        // SC: O(1)
        private static void leftRotate(int[] arr, int k) {
            ArrayUtils.reverse(arr, 0, k-1);
            ArrayUtils.reverse(arr, k, arr.length-1);
            ArrayUtils.reverse(arr, 0, arr.length-1);
        }

        // TC: O(K+ (N-K) + N) = O(2*N) = O(N)
        // SC: O(1)
        private static void rightRotate(int[] arr, int k) {
            int lastK = arr.length - k;
            ArrayUtils.reverse(arr, lastK, arr.length-1);
            ArrayUtils.reverse(arr, 0, lastK-1);
            ArrayUtils.reverse(arr, 0, arr.length-1);
        }
    }

    static class RemoveDuplicatesFromArray {
        // TC: O(N)
        // SC: O(1)
        public static int optimal(int[] nums) {
            int n = nums.length;
            int ptr = -1;

            for (int i=0;i<n;i++) {
                if (ptr == -1 || nums[i] != nums[ptr]) {
                    nums[++ptr] = nums[i];
                }
            }

            return ptr+1;
        }
    }

    static class UnionArray {
        // TC: O((M+N)log(M+N)) ≈ (k*log(K))
        // SC: O(M+N)
        public static int[] brute(int[] nums1, int[] nums2) {
            Set<Integer> st = new TreeSet<>();
            for (int c: nums1) st.add(c);
            for (int c: nums2) st.add(c);

            return st.stream().mapToInt(Integer::intValue).toArray();
        }

        public static int[] optimal(int[] nums1, int[] nums2) {
            List<Integer> unionList = new ArrayList<>();
            int idx1=0, n1 = nums1.length, idx2=0, n2 = nums2.length;

            while (idx1<n1 && idx2 < n2) {
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
            int k=0;
            for (int c: unionList) result[k++] = c;
            return result;
        }
    }

    static class IntersectionArray {
        // TC: O(M+N)
        // SC: O(M+N)
        public static int[] optimal(int[] nums1, int[] num2) {
            List<Integer> intersectionsList = new ArrayList<>();
            int i = 0, n = nums1.length, j =0, m = num2.length;

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
            for (int c: intersectionsList) res[k++] = c;
            return res;
        }
    }

    static class LeadersInArray {
        // TC: O(N)
        // SC: O(N)
        public static ArrayList<Integer> optimal(int[] nums) {
            ArrayList<Integer> leaders = new ArrayList<>();
            int lastLeader = nums[nums.length-1];
            leaders.add(0, lastLeader);

            for (int i=nums.length-2;i>=0;i--) {
                if (nums[i] > lastLeader) {
                    lastLeader = nums[i];
                    leaders.add(0, lastLeader);
                }
            }
            return leaders;
        }
    }

    static class SpiralMatrix {
        // TC: O(M*N)
        // SC: O(M*N)
        public static List<Integer> optimal(int[][] matrix) {
            List<Integer> res = new ArrayList<>();

            int m = matrix.length, n = matrix[0].length;
            int left = 0, right = n-1, top = 0, bottom = m - 1;

            while (left <= right && top <= bottom) {
                // left to right
                for (int i=left;i<=right;i++) res.add(matrix[top][i]);
                top++;

                // right top to bottom
                for (int i=top;i<=bottom;i++) res.add(matrix[i][right]);
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

    static class RearrangeArrayElements {

        // TC: O(N)
        // SC: O(N)
        public static int[] optimal(int[] nums) {
            int[] res = new int[nums.length];

            int posIdx = 0, negIdx = 1;
            for (int c: nums) {
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

    static class PascalTriangle {

        // pattern 1: for given row R and col C, find the element at position (r,c) in pascal triangle.
        // TC: O(C)
        // SC: O(1)
        public static int elementInPascalTriangle(int r, int c) {
            return nCr(r-1, c-1);
        }


        // pattern 2: print nth row of pascal triangle.
        // TC: O(N*R)
        // SC: O(1)
        public static List<Integer> pascalTriangleForNthRow(int N) {
            List<Integer> row = new ArrayList<>();

            for (int c = 1; c<= N; c++) {
                row.add(nCr(N-1, c-1));
            }

            return row;
        }

        // pattern 3: generate Pascal triangle upto N rows
        // TC: O(N*N)
        // SC: O(N*N)
        public static List<List<Integer>> pascalTriangle(int n) {
            List<List<Integer>> triangle = new ArrayList<>();
            for (int i=0;i<n;i++) {
                List<Integer> row = new ArrayList<>();
                for (int j=0;j<=i;j++){
                    row.add(nCr(i,j));
                }
                triangle.add(row);
            }

            return triangle;
        }


        private static int nCr(int n, int r) {
            if (r > n-r) {
                r = n-r;
            }

            long res = 1;
            for (int i=0;i<r;i++) {
                res *= n-i;
                res /= i+1;
            }

            return (int) res;
        }
    }

    static class RotateMatrixBy90Degree {
        // TC: O(M*N)
        // SC: O(M*N)
        public static void brute(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] tmp = new int[m][n];

            for (int i=0;i<m;i++) {
                for (int j=0;j<n;j++) {
                    tmp[j][n-i-1] = matrix[i][j]; // note: iterate indexes on pen and paper for pattern.
                }
            }

            for (int i=0;i<m;i++) {
                for (int j=0;j<n;j++) {
                    matrix[i][j] = tmp[i][j];
                }
            }
        }


        // TC: O(M*N)
        // SC: O(1)
        public static void optimal(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            // transpose matrix
            for (int i=0;i<m;i++) {
                for (int j=0;j<n;j++) {
                    if (i<j) swap(matrix, i, j);
                }
            }

            // reverse each row.
            for (int[] row: matrix) ArrayUtils.reverse(row, 0, row.length-1);
        }

        private static void swap(int[][] matrix, int row, int col) {
            int tmp = matrix[row][col];
            matrix[row][col] = matrix[col][row];
            matrix[col][row] = tmp;
        }
    }

    static class TwoSum {

        // TC: O(N*N)
        // SC: O(1)
        public static int[] brute(int[] arr, int target) {
            int[] result = new int[]{-1,-1};

            for (int i=0;i<arr.length;i++) {
                for (int j=0;j<arr.length;j++) {
                    if (i != j && arr[i]+arr[j] == target) {
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
            int[] result = new int[]{-1,-1};
            Map<Integer, Integer> mp = new HashMap<>();

            for (int i =0; i<arr.length;i++) {
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
            for (int i=0;i<n;i++) {
                newArr[i][0] = arr[i];
                newArr[i][1] = i;
            }

            Arrays.sort(newArr, (first, second) -> first[0]-second[0]);

            int[] res = new int[]{-1,-1};
            int low = 0, high = newArr.length-1;
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
}



