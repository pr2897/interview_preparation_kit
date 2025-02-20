package org.learning.dsa.greedy_algorithm;

import java.util.Arrays;

public class Problems {

    public static void main(String[] args) {
        int[] bills = new int[]{5,5,5,5,5,5,5,5,5,5,5,5,20,20,5,5,5,5,20,10,10,10,5,5,5,5,5,5,5,5,5,10,5,5,5,10,5,5,5,5,5,20,10,5,5,5,10,10,10,5,5,5,20,5,5,10,5,5,5,5,10,5,10,10,10,5,5,5,5,5,5,10,20,5,20,10,5,5,10,5,5,5,10,5,20,10,10,5,20,20,10,5,20,5,10,20,20,10,5,5,10,20,5,5,10,10,5,5,10,5,5,5,5,5,5,5,5,20,5,5,10,5,10,10,5,5,5,10,10,20,5,5,10,5,10,10,10,10,5,5,10,10,5,5,10,10,20,20,5,5,5,10,5,5,10,5,5,10,5,5,10,5,20,5,5,20,20,5,20,20,5,5,20,5,5,20,5,20,5,10,5,5,5,20,10,5,5,5,5,20,5,5,5,5,5,5,5,5,20,10,20,5,5,10,5,5,10,10,5,5,20,5,5,5,10,5,10,5,10,5,20,10,5,10,10,5,5,20,10,5,5,5,5,5,10,5,5,5,20,5,5,5,5,5,10,10,10,5,5,20,10,20,5,20,5,5,10,5,10,5,10,10,20,20,20,5,20,5,20,10,5,10,10,10,5,20,20,5,5,20,5,20,5,5,5,5,5,20,5,5,5,10,5,5,5,5,5,10,10,5,5,10,20,5,10,5,5,20,5,5,5,5,10,10,10,10,10,5,5,5,5,10,5,5,20,10,5,5,5,20,5,5,5,10,5,5,10,5,5,10,5,20,20,5,5,5,5,10,5,5,10,10,5,5,20,10,5,20,20,10,20,10,5,5,10,5,20,20,10,5,5,5,5,5,20,5,5,10,5,5,5,10,20,5,5,5,10,20,5,10,5,5,20,5,5,5,5,10,10,20,5,5,5,5,5,10,5,10,10,20,5,5,5,10,20,20,10,20,5,5,20,10,5,5,10,5,5,5,5,10,20,5,5,10,5,5,20,5,20,5,10,5,5,10,10,10,5,5,5,10,10,20,5,10,10,5,10,5,5,5,5,5,5,20,10,5,5,10,5,5,5,5,10,5,20,5,5,5,5,5,5,10,10,10,20,10,10,10,5,5,5,5,5,10,10,5,5,5,5,10,5,5,10,5,10,5,5,10,5,20,5,20,5,5,10,10,10,10,20,10,20,20,20,20,10,5,20,10,20,20,5,10,5,5,20,10,10,5,5,5,5,5,5,20,5,5,10,5,5,5,5,10,10,5,5,10,10,5,5,5,5,5,5,5,10,10,10,20,5,10,5,10,10,10,10,5,10,10,5,5,10,10,20,20,10,10,5,10,5,10,20,5,5,10,10,10,10,5,5,10,10,5,5,5,10,10,5,5,5,5,5,5,10,5,10,5,5,10,10,20,5,10,5,5,5,20,20,20,5,10,5,10,5,20,10,20,10,5,5,5,5,5,5,5,5,10,5,10,5,10,5,5,5,5,20,10,5,10,5,5,5,10,5,5,10,5,10,5,5,20,20,10,20,5,5,5,10,10,5,5};
        System.out.println(LemonadeChange.lemonadeChange(bills));
    }

    // https://takeuforward.org/plus/dsa/greedy-algorithms/easy/assign-cookies
    static class AssignCookies {
        public static int findMaximumCookieStudents(int[] Student, int[] Cookie) {
            Arrays.sort(Student);
            Arrays.sort(Cookie);

            int stu = 0, cookie = 0;

            while (cookie < Cookie.length && stu < Student.length) {
                if (Student[stu] <= Cookie[cookie]) {
                    stu++;
                }
                cookie++;
            }

            return stu;
        }
    }

    //
    static class LemonadeChange {
        public static boolean lemonadeChange(int[] bills) {
            int _5 =0, _10 =0;

            for (int bill: bills) {
                switch (bill) {
                    case 5:{
                        _5++;
                        break;
                    }
                    case 10: {
                        if (_5 > 0) {
                            _5--;
                            _10++;
                            break;
                        } else {
                            return false;
                        }
                    }
                    case 20: {
                        if (_10 > 0) { // 10 rupee exists
                            _10--;
                            _5--;

                        } else {  // only 5
                            _5 -= 3;
                        }

                        if (_5 < 0) return false;
                        break;
                    }
                }
            }

            return true;
        }
    }

    // https://takeuforward.org/plus/dsa/greedy-algorithms/easy/jump-game---i
    static class JumpGame1 {
        public boolean canJump(int[] nums) {
            int maxi = 0;
            for (int i=0;i<nums.length;i++) {
                if (i > maxi) return false;

                maxi = Math.max(maxi, i+nums[i]);
            }

            return true;
        }
    }

    // https://takeuforward.org/plus/dsa/greedy-algorithms/scheduling-and-interval-problems/shortest-job-first
    static class ShortestJobFirst {
        public long solve(int[] bt) {
            int n = bt.length;
            long wt = 0;
            Arrays.sort(bt);

            for (int i=0;i<n;i++) {
                long wp = n-1-i;
                wt += wp*bt[i];
            }

            return wt / n;
        }
    }
}
