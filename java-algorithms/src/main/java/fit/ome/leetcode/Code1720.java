package fit.ome.leetcode;

public class Code1720 {
    /**
     * 利用XOR的属性，非负整数支持逆向运算
     * a^b =c
     * a^c =b
     *
     * arr[i]^arr[i+1] = encoded[i] => arr[i-1]^arr[i] = encoded[i-1]
     * arr[i] = encoded[i-1]^arr[i-1]
     * @param encoded
     * @param first
     * @return
     */
    public int[] decode(int[] encoded, int first) {
        int[] ans = new int[encoded.length + 1];
        ans[0]=first;
        for (int i = 1; i < ans.length; i++) {
            ans[i]=ans[i-1]^encoded[i-1];
        }
        return ans;
    }
}
