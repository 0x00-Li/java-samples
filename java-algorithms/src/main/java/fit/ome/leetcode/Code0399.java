package fit.ome.leetcode;

import java.util.*;

/**
 * 除法求值
 * https://leetcode-cn.com/problems/evaluate-division/
 * <p>
 * 并查集
 */
public class Code0399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Double> rMap = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> s = equations.get(0);
            rMap.put(s.get(0) + "/" + s.get(1), values[i]);
            rMap.put(s.get(1) + "/" + s.get(0), 1 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> q = queries.get(i);
            Double v = rMap.getOrDefault(q.get(0) + "/" + q.get(1), -1d);
            if (v != -1) {
                res[i] = v;
                continue;
            }
            v = rMap.getOrDefault(q.get(1) + "/" + q.get(0), -1d);
            if (v != -1) {
                res[i] = v;
            }

        }

        return null;
    }

    /**
     * 并查集
     * 实现用于查找同源问题
     * 此场景下，需要查找当前节点和同源节点的量化关系
     * Ai*Wi=rootA
     * Bj*Wj=
     */
    public static class UnionFind {
        // 相应节点的父节点
        int[] parent;
        // 指向父节点的权值
        int[] weight;
        int[] help;

        public UnionFind(int size) {
            parent = new int[size];
            weight = new int[size];
        }

        public void union(int i, int j, double v) {

        }

        public int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = i;
                weight[help[hi]] *= weight[i];
            }
            return i;
        }

    }
}
