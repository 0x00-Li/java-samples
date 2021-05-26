package fit.ome.leetcode;

import java.util.*;

/**
 * 除法求值
 * https://leetcode-cn.com/problems/evaluate-division/
 * <p>
 * 傻缓存？
 * 动态规划？
 *
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
                res[i]=v;
            }

        }

return null;
    }
}
