package fit.ome.leetcode;

import java.util.*;

/**
 * 前k个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * <p>
 * 大根堆
 */
public class Code0347 {
    public static void main(String[] args) {
//        System.out.println(new Code0347().topKFrequent(new int[]{1,2},2));
        TreeSet<Info> set=new TreeSet<>();
        set.add(new Info(1,1));
        set.add(new Info(2,1));
        set.add(new Info(3,1));
    }
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> nMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            nMap.put(nums[i], nMap.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Info> queue=new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o2.count-o1.count;
            }
        });
        for (Map.Entry<Integer, Integer> entry : nMap.entrySet()) {
            queue.add(new Info(entry.getKey(), entry.getValue()));
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll().val;
        }
        return res;
    }

    public static class Info   {
        int val;
        int count;

        public Info(int v, int c) {
            val = v;
            count = c;
        }



    }
}
