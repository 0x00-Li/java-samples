package fit.ome.sort.impl;

import fit.ome.sort.OmeSort;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/6
 **/
public class Sort_7_Heap implements OmeSort {
    @Override
    public int[] sort(int[] nums) {
        int len = nums.length;
        buildMaxHeap(nums, len);
        for (int i = len - 1; i > 0; i--) {
            swap(nums, 0, i);
            len--;
            heapify(nums, 0, len);
        }
        return nums;
    }

    /**
     * 构建大顶堆
     *
     * @param nums
     */
    private void buildMaxHeap(int[] nums, int len) {
        for (int i = (int) Math.floor(len / 2); i >= 0; i--) {
            // 找到一个非叶子节点开始，调整为大顶堆
            heapify(nums, i, len);
        }
    }

    private void heapify(int[] nums, int i, int len) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largestIndex = i;
        if (left < len && nums[left] > nums[largestIndex]) {
            largestIndex = left;
        }
        if (right < len && nums[right] > nums[largestIndex]) {
            largestIndex = right;
        }
        if (largestIndex != i) {
            swap(nums, largestIndex, i);
            heapify(nums, largestIndex, len);
        }
    }

    private void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }
}
