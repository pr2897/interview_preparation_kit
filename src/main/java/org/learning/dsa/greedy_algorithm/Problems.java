package org.learning.dsa.greedy_algorithm;

import java.util.Arrays;

public class Problems {

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
}
