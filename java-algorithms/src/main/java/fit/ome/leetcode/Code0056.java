package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 合并区间
 * https://leetcode-cn.com/problems/merge-intervals/
 */
public class Code0056 {
    public int[][] merge(int[][] intervals) {
        PriorityQueue<Integer[]> priorityQueue = new PriorityQueue<>((v1, v2) -> v1[0] - v2[0]);
        for (int[] arr : intervals) {
            priorityQueue.offer(new Integer[]{arr[0], arr[1]});
        }
        List<Integer[]> res = new ArrayList<>();
        Integer[] pre = null;
        while (!priorityQueue.isEmpty()) {
            if (pre == null) {
                pre = priorityQueue.poll();
            }
            if (priorityQueue.isEmpty()) {
                res.add(pre);
                pre = null;
            } else {

                Integer[] next = priorityQueue.peek();
                if (pre[1] >= next[0]) {
                    priorityQueue.poll();
                    pre[1] = Math.max(pre[1], next[1]);
                    if (priorityQueue.isEmpty()) {
                        res.add(pre);
                        pre = null;
                    }
                } else {
                    res.add(pre);
                    pre = null;
                }
            }
        }
        int[][] resArr = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            resArr[i][0] = res.get(i)[0];
            resArr[i][1] = res.get(i)[1];
        }
        return resArr;
    }
}
