package fit.ome.leetcode;

import java.util.*;

/**
 * 除法求值
 * https://leetcode-cn.com/problems/evaluate-division/
 * <p>
 * 并查集
 */
public class Code0399 {

    //    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
//        Map<String, Double> rMap = new HashMap<>();
//        for (int i = 0; i < equations.size(); i++) {
//            List<String> s = equations.get(0);
//            rMap.put(s.get(0) + "/" + s.get(1), values[i]);
//            rMap.put(s.get(1) + "/" + s.get(0), 1 / values[i]);
//        }
//        double[] res = new double[queries.size()];
//        for (int i = 0; i < queries.size(); i++) {
//            List<String> q = queries.get(i);
//            Double v = rMap.getOrDefault(q.get(0) + "/" + q.get(1), -1d);
//            if (v != -1) {
//                res[i] = v;
//                continue;
//            }
//            v = rMap.getOrDefault(q.get(1) + "/" + q.get(0), -1d);
//            if (v != -1) {
//                res[i] = v;
//            }
//
//        }
//
//        return null;
//    }
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int size = equations.size();
        // 填充并查集
        UnionFind uf = new UnionFind(size * 2);
        Map<String, Integer> strDic = new HashMap<>();
        int id = 0;

        for (int i = 0; i < size; i++) {
            List<String> equ = equations.get(i);
            String s0 = equ.get(0);
            String s1 = equ.get(1);
            if (!strDic.containsKey(s0)) {
                strDic.put(s0, id++);
            }
            if (!strDic.containsKey(s1)) {
                strDic.put(s1, id++);
            }
            uf.union(strDic.get(s0), strDic.get(s1), values[i]);
        }
        int qsize = queries.size();
        double[] res = new double[qsize];
        for (int i = 0; i < qsize; i++) {
            List<String> q = queries.get(i);
            String s0 = q.get(0);
            String s1 = q.get(1);
            if (strDic.containsKey(s0) && strDic.containsKey(s1)) {
                res[i] = uf.isConnected(strDic.get(s0), strDic.get(s1));
            } else {
                res[i] = -1.0d;
            }
        }
        return res;
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
        double[] weight;


        public UnionFind(int size) {
            parent = new int[size];
            weight = new double[size];

            for (int i = 0; i < size; i++) {
                parent[i] = i;
                weight[i] = 1;
            }
        }

        public void union(int i, int j, double v) {
            int rooti = find(i);
            int rootj = find(j);
            if (rootj != rooti) {
                parent[rooti] = rootj;
                weight[rooti] = weight[j] * v / weight[i];
            }
        }

        public int find(int i) {
            if (i != parent[i]) {
                int origin = parent[i];
                parent[i] = find(parent[i]);
                weight[i] *= weight[origin];
            }
            return parent[i];
        }

        public double isConnected(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                return -1;
            } else {
                return weight[i] / weight[j];
            }
        }

    }
}
