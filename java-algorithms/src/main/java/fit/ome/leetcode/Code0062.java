package fit.ome.leetcode;

/**
 * 不同路径
 * <p>
 * https://leetcode-cn.com/problems/unique-paths/
 * <p>
 * 使用backtrack 解决
 */
public class Code0062 {


    public int uniquePaths1(int m, int n) {


        return backtrack1(m, n, 0, 0);

    }

    public int backtrack1(int m, int n, int x, int y) {
        if (x == m - 1 && y == n - 1) {
            return 1;
        } else if (x < m - 1 || y < n - 1) {
            int ans = 0;
            if (x < m - 1) {
                ans += backtrack1(m, n, x + 1, y);
            }
            if (y < n - 1) {
                ans += backtrack1(m, n, x, y + 1);
            }
            return ans;
        } else {
            return 0;
        }
    }

    public int uniquePaths(int m, int n) {

        int[] res = new int[1];
        backtrack(m, n, 0, 0, res);
        return res[0];
    }

    public void backtrack(int m, int n, int x, int y, int[] res) {
        if (x == m - 1 && y == n - 1) {
            res[0]++;
            return;
        }
        if (x < m - 1 || y < n - 1) {
            if (x < m - 1) {
                backtrack(m, n, x + 1, y, res);
            }
            if (y < n - 1) {
                backtrack(m, n, x, y + 1, res);
            }
        }
    }

    //==============================
    // 暴力递归实现
    public int uniquePaths2(int m, int n) {


        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[j][i]=dp[j-1][i]+dp[j][i-1];
            }
        }
        return dp[m - 1][n - 1];

    }



    public static void main(String[] args) {
        System.out.println(new Code0062().uniquePaths2(4, 2));
    }
}
