package fit.ome.leetcode;

/**
 * 下一个排列
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/10
 **/
public class Code0031 {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }
        if(nums.length==2){
            swap(nums,0,1);
            return;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i - 1 > 0 && nums[i] > nums[i - 1]) {
                swap(nums, i, i - 1);
                return;
            }
        }

        if (nums[0] > nums[1]) {
            for (int i = 0, j = nums.length - 1; j > i && i < nums.length; i++, j--) {
                swap(nums, i, j);
            }
            return;
        }


        int mid = nums[0];
        boolean hasZero = false;
        for (int i = 1, j = nums.length - 1; j > i && i < nums.length; i++, j--) {


            if (!hasZero && nums[j] > mid) {
//                nums[0] = nums[j];
//                nums[j] = mid;
                swap(nums,0,j);
                swap(nums, i, j);
                hasZero = true;
            } else if (nums[j - 1] <= mid && nums[i + 1] > mid) {
                swap(nums, i, j);
            } else if (!hasZero && nums[j - 1] > mid) {
                // 留住j位置数字
               swap(nums,0,j-1);
                swap(nums, i, j);
                hasZero = true;
            } else if (!hasZero && nums[i + 1] < mid) {
//                留住i位置数字
//                nums[0] = nums[i];
//                nums[i] = mid;
                swap(nums,0,i);
                swap(nums, i, j);
                hasZero = true;
            }

        }

    }

    public void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        int[] nums = {3, 5, 4, 3, 2};
        new Code0031().nextPermutation(nums);
        printArr(nums);
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        ;
    }
}
