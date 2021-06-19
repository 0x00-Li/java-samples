package fit.ome.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * https://leetcode-cn.com/problems/majority-element/
 */
public class Code0169 {
    public int majorityElement1(int[] nums) {

        int len = nums.length;
        int ans = nums[0];
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (nums[i] == ans) {
                count++;
            } else {

                if ((--count) < 0) {
                    ans = nums[i];
                    count=0;
                }
            }
        }
        return ans;
    }

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> eleMap = new HashMap<>();
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            eleMap.put(nums[i], eleMap.getOrDefault(nums[i], 0) + 1);
            if (eleMap.get(nums[i]) > (len >> 1)) {
                ans = nums[i];
                break;
            }
        }
        return ans;
    }


}
