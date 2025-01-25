package org.learning.dsa.arrays;

import com.sun.source.tree.BreakTree;

import java.util.*;

public class Problems {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        for (int[] row: matrix) System.out.println(Arrays.toString(row));

        System.out.println("-".repeat(10));

        List<Integer> res = SpiralMatrix.optimal(matrix);
        System.out.println(res);
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
}



