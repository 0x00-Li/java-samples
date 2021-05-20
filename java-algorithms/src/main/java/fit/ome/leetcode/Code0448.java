package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 找到所有数组中消失的数字
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 */
public class Code0448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums);
        if (nums[0] != 1) {
            for (int i = 1; i < nums[0]; i++) {
                res.add(i);
            }
        }
        // 此刻应该出现的 数字

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] && nums[i] > nums[i - 1] + 1) {
                for (int j = nums[i - 1] + 1; j < nums[i]; j++) {
                    res.add(j);
                }
            }
        }
        int target = nums[nums.length - 1] + 1;
        while (target <= nums.length) {
            res.add(target++);
        }
        return res;
    }

    public List<Integer> findDisappearedNumbers1(int[] nums) {
        int len = nums.length;
        for (int num :
                nums) {


            int index = (num - 1) % len;
            nums[index] += len;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= len) {
                res.add(i);
            }
        }
        return res;
    }
}
