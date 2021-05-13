package fit.ome.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 任务调度器
 * https://leetcode-cn.com/problems/task-scheduler/
 * <p>
 * 使用贪心算法
 * <p>
 * 找出需要执行次数最多的任务的最多执行次数 maxTimes
 * 找出执行次数最多的任务的个数 maxCount
 * 在冷却周期>所有任务种类的情况下，需要耗时(maxTimes-1)*(n+1)+maxCount
 * 在冷却周期 < 所有任务种类的情况下，耗时 为任务总数
 */
public class Code0621 {
    public int leastInterval(char[] tasks, int n) {

        if (tasks == null || tasks.length == 0) {
            return 0;
        }
        int[] buckets = new int[26];

        // 统计任务出现次数
        for (int i = 0; i < tasks.length; i++) {
            buckets[tasks[i] - 'A']++;
        }
        Arrays.sort(buckets);
        int maxTimes = buckets[25];
        int maxCount = 1;
        for (int i = 25; i > 1; i--) {
            if (buckets[i] == buckets[i - 1]) {
                maxCount++;
            } else {
                break;
            }
        }


        return Math.max((maxTimes - 1) * (n + 1) + maxCount, tasks.length);
    }
}
