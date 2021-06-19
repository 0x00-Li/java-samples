package fit.ome.leetcode;

import java.util.*;

/**
 * 课程表
 * https://leetcode-cn.com/problems/course-schedule/
 */
public class Code0207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // <curr,pre>
        Map<Integer, Set<Integer>> currCourse = new HashMap<>();
        Stack<Integer> path = new Stack<>();
        for (int i = 0; i < prerequisites.length; i++) {
            Set<Integer> set = currCourse.get(prerequisites[i][0]);
            if (set == null) {
                set = new HashSet<>();
                currCourse.put(prerequisites[i][0], set);
            }
            set.add(prerequisites[i][1]);
        }
        // 判断每一门课程能否学完
        // 0 未判断  -1 不能学  1 可以学完
        int[] dp = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dp[i] != 0) {
                continue;
            }
            dp[i] = canF(i, currCourse, dp, path);
            if (dp[i] == -1) {
                break;
            }
        }
        return dp[numCourses - 1] == 1;
    }

    private int canF(int c, Map<Integer, Set<Integer>> cMap, int[] dp, Stack<Integer> path) {
        if (!cMap.containsKey(c)) {
            // 无依赖课程
            return 1;
        }
        Set<Integer> pre = cMap.get(c);
        int ans = 0;
        for (Integer p : pre) {
            if (dp[p] == 1) {
                ans = 1;
            } else if (dp[p] == -1) {
                ans = -1;
            } else {
                if (path.contains(p)) {
                    ans = -1;
                } else {
                    path.add(p);
                    ans = canF(p, cMap, dp, path);
                    dp[path.peek()] = dp[path.peek()] == -1 ? -1 : ans;
                    path.pop();
                }
            }
            if (ans == -1) {
                break;
            }
        }
        return ans;
    }
}
