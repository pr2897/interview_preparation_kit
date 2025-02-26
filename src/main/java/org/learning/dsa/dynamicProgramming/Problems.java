package org.learning.dsa.dynamicProgramming;

import java.util.Arrays;

/**
 * Input: nums = [1,2,3,1]    // O(N)
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 *
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 */

// u cant pick the neighbour house.

// follow-up question, house are arranged in cyclic order.

public class Problems {
    public static void main(String[] args) {

        int[][] testcases = new int[][] {
                {2,3,5,9},
                {1,2,3,1},
                {2,7,9,3,1}
        };

        int[] expectedOutput = new int[] {12, 4, 12};

        for (int i = 0; i< testcases.length; i++) {
            int[] memo = new int[testcases[i].length + 1];
            Arrays.fill(memo, -1);
            int actual = maxProfit(0, testcases[i], memo);
            System.out.printf("%s actual: %d, expected: %d\n", actual == expectedOutput[i] ? "✅" : "❌", actual, expectedOutput[i]);
        }
    }

    public static int maxProfit(int idx, int[] nums, int[] memo) {
        if (idx >= nums.length) return 0;

        if (memo[idx] != -1) return memo[idx];

        int pick = nums[idx] + maxProfit(idx+2, nums, memo);
        int notPick = maxProfit(idx+1, nums, memo);

        return memo[idx] = Math.max(pick, notPick);
    }
}
