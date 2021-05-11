package fit.ome.leetcode;

/**
 * 最长有效括号
 * 左右括号计数+滑动窗口
 */
public class Code0032 {
    public int longestValidParentheses(String s) {
        int max = 0;
        char[] charArr = s.toCharArray();
        int leftCount = 0;
        int rightCount = 0;
        int fast = 0;
        int slow = 0;
        while (slow < charArr.length) {
            fast = slow;
            // 字符串不能以）开头
            if (charArr[slow] == ')') {
                slow++;
                fast = slow;
                continue;
            }
            while (fast < charArr.length) {
                if (charArr[fast] == '(') {
                    leftCount++;
                } else {
                    rightCount++;
                }
                fast++;

                if (rightCount == leftCount) {
                    max = Math.max(leftCount * 2, max);
                }
                if (rightCount > leftCount) {

                    break;
                }
            }
            leftCount = 0;
            rightCount = 0;
            slow++;
        }
        return max;
    }

    // 憋暴力递归
    public int longestValidParentheses1(String s) {

        char[] charArr = s.toCharArray();
        return process1(charArr, 0, charArr.length - 1);

    }

    public int process1(char[] arr, int start, int end) {

        if (start == end) {
            return 0;
        }
        return process1(arr, start, end - 1) + arr[start - 1] == '(' && arr[end] == ')' ? 2 : 0;
    }

    // 动态规划的解
    public int longestValidParentheses2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        char[] charArr = s.toCharArray();
        int[] dp = new int[s.length()];
        for (int i = 1; i < charArr.length; i++) {
            if (charArr[i] == ')') {
                if (charArr[i - 1] == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && charArr[i - dp[i - 1] - 1] == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1] >= 2) ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }
    // 官方题解
    public int longestValidParentheses3(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

//    public int longestValidParentheses2(String s) {
//        if (s == null || s.length() == 0) {
//            return 0;
//        }
//        char[] charArr = s.toCharArray();
//        int[][] dp = new int[charArr.length][charArr.length];
//        for (int i = 0; i < charArr.length; i++) {
//            dp[i][i] = 0;// 对角线都为0
//            // 对角线上沿，可直接判断
//            if (i + 1 < charArr.length) {
//                dp[i][i + 1] = charArr[i] == '(' && charArr[i + 1] == ')' ? 2 : 0;
//            }
//
//        }
//        for (int k = 2; k < charArr.length; k++) {
//            for (int i = 0, j = k; i < charArr.length - 2 && j < charArr.length; i++, j++) {
//                int v = 0;
//                v = Math.max(dp[i + 1][j], dp[i][j - 1]);
//                v = Math.max(v, dp[i + 1][j - 1] + charArr[i] == '(' && charArr[j] == ')' ? 2 : 0);
//                dp[i][j] = v;
//            }
//        }
//
//        return dp[0][charArr.length - 1];
//    }


    public static void main(String[] args) {
        System.out.println(new Code0032().longestValidParentheses2(")()())"));
    }
}
