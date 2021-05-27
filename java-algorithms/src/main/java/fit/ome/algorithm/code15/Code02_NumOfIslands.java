package fit.ome.algorithm.code15;

/**
 * 岛链数量问题
 * 测试链接：https://leetcode.com/problems/number-of-islands/
 */
public class Code02_NumOfIslands {
    public int numIslands(char[][] grid) {

        int row = grid.length;
        int col = grid[0].length;
        int[][] pos = new int[][]{
                {-1, 0},
                {0, -1},
                {1, 0},
                {0, 1}
        };
        UnionFind uf = new UnionFind(row, col);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '0') {
                    uf.substration();
                } else {

                    for (int[] p : pos) {
                        if (i + p[0] >= 0 && i + p[0] < row && j + p[1] >= 0 && j + p[1] < col && grid[i + p[0]][j + p[1]] == '1') {
                            uf.union(i, j, i + p[0], j + p[1]);
                        }
                    }
                }
            }
        }
        return uf.count();
    }

    public static class UnionFind {
        // 指定节点的parent 应该是个二维数组
        int[][][] parent;
        // [途径的第几个点][2-相关点的x,y]
        int[][] help;
        // 某个位置i，j 所在的集合的大小
        int[][] size;
        // 集合数量
        int sets;

        public UnionFind(int row, int col) {
            parent = new int[row][col][2];
            help = new int[row * col][2];
            size = new int[row][col];
            sets = row * col;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    parent[i][j] = new int[]{i, j};
                    size[i][j] = 1;
                }
            }
        }

        public void union(int i1, int j1, int i2, int j2) {
            if((i1==2&&j1==1)||(i2==2&&j2==1)){
                System.out.println("debug");
            }
            int[] p1 = find(i1, j1);
            int[] p2 = find(i2, j2);
            if (p1[0] != p2[0] || p1[1] != p2[1]) {
                if (size[p1[0]][p1[1]] >= size[p2[0]][p2[1]]) {
                    size[p1[0]][p1[1]] += size[p2[0]][p2[1]];
                    parent[p2[0]][p2[1]] = p1;
                } else {
                    size[p2[0]][p2[1]] += size[p1[0]][p1[1]];
                    parent[p1[0]][p1[1]] = p2;
                }
                sets--;
            }
        }

        public int[] find(int i, int j) {
            int[] res=new int[]{i,j};
            int hi = 0;// 记录路径使用的位置
            while (res[0] != parent[i][j][0] || res[1] != parent[i][j][1]) {
                help[hi++] = res;
                res = parent[i][j];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi][0]][help[hi][1]] = res;
            }
            return res;
        }

        public int count() {
            return sets;
        }

        public void substration() {
            sets--;
        }
    }

    public static void main(String[] args) {
//        char[][] grid = {
//                {'1', '1', '1', '1', '0'},
//                {'1', '1', '0', '1', '0'},
//                {'1', '1', '0', '0', '0'},
//                {'0', '0', '0', '0', '0'}
//        };
        char[][] grid = {
                {'1','0','1','1','1'},
                {'1','0','1','0','1'},
                {'1','1','1','0','1'}
        };
        System.out.println(new Code02_NumOfIslands().numIslands(grid));
    }
    //[
    // ["1","0","1","1","1"],
    // ["1","0","1","0","1"],
    // ["1","1","1","0","1"]
    // ]

}
