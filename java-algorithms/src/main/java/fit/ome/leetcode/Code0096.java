package fit.ome.leetcode;

/**
 * 不同的二叉搜索树
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 * <p>
 * 左子树上的值都小于右子树上的值
 * <p>
 * 中序遍历递增
 */
public class Code0096 {
    public int numTrees1(int n) {
        if (n == 1) {
            return 1;
        }
        // n个节点的时候，多少种二叉搜索树
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        return dp[n];
    }

    // n 总节点数
    // n 左侧节点数
//    public int process(int n, int i) {
//        if (n == 0 || n == 1) {
//            return process(n - i - 1, 0);
//        }
//        return process(n - 1) + 1;
//    }


    // ---------
    public int numTrees(int n) {
        if (n == 1) {
            return 1;
        }
        int[] c = new int[n + 1];
        int[] preCount = new int[n + 1];
        c[0] = 0;
        c[1] = 1;// +1
        preCount[2] = preCount[1] * 2;
        c[2] = 2;// 2
        preCount[3] = preCount[2] * 2 + 1;
        c[3] = 5;// 2*2+1
        preCount[4] = preCount[3] =
                c[4] = 14;//5*2+2*2+1;
        int ans = 0;
        for (int i = 1; i < n + 1; i++) {
            int lCount = i - 1;
            int rCount = n - i;
//            ans +=  Math.pow(2, i==1||i==0?0:i-1) *  Math.pow(2, i==n-2||i==n-1?0:n - i -1);
            ans += Math.pow(2, lCount == 1 ? 0 : lCount) * Math.pow(2, rCount);
        }
        return ans;
    }

    public static void main(String[] args) {
//        System.out.println(new Code0096().numTrees(2));
        System.out.println(new Code0096().numTrees1(4));
    }
    // 一个节点 i 作为根节点的话
    // 0~i-1 在左子树
    // i+1 ~ n 在右子树
    // 2^(i+1)+2(n-i-1)
//    public void m(node){
//        node.left
//                node
//                node.right
//    }
}
