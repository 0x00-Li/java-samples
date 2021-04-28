package fit.ome.sort.impl;

import fit.ome.sort.OmeSort;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/6
 **/
public class Sort_6_Quick implements OmeSort {
    @Override
    public int[] sort(int[] nums) {
        // 需要传入，左侧索引和右侧索引
        return quickSort(nums,0,nums.length-1);
    }

    private int[] quickSort(int[] nums, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(nums, left, right);
            // 分区算法的时候，已经将基准数据放入最终位置，所以每次的基准位置不需要参与排序
            quickSort(nums, left, partitionIndex - 1);//

            quickSort(nums, partitionIndex , right);//
        }
        return nums;
    }

    private int partition(int[] nums, int left, int right) {
        // 定基准索引位置
        int pivot = left;
        int index = pivot + 1;// 新分组左侧位
        for (int i = index; i <= right; i++) {
            if (nums[i] < nums[pivot]) {
                swap(nums, i, index);
                index++;
            }
        }
        swap(nums, pivot, index - 1);// 将基准的位置放入最终位置
//        System.out.println("----");
//        App.printArray(nums);
        // 分区位置是待存储位置
        return index;
    }

    private void swap(int[] arr, int i, int j) {
        if(i!=j){
            arr[i] = arr[i] + arr[j];
            arr[j] = arr[i] - arr[j];
            arr[i] = arr[i] - arr[j];
        }

    }
}
