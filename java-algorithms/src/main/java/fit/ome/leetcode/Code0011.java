package fit.ome.leetcode;

/**
 * 盛水最多的容器
 */
public class Code0011 {

    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // 假定方法可以处理，到某个点（i，ai）可以容纳最大的面积的水
        // 初始最大面积
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            max = Math.max(max, process(height, i, i + 1, 0));
        }

        return max;
    }

    /**
     * 使用暴力方案，递归解决
     * start 0~n
     * end 0~n
     * <p>
     * 两个点（i，ai）（j,aj）之间的面积为
     * (j-i)*min(ai,aj)
     *
     * @param height
     * @param start
     * @return
     */
    private int process(int[] height, int start, int target, int area) {
        if (start == (height.length - 1) || target > (height.length - 1)) {
            // 已经到最后位置，直接返回原面积
            return area;
        }
        int currArea = (target - start) * Math.min(height[start], height[target]);
        return process(height, start, target + 1, Math.max(area, currArea));
    }

    // 暴力递归，超时，改动官方题解，双指针
    public int maxArea1(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // 假定方法可以处理，到某个点（i，ai）可以容纳最大的面积的水
        // 初始最大面积
        int max = 0;
        int start=0;
        int end=height.length-1;
        while (start<end){
            max=Math.max(max,(end-start)*Math.min(height[start],height[end]));
            if(height[start]<height[end]){
                start++;
            }
            else {
                end--;
            }
        }
        return max;
    }

// 改动优化


    // for test

    public static void main(String[] args) {
        //1, 8, 6, 2, 5, 4, 8, 3, 7
        //0, 1 ,2, 3, 4, 5, 6, 7, 8
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new Code0011().maxArea(height));
        System.out.println(new Code0011().maxArea1(height));

    }

}
