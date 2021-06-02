package fit.ome.leetcode;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * 最长连续序列
 * https://leetcode-cn.com/problems/longest-consecutive-sequence/
 */
public class Code0128 {
    public int longestConsecutive1(int[] nums){
        Set<Integer> numSet=new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
        }
        int longest=0;
        for (Integer num :
                numSet) {

            if (!numSet.contains(num - 1)) {
                int currLen=1;
                int currNum=num;
                while (numSet.contains(currNum+1)){
                    currNum+=1;
                    currLen+=1;
                }

                longest=Math.max(longest,currLen);
            }

        }
        return longest;
    }
    public int longestConsecutive(int[] nums) {
        int maxLen = 0;
        HashSet<Integer> numProcessed = new HashSet<>();
        /**
         * 缓存下位置索引开始的 最大值和最小值
         *
         * 缓存最小位置  【最小值，最大值】
         */
        Map<Integer, Integer[]> minMap = new LinkedHashMap<>();
//        缓存最大位置  【最小值，最大值】
        Map<Integer, Integer[]> maxMap = new LinkedHashMap<>();
        for (int i = 0; i < nums.length; i++) {

            if (numProcessed.contains(nums[i])) {
                continue;
            }
            numProcessed.add(nums[i]);

            boolean upper = false;
            Integer[] uppArr = minMap.get(nums[i] + 1);
            if (uppArr != null) {
                upper = true;
                uppArr[0] = nums[i];// 有上线，设置下线
                // 更新最小位置索引
                minMap.put(nums[i], uppArr);
                minMap.remove(nums[i] + 1);
                maxLen = Math.max(uppArr[1] - uppArr[0] + 1, maxLen);
            }
            boolean lower = false;
            Integer[] lowArr = maxMap.get(nums[i] - 1);
            if (lowArr != null) {
                lower = true;
                lowArr[1] = nums[i];
                // 更新最大位置索引
                maxMap.put(nums[i], lowArr);
                maxMap.remove(nums[i] - 1);
                maxLen = Math.max(lowArr[1] - lowArr[0] + 1, maxLen);
            }
            //整合上下限
            if (!lower && !upper) {
                // 无上无下
                Integer[] value = {nums[i], nums[i]};
                minMap.put(nums[i], value);
                maxMap.put(nums[i], value);
                maxLen = Math.max(maxLen, value[1] - value[0] + 1);

            } else if (lower && upper) {
                // 有上，有下，可以合并

                lowArr[1] = uppArr[1];
                minMap.put(lowArr[0], lowArr);
                maxMap.put(uppArr[1], lowArr);
                minMap.remove(nums[i]);
                maxMap.remove(nums[i]);
                maxLen = Math.max(maxLen, lowArr[1] - lowArr[0] + 1);
            }
        }
        // 合并min和max
        for (Map.Entry<Integer, Integer[]> max : maxMap.entrySet()) {
            if (minMap.containsKey(max.getValue()[0] - 1)) {

            }
        }


        return maxLen;
    }
}
