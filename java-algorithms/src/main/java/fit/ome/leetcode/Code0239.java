package fit.ome.leetcode;

import java.util.*;

/**
 * 滑动窗口最大值
 * https://leetcode-cn.com/problems/sliding-window-maximum/
 * <p>
 * <p>
 * 保留前三最大
 */
public class Code0239 {
    // 错误答案
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        Map<Integer, Integer> popData = new HashMap<>();

        res[0] = queue.peek();

        int j = 1;
        for (int i = k; i < nums.length; i++) {
            int currNum = nums[i - k];
            popData.put(currNum, popData.getOrDefault(popData, 0) + 1);
            queue.add(nums[i]);
            while (popData.containsKey(queue.peek())) {
                Integer poll = queue.poll();
                int count = popData.get(poll);
                if ((--count) == 0) {
                    popData.remove(poll);
                } else {
                    popData.put(poll, count);
                }
            }
            res[j++] = queue.peek();
        }
        return res;
    }
}
