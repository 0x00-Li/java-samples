package fit.ome.leetcode_day;

import java.util.*;

/**
 * 传递信息
 * https://leetcode-cn.com/problems/chuan-di-xin-xi/
 */
public class Code_0007 {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> relMap = new HashMap<>();
        for (int[] r : relation) {
            List<Integer> t = relMap.get(r[0]);
            if(t==null){
                t=new ArrayList<>();
                relMap.put(r[0],t);
            }
            t.add(r[1]);
        }
        return backtrack(n-1,relMap,0,k-1);
    }
    public int backtrack(int n,Map<Integer,List<Integer>> relation,int curr,int k){
        if(k<0){
            return 0;
        }
        int ans=0;
        List<Integer> nextSteps = relation.get(curr);
        if(nextSteps==null){
            return ans;
        }
        for (int next:nextSteps) {
            if(n==next&&k==0){
                ans++;
            }else {
                ans+=backtrack(n,relation,next,k-1);
            }
        }
        return ans;
    }
}
