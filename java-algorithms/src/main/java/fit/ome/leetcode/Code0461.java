package fit.ome.leetcode;

/**
 * 汉明距离
 * <p>
 * https://leetcode-cn.com/problems/hamming-distance/
 */
public class Code0461 {
    public int hammingDistance(int x, int y) {
        int z = x ^ y;
        int ans = 0;
        while (z > 0) {
            if ((z & 1) == 1) {
                ans++;
            }
            z >>= 1;
        }
        return ans;
    }
}
