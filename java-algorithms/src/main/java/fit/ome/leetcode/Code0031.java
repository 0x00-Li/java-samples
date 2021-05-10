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

        int lessIndex = nums.length - 2;
        while (lessIndex >= 0 && nums[lessIndex] >= nums[lessIndex + 1]) {
            lessIndex--;
        }
        if (lessIndex >= 0) {
            // 非全局逆序
            int moreIndex = nums.length - 1;
            while (moreIndex >= 0 && nums[moreIndex] <= nums[lessIndex]) {
                moreIndex--;
            }
            swap(nums, moreIndex, lessIndex);

        }
        reverse(nums, lessIndex + 1);


    }

    public void reverse(int[] nums, int start) {
        for (int i = start, j = nums.length - 1; j > i; i++, j--) {
            swap(nums, i, j);
        }
    }

    public void swap(int[] nums, int i, int j) {
        if(i==j){
            return;
        }
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }

    public static void main(String[] args) {
        int[] nums = {1,1};
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
