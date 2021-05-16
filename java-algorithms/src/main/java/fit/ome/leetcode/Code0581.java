package fit.ome.leetcode;

/**
 * 最短无序连续子数组
 * <p>
 * https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/
 * <p>
 * 第一次遍历，找到最大和最小值
 * <p>
 * 低二次遍历，从左到右，找到第一个比最小值大的值的位置
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/16
 **/
public class Code0581 {
    public int findUnsortedSubarray(int[] nums) {


        int lMaxIndex = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                lMaxIndex = i + 1;

            } else if (nums[i] > nums[i + 1]) {

                break;
            }
        }
        if (lMaxIndex == nums.length-1) {
            return 0;
        }

        int rMinIndex = nums.length - 1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                rMinIndex = i-1;
            }else if(nums[i] < nums[i - 1]) {
                break;
            }
        }
        if(rMinIndex==0){
            return 0;
        }
        // rMinIndex > lMaxIndex
        // lMaxIndex 到 rMinIndex之间的最大值和最小值,两端不包含
        int rMin = nums[rMinIndex];
        int lMax = nums[lMaxIndex];
        for (int i = lMaxIndex + 1; i < rMinIndex; i++) {
            lMax = Math.max(nums[i], lMax);
            rMin = Math.min(nums[i], rMin);
        }
        if(rMin==lMax){
            return 0;
        }
        int L = lMaxIndex, R = rMinIndex ;
        // 将最大值和最小值向两边扩，最大边界为最大数组长度
        for (int i = R; i < nums.length; i++) {

            if (nums[i] < lMax) {
                R = i;

            }else {
                break;
            }
        }
        for (int i = L-1; i >= 0; i--) {
            if (nums[i] > rMin) {
                L = i;

            }else {
                break;
            }
        }

        return R - L + 1;

    }

    public static void main(String[] args) {
        System.out.println(new Code0581().findUnsortedSubarray(new int[]{1,2,5,3,4}));
        System.out.println(new Code0581().findUnsortedSubarray(new int[]{1,1,1,1,1,1,1,1,1}));
        System.out.println(new Code0581().findUnsortedSubarray(new int[]{1,2,3,3,3}));
        System.out.println(new Code0581().findUnsortedSubarray(new int[]{1,3,2,2,2}));
        System.out.println(new Code0581().findUnsortedSubarray(new int[]{1, 3, 2, 4, 5}));
        System.out.println(new Code0581().findUnsortedSubarray(new int[]{2, 1}));
        System.out.println(new Code0581().findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(new Code0581().findUnsortedSubarray(new int[]{1, 2, 3, 4}));
    }
}
