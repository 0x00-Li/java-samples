package fit.ome.leetcode;

/**
 * 单词搜索
 * https://leetcode-cn.com/problems/word-search/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/21
 **/
public class Code0079 {
    public boolean exist(char[][] board, String word) {

        int h = board.length;
        int w = board[0].length;
        boolean[][] visited = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                boolean ans = check(board, word, i, j, visited, 0);
                if (ans) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean check(char[][] board, String word, int i, int j, boolean[][] visited, int k) {


        if (board[i][j] != word.charAt(k)) {
            return false;
        } else if (k == word.length() - 1) {
            return true;
        }
        visited[i][j] = true;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean result = false;

        for (int[] dir : dirs) {
            int ni = i + dir[0], nj = j + dir[1];
            if (ni >= 0 && ni < board.length && nj >= 0 && board[0].length > nj) {
                if (!visited[ni][nj]) {
                    boolean flag = check(board, word, ni, nj, visited, k + 1);
                    if (flag) {
                        result = true;
                        break;
                    }
                }

            }
        }
        visited[i][j] = false;
        return result;
    }

}
