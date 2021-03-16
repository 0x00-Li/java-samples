package fit.ome.sort.impl;

import fit.ome.sort.OmeSort;

/**
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/3/3
 **/
public class Sort_2_Selection implements OmeSort {
    @Override
    public int[] sort(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int minIndex=i;
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]<nums[minIndex]){
                    minIndex=j;
                }
            }
            if(minIndex!=i){
                int tmp=nums[minIndex];
                nums[minIndex]=nums[i];
                nums[i]=tmp;
            }
        }
        return nums;
    }
}
