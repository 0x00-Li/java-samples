package fit.ome.leetcode;

/**
 * 搜索旋转数组
 * <p>
 * 旋转后的两部分有序
 * 从两部分中的其中一个进行搜索
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/11
 **/
public class Code0033 {
    public int search(int[] nums, int target) {

        if (nums[nums.length-1] > nums[0]) {
            // 二分查找
            int l = 0;
            int r = nums.length - 1;
            int m = 0;
            while (l <= r) {
                m = (l + r) >> 1;
                if(nums[m]==target){
                    return m;
                }else if(nums[m]<target){
                    l=m+1;
                }else if(nums[m]>target){
                    r=m-1;
                }
            }
        } else if (nums[0] == target) {
            return 0;
        } else if (nums[nums.length - 1] == target) {
            return nums.length - 1;
        } else if (nums[0] < target) {
            // 在前半区中查找
            int i = 0;
            while (i + 1 < nums.length && nums[i] < nums[i + 1]) {
                if (nums[i + 1] == target) {
                    return i+1;
                } else if (nums[i + 1] > target) {
                    break;
                }
                i++;
            }
        } else if (nums[nums.length - 1] > target) {
            // 在后半区查找
            int i = nums.length - 1;
            while (i > 0 && nums[i] > nums[i - 1]) {
                if (nums[i - 1] == target) {
                    return i - 1;
                } else if (nums[i - 1] < target) {
                    break;
                }
                i--;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new Code0033().search(new int[]{3,5,1}, 5));
//        System.out.println(new Code0033().search(new int[]{4,5,6,7,0,1,2}, 0));
    }
}
