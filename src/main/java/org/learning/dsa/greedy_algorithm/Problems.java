package org.learning.dsa.greedy_algorithm;

import java.util.Arrays;

public class Problems {

    public static void main(String[] args) {
        int[] bills = new int[]{5,5,5,5,5,5,5,5,5,5,5,5,20,20,5,5,5,5,20,10,10,10,5,5,5,5,5,5,5,5,5,10,5,5,5,10,5,5,5,5,5,20,10,5,5,5,10,10,10,5,5,5,20,5,5,10,5,5,5,5,10,5,10,10,10,5,5,5,5,5,5,10,20,5,20,10,5,5,10,5,5,5,10,5,20,10,10,5,20,20,10,5,20,5,10,20,20,10,5,5,10,20,5,5,10,10,5,5,10,5,5,5,5,5,5,5,5,20,5,5,10,5,10,10,5,5,5,10,10,20,5,5,10,5,10,10,10,10,5,5,10,10,5,5,10,10,20,20,5,5,5,10,5,5,10,5,5,10,5,5,10,5,20,5,5,20,20,5,20,20,5,5,20,5,5,20,5,20,5,10,5,5,5,20,10,5,5,5,5,20,5,5,5,5,5,5,5,5,20,10,20,5,5,10,5,5,10,10,5,5,20,5,5,5,10,5,10,5,10,5,20,10,5,10,10,5,5,20,10,5,5,5,5,5,10,5,5,5,20,5,5,5,5,5,10,10,10,5,5,20,10,20,5,20,5,5,10,5,10,5,10,10,20,20,20,5,20,5,20,10,5,10,10,10,5,20,20,5,5,20,5,20,5,5,5,5,5,20,5,5,5,10,5,5,5,5,5,10,10,5,5,10,20,5,10,5,5,20,5,5,5,5,10,10,10,10,10,5,5,5,5,10,5,5,20,10,5,5,5,20,5,5,5,10,5,5,10,5,5,10,5,20,20,5,5,5,5,10,5,5,10,10,5,5,20,10,5,20,20,10,20,10,5,5,10,5,20,20,10,5,5,5,5,5,20,5,5,10,5,5,5,10,20,5,5,5,10,20,5,10,5,5,20,5,5,5,5,10,10,20,5,5,5,5,5,10,5,10,10,20,5,5,5,10,20,20,10,20,5,5,20,10,5,5,10,5,5,5,5,10,20,5,5,10,5,5,20,5,20,5,10,5,5,10,10,10,5,5,5,10,10,20,5,10,10,5,10,5,5,5,5,5,5,20,10,5,5,10,5,5,5,5,10,5,20,5,5,5,5,5,5,10,10,10,20,10,10,10,5,5,5,5,5,10,10,5,5,5,5,10,5,5,10,5,10,5,5,10,5,20,5,20,5,5,10,10,10,10,20,10,20,20,20,20,10,5,20,10,20,20,5,10,5,5,20,10,10,5,5,5,5,5,5,20,5,5,10,5,5,5,5,10,10,5,5,10,10,5,5,5,5,5,5,5,10,10,10,20,5,10,5,10,10,10,10,5,10,10,5,5,10,10,20,20,10,10,5,10,5,10,20,5,5,10,10,10,10,5,5,10,10,5,5,5,10,10,5,5,5,5,5,5,10,5,10,5,5,10,10,20,5,10,5,5,5,20,20,20,5,10,5,10,5,20,10,20,10,5,5,5,5,5,5,5,5,10,5,10,5,10,5,5,5,5,20,10,5,10,5,5,5,10,5,5,10,5,10,5,5,20,20,10,20,5,5,5,10,10,5,5};
        System.out.println(LemonadeChange.lemonadeChange(bills));

        int[][] Jobs = new int[][]{{1,4,20}, {2,1,10}, {3,1,40}, {4,1,30}};
        System.out.println(Arrays.toString(JobSequencingProblem.JobScheduling(Jobs)));
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

    //
    static class JobSequencingProblem  {
        public static int[] JobScheduling(int[][] Jobs) {
            Arrays.sort(Jobs, (a,b) -> b[2]-a[2]);
            int totalProfit = 0, cnt = 0, maxDeadLine = -1;
            for(int[] job: Jobs) maxDeadLine = Math.max(maxDeadLine, job[1]);
            int[] hash = new int[maxDeadLine+1];
            Arrays.fill(hash, -1);

            for(int[] job: Jobs) {
                int id = job[0], profit = job[2], deadline =job[1];

                for(int i=deadline; i > 0; i--) {
                    if(hash[i] == -1) {
                        hash[i] = id;
                        cnt++;
                        totalProfit += profit;
                        break;
                    }
                }
            }

            return new int[] {cnt, totalProfit};
        }
    }
}
