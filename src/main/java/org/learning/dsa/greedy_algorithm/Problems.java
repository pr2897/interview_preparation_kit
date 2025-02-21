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

    // https://takeuforward.org/plus/dsa/greedy-algorithms/scheduling-and-interval-problems/n-meetings-in-one-room
    static class NMeetingInOneRoom {
        public static int maxMeetings(int[] start, int[] end) {
            int n = start.length;
            int[][] meetings = new int[start.length][3];
            for(int i=0;i<n;i++) {
                meetings[i][0] = start[i];
                meetings[i][1] = end[i];
                meetings[i][2] = i;
            }

            Arrays.sort(meetings, (a,b) -> a[1]-b[1]);
            int cnt = 1, free = meetings[0][1];

            for(int i=1;i<n;i++) {
                if(meetings[i][0] > free) {
                    cnt++;
                    free = meetings[i][1];
                }
            }

            return cnt;
        }
    }

    // https://takeuforward.org/plus/dsa/greedy-algorithms/scheduling-and-interval-problems/non-overlapping-intervals
    static class NonOverlappingProblem {
        public static int MaximumNonOverlappingIntervals(int[][] Intervals) {
            Arrays.sort(Intervals, (a,b) -> a[1]-b[1]);
            int cnt = 1, free = Intervals[0][1];

            for (int i=1;i<Intervals.length;i++) {
                if (Intervals[i][0] >= free) {
                    cnt++;
                    free = Intervals[i][1];
                }
            }

            return Intervals.length - cnt;
        }
    }
}
