package fit.ome.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 * https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 */
public class Code0215 {
    public int findKthLargest(int[] nums, int k) {
//        PriorityQueue<Integer> queue=new PriorityQueue<>(new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o2-o1;
//            }
//        });
//        for (int i = 0; i < nums[i]; i++) {
//            queue.add(nums[i]);
//        }
//        int ans=0;
//        for (int i = 0; i < k; i++) {
//            ans=queue.poll();
//        }
//        return ans;
        selectSort(nums);
        return nums[nums.length - k];
    }

    // 快排
    public void quickSort(int[] nums, int l, int r) {
        if (l < 0 || r <= l||r>=nums.length) {
            return;
        }

        int pIndex = partition(nums, l, r);
        quickSort(nums, l, pIndex - 1);
        quickSort(nums, pIndex + 1, r);
    }

    public int partition(int[] nums, int l, int r) {
        int index = l + 1;
        int pivot = l;

        for (int i = index; i <= r; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, index++);
            }
        }
        swap(nums, index - 1, pivot);
        return index - 1;
    }

    // 选择排序
    public void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    swap(nums, i, j);
                }
            }
        }
    }

    // 归并排序
    public void mergeSort(int[] nums, int l, int r) {
        if (r - l < 1) {
            return;
        }
        int m = (r + l) / 2;
        mergeSort(nums, l, m);
        mergeSort(nums, m + 1, r);
        merge(nums, l, m, r);
    }

    public void merge(int[] nums, int l, int m, int r) {
        int lStart = l;
        int rStart = m + 1;
        int[] h = new int[r - l + 1];
        int i = 0;
        while (lStart < m + 1 && rStart <= r) {
            h[i++] = nums[lStart] < nums[rStart] ? nums[lStart++] : nums[rStart++];
        }
        while (lStart <= m) {
            h[i++] = nums[lStart++];

        }
        while (rStart <= r) {
            h[i++] = nums[rStart++];
        }
        for (int j = 0; j < h.length; j++) {
            nums[l + j] = h[j];
        }
    }

    public void swap(int[] nums, int i, int j) {
        if(i==j)return;
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        new Code0215().quickSort(nums, 0, nums.length-1);
    }
}
