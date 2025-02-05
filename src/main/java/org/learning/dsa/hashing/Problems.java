package org.learning.dsa.hashing;

import java.util.HashMap;
import java.util.Map;

public class Problems {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,1,1,1,1,3,3};
        int k = 6;

        System.out.println(LongestSubArrayWithSumK.optimal(arr, k));
    }

    // https://takeuforward.org/plus/dsa/hashing/faqs/longest-subarray-with-sum-k
    static class LongestSubArrayWithSumK {

        // TC: O(N*logN)
        // SC: O(N)
        /** <b>optimal for arrays which contains both positive and negative.</b>
         *
         * @param nums input array
         * @param K target sum
         * @return max length of subarray with sum k
         *
        <hr/>
         [. . . . . . . . ]
         prefix sum is x, and target sum is k.

         if there exists a subarray with sum k with (.) as the last element.
         then there exists a prefix sum with x-k sum.

        ------x------ [prefix sum]
        [.........(.)]
        ------ ----
         x-k    k (target sum)
         */
        public static int better(int[] nums, int K) {
            Map<Integer, Integer> preSumMap = new HashMap<>();
            int sum = 0;
            int maxLen = 0;

            for (int i=0;i<nums.length;i++) {
                sum += nums[i];
                if (sum == K) {
                    maxLen = Math.max(maxLen, i+1);
                }

                int remaining = sum - K;
                if (preSumMap.containsKey(remaining)) {
                    int length = i- preSumMap.get(remaining);
                    maxLen = Math.max(maxLen, length);
                }

                if (!preSumMap.containsKey(sum)) preSumMap.put(sum, i);
            }

            return maxLen;
        }

        // TC: O(N)
        // SC: O(1)
        public static int optimal(int[] nums, int K) {
            int left = 0, right = 0, n = nums.length;
            int maxLen = 0;
            int sum = nums[0];

            while (right < n) {
                while (left <= right && sum > K) {
                    sum -= nums[left];
                    left++;
                }

                if (sum == K) {
                    maxLen = Math.max(maxLen, right-left+1);
                }

                right++;
                if (right < n) sum += nums[right];

            }

            return maxLen;
        }
    }
}
