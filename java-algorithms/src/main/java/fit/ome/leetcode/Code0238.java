package fit.ome.leetcode;

/**
 * 除自身以外的数的乘积
 * https://leetcode-cn.com/problems/product-of-array-except-self/
 * 前后缀乘积
 */
public class Code0238 {
    public int[] productExceptSelf(int[] nums) {

        int n=nums.length;
        int[] pre=new int[n];
        pre[0]=nums[0];

        for (int i = 1; i < n; i++) {
            pre[i]=pre[i-1]*nums[i];
        }
        int[] suf=new int[n];
        suf[n-1]=nums[n-1];
        for (int i = n-2; i >=0 ; i--) {
            suf[i]=suf[i+1]*nums[i];
        }
        int[] res=new int[n];
        for (int i = 0; i < n; i++) {
            res[i]=(i-1<0?1:pre[i-1])*(i+1<n?suf[i+1]:1);
        }
        return res;
    }
}
