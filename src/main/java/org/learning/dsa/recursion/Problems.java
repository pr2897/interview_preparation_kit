package org.learning.dsa.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Problems {
    public static void main(String[] args) {
        System.out.println(Pow.brute(2.5,2));
        System.out.println(Pow.optimal(2.5, 2));

        System.out.println(GenerateParenthesis.generateParenthesis(1));

        System.out.println(PowerSet.powerSet(new int[]{1,2,3}));

        System.out.println(ExistsSubsequenceWithSumK.checkSubsequenceSum(new int[]{1, 2, 3, 4, 5}, 8));
        System.out.println(ExistsSubsequenceWithSumK.checkSubsequenceSum(new int[]{4, 3, 9, 2}, 10));

        System.out.println(CombinationSum.combinationSum(new int[]{2, 3, 5, 4}, 7));

        System.out.println(CombinationSum2.combinationSum2(new int[] {2, 1, 2, 7, 6, 1, 5}, 8));
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

    // https://takeuforward.org/plus/dsa/recursion/subsequence-pattern-problems/check-if-there-exists-a-subsequence-with-sum-k
    static class ExistsSubsequenceWithSumK {
        public static boolean checkSubsequenceSum(int[] nums, int k) {
            return helper(0, nums.length, k, nums);
        }

        public static boolean helper(int idx, int n, int k, int[] nums) {
            if (k == 0) return true;
            if (idx == n) return false;

            return helper(idx+1, n, k - nums[idx], nums) || helper(idx+1, n, k, nums);
        }
    }

    // https://takeuforward.org/plus/dsa/recursion/subsequence-pattern-problems/count-all-subsequences-with-sum-k
    static class CountSubsequenceWithSumK {
        public static int checkSubsequenceSum(int[] nums, int k) {
            return helper(0, nums.length, k, nums);
        }

        public static int helper(int idx, int n, int k, int[] nums) {
            if (k == 0) return 1;
            if (idx == n) return 0;

            return helper(idx+1, n, k - nums[idx], nums) + helper(idx+1, n, k, nums);
        }
    }

    // https://takeuforward.org/plus/dsa/recursion/faqs-medium/combination-sum
    static class CombinationSum {
        public static List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> answer = new ArrayList<>();
            helper(0, candidates.length, target, new ArrayList<>(), answer,candidates);
            return answer;
        }

        private static void helper(int idx, int n, int target, List<Integer> tmp, List<List<Integer>> answer, int[] candidates) {
            if (target == 0) {
                answer.add(new ArrayList<>(tmp));
                return;
            }
            if (target < 0) return;
            if (idx == n) return;


            // pick
            tmp.add(candidates[idx]);
            helper(idx, n, target - candidates[idx], tmp, answer, candidates);
            tmp.removeLast();

            // non pick
            helper(idx+1, n, target, tmp, answer, candidates);
        }
    }

    // https://takeuforward.org/plus/dsa/recursion/faqs-medium/combination-sum-ii
    static class CombinationSum2 {
        public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            Set<List<Integer>> answer = new HashSet<>();

            helper(0, candidates.length, target, new ArrayList<>(), answer, candidates);
            return answer.stream().toList();
        }

        private static void helper(int idx, int length, int target, List<Integer> tmp, Set<List<Integer>> answer, int[] candidates) {
            if (target == 0) {
                answer.add(new ArrayList<>(tmp));
                return;
            }

            if (target < 0) return;
            if (idx == length) return;

            // pick
            tmp.add(candidates[idx]);
            helper(idx+1, length, target - candidates[idx], tmp, answer, candidates);
            tmp.removeLast();

            // non-pick
            helper(idx+1, length, target, tmp, answer, candidates);
        }


    }

    // https://takeuforward.org/plus/dsa/recursion/faqs-medium/subsets-i
    static class Subset1 {
        public static List<Integer> subsetSums(int[] nums) {
            List<Integer> result = new ArrayList<>();
            helper(0, 0, nums, result);
            return result;
        }

        private static void helper(int idx, int sum, int[] nums, List<Integer> ans) {
            if (idx == nums.length) {
                ans.add(sum);
                return;
            }

            // pick
            helper(idx + 1, sum + nums[idx], nums, ans);

            // non pick
            helper(idx + 1, sum, nums, ans);
        }
    }

    //
    static class Subset2 {
        public static List<List<Integer>> subsetsWithDup(int[] nums) {
            Arrays.sort(nums);
            Set<List<Integer>> ans = new TreeSet<>();
            helper(0, nums.length, new ArrayList<>(), ans, nums);
            return new ArrayList<>(ans);
        }

        private static void helper(int idx, int n, List<Integer> tmp, Set<List<Integer>> ans, int[] nums) {
            if (idx == n) {
                ans.add(new ArrayList<>(tmp));
                return;
            }

            tmp.add(nums[idx]);
            helper(idx+1, n, tmp, ans, nums);
            tmp.removeLast();

            helper(idx+1, n, tmp, ans, nums);
        }
    }
}
