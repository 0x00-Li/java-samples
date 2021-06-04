package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 戳气球
 * https://leetcode-cn.com/problems/burst-balloons/
 * <p>
 * 回溯 递归
 */
public class Code0312 {
    public int maxCoins(int[] nums) {
        List<Integer> path = new ArrayList<>();

        return backtrack(nums, path, 0, 0);
    }

    public int backtrack(int[] nums, List<Integer> path, int index, int total) {
        if (index == nums.length) {
            return 0;
        }
        int leftIndex=index-1;
        while (leftIndex>=0){
            if(path.contains(leftIndex)){
                leftIndex--;
            }else {
                break;
            }
        }
        int leftVal=leftIndex<0?1:nums[leftIndex];

        int rightIndex=index+1;
        while (rightIndex<nums.length){
            if(path.contains(rightIndex)){
                rightIndex++;
            }else {
                break;
            }
        }
        int rightVal=rightIndex>=nums.length?1:nums[rightIndex];
        // 1. 戳破当前气球
        int t1=0;
        path.add(index);
        t1=backtrack(nums,path,index+1,total+(rightVal*leftVal*nums[index]));
        // 2. 保留当前气球

        return 0;
    }
}
