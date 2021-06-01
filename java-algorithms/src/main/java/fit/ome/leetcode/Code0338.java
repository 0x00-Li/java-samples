package fit.ome.leetcode;

/**
 * 比特位计数
 * https://leetcode-cn.com/problems/counting-bits/
 */
public class Code0338 {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            int t = i;
            while (t > 0) {
                if ((t & 1) == 1) {
                    res[i]++;
                }
                t >>= 1;
            }
        }
        return res;
    }
}
