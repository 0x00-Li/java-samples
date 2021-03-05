package fit.ome.sort.impl;

import fit.ome.sort.OmeSort;

import java.util.Arrays;

/**
 * 归并排序
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/5
 **/
public class Sort_5_Merge implements OmeSort {
    @Override
    public int[] sort(int[] nums) {
        if (nums.length < 2) {
            return nums;
        }
        int mid = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, mid);
        int[] right = Arrays.copyOfRange(nums, mid, nums.length);
        return merge(sort(left), sort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int leftIndex = 0;
        int rightIndex = 0;
        int mergeIndex = 0;
        while (leftIndex < left.length && rightIndex < right.length) {
            if (left[leftIndex] < right[rightIndex]) {
                result[mergeIndex++] = left[leftIndex++];
            } else {
                result[mergeIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < left.length) {
            result[mergeIndex++] = left[leftIndex++];
        }
        while (rightIndex < right.length) {
            result[mergeIndex++] = right[rightIndex++];
        }
        return result;
    }
}
