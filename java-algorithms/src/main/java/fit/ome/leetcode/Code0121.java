package fit.ome.leetcode;

/**
 * 买卖股票的最佳时机
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/31
 **/
public class Code0121 {
    public int maxProfit(int[] prices) {
        int index=0;
        int minIndex=index;
        int maxAfterMin=index;
        int maxProfit=0;

        while (index<prices.length){
            if(prices[index]<prices[minIndex]){
                minIndex=index;
                maxAfterMin=index;
            }
            if(prices[index]>prices[maxAfterMin]){
                maxAfterMin=index;
            }
            if(maxAfterMin<prices.length){
                maxProfit=Math.max(maxProfit,prices[maxAfterMin]-prices[minIndex]);
            }
            index++;
        }

        return maxProfit;

    }
}
