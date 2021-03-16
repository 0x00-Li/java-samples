package fit.ome.sort.impl;

import fit.ome.sort.OmeSort;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/3
 **/
public class Sort_3_Insertion implements OmeSort {
    @Override
    public int[] sort(int[] nums) {

        for (int i = 1; i < nums.length; i++) {
            int tmp = nums[i];
            int j=i;
            while (j>0&&tmp<nums[j-1]){
                nums[j]=nums[j-1];
                j--;
            }
            if(i!=j){
                nums[j]=tmp;
            }

        }

        return nums;
    }
}
