package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 生成括号对
 * <p>
 * 利用回溯算法，依次生成
 */
public class Code0022 {

    public List<String> generateParenthesis(int n) {
        if (n == 0) {
            // 0
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        backtrack(n, n, n, path, res);
        return res;
    }

    // left 左括号剩余数量
    // right 右括号剩余数量
    public void backtrack(int n, int left, int right, StringBuilder path, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(path.toString());
            return;
        }
        if (right < left) {
            return;
        }

        if (left == right) {
            // 不需要回溯
            path.append("(");
            backtrack(n, left - 1, right, path, res);
            if(left<n){
                path.deleteCharAt(path.length() - 1);
            }
        } else if (left > 0 && left < right) {
            path.append("(");
            backtrack(n, left - 1, right, path, res);
            path.deleteCharAt(path.length() - 1);
            path.append(")");
            backtrack(n, left, right - 1, path, res);
            path.deleteCharAt(path.length() - 1);
        } else if (left == 0 && right > 0) {
            path.append(")");
            backtrack(n, left, right - 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }


    }

    public static void main(String[] args) {
        System.out.println(new Code0022().generateParenthesis(3));
    }
}
