package fit.ome.sort.impl;

import fit.ome.sort.OmeSort;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/5
 **/
public class Sort_4_Shell implements OmeSort {

    public int[] sort1(int[] nums) {
        int length = nums.length;
        int tmp;
        for (int step = length / 2; step > 0; step /= 2) {
            for (int i = step; i < length; i++) {
                tmp = nums[i];
                int j = i - step;
                while (j >= 0 && nums[j] > tmp) {
                    nums[j + step] = nums[j];
                    j -= step;
                }
                nums[j + step] = tmp;
            }
        }
        return nums;
    }

    @Override
    public int[] sort(int[] nums) {
        // 外层循环分组
        for (int step = nums.length / 2; step > 0; step /= 2) {
            // 循环每个组进行插入排序
            for (int i = step; i < nums.length; i++) {
                int j = i;
                while (j - step >= 0 && nums[j] < nums[j - step]) {
                    nums[j - step] = nums[j] + nums[j - step];
                    nums[j] = nums[j - step] - nums[j];
                    nums[j - step] = nums[j - step] - nums[j];
                    j -= step;
                }
            }
        }
        return nums;
    }
}
