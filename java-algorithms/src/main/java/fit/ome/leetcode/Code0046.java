package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * 全排列
 *
 */
public class Code0046 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new ArrayList<>();
        List<Integer> path=new ArrayList<>();
        path.add(nums[0]);
        backtrack(nums,1,path,res);
        return res;
    }
    public void backtrack(int[] nums,int i, List<Integer> path, List<List<Integer>> res){
        if(i==nums.length){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int j = 0; j <i; j++) {
            path.add(j,nums[i]);// 在最前和中间每个位置加一次
            backtrack(nums,i+1,path,res);
            path.remove(j);
        }
        path.add(nums[i]);// 加载最后
        backtrack(nums,i+1,path,res);
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        List<List<Integer>> permute = new Code0046().permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }
}
