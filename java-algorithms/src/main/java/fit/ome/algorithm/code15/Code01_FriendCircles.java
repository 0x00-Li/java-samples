package fit.ome.algorithm.code15;

import fit.ome.tpl.UnionFind;

/**
 * 查找省份数量
 * https://leetcode-cn.com/problems/number-of-provinces/
 * 使用并查集解决
 */
public class Code01_FriendCircles {
    public int findCircleNum(int[][] isConnected) {
        int len = isConnected.length;
        UnionFind uf = new UnionFind(len);
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.count();
    }

    public static class UnionFind {
        int[] parent;
        int[] help;
        int[] size;
        int sets;

        public UnionFind(int N) {
            parent = new int[N];
            help = new int[N];
            size = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                // 记录途径节点
                help[hi++] = i;
                // 推进代表节点
                i = parent[i];
            }
            // 变更所有途径几点的代表节点
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
            }
            return i;
        }

        // 调用这个方法，说明，i和j是一个集合中的
        public void union(int i, int j) {
            int pi = find(i);
            int pj = find(j);
            if (pi != pj) {
                if (size[pi] >= size[pj]) {
                    // 小集合向大集合合并
                    size[pi] += size[pj];
                    parent[pj] = pi;
                } else {
                    size[pj] += size[pi];
                    parent[pi] = pj;

                }
                sets--;
            }
        }

        public int count() {
            return sets;
        }
    }
}
