package org.learning.dsa.recursion;

import java.util.ArrayList;
import java.util.List;

public class Problems {
    public static void main(String[] args) {
        System.out.println(Pow.brute(2.5,2));
        System.out.println(Pow.optimal(2.5, 2));

        System.out.println(GenerateParenthesis.generateParenthesis(1));

        System.out.println(PowerSet.powerSet(new int[]{1,2,3}));
    }

    static class Pow {
        public static double brute(double x, int n) {
            if (n < 0) {
                x = 1/x;
                n *= -1;
            }

            if (n == 0) return 1;

            double result = x;

            while (n != 1) {
                if (n % 2 == 0) {
                    result *= result;
                    n /= 2;
                } else {
                    result *= x;
                    n -= 1;
                }
            }

            return result;
        }

        public static double optimal(double x, int n) {
            if(n < 0) return optimal(1.0/x, n*-1);
            if(n == 0) return 1;

            if(n % 2 == 0) return optimal(x*x, n/2);
            else return x * optimal(x, n-1);
        }
    }

    // https://takeuforward.org/plus/dsa/recursion/implementation-problems/generate-paranthesis
    static class GenerateParenthesis {
        public static List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            helper(n, n, "", result);
            return result;
        }

        private static void helper(int start, int end, String tmp, List<String> result) {
            if (start < 0 || end < 0) return;
            if (start > end) return;
            if (start == 0 && end == 0) result.add(tmp);

            helper(start-1, end, tmp+"(", result);
            helper(start, end-1, tmp + ")", result);
        }
    }

    // https://takeuforward.org/plus/dsa/recursion/implementation-problems/power-set
    static class PowerSet {
        public static List<List<Integer>> powerSet(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            helper(nums, 0, nums.length, new ArrayList<>(), result);
            return result;
        }

        private static void helper(int[] nums, int idx, int n, List<Integer> tmp, List<List<Integer>> ans) {
            if (idx == n) {
                ans.add(new ArrayList<>(tmp));
                return;
            }

            //non pick
            helper(nums, idx+1, n, tmp, ans);

            // pick
            tmp.add(nums[idx]);
            helper(nums, idx+1, n, tmp, ans);
            tmp.removeLast();
        }
    }
}
