package fit.ome.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 柱状图中的最大矩形
 * https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * <p>
 * 最大单调栈，两侧最大值的位置，然后二次遍历求面积
 * <p>
 * 栈底到顶为从大到小
 * <p>
 * 求出每个以自己为高度的矩形的面积
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/5/23
 **/
public class Code0084 {
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;

        }
        if (len == 1) {
            return heights[0];
        }
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        int l;
        int r;
        for (int i = 0; i < len; i++) {

            leftMax[i] = i;
            rightMax[i] = i;

            r = i + 1;
            l = i - 1;
            while (r < len) {
                if (heights[r] >= heights[i]) {
                    rightMax[i] = r;
                    r++;
                } else {
                    break;
                }
            }
            while (l >= 0) {
                if (heights[l] >= heights[i]) {
                    leftMax[i] = l;
                    l--;
                } else {
                    break;
                }
            }
        }
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, heights[i] * (rightMax[i] - leftMax[i] + 1));
        }
        return maxArea;
    }

    public int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;

        }
        if (len == 1) {
            return heights[0];
        }
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];

        leftMax[0] = 0;
        for (int i = 1; i < len; i++) {
            if (heights[i - 1] < heights[i]) {
                leftMax[i] = i;
            } else if (heights[i - 1] == heights[i]) {
                leftMax[i] = leftMax[i - 1];
            } else {
                int j = i - 1;
                while (j >= 0 && leftMax[j] >= 0) {
                    if (heights[j] < heights[i]) {
                        leftMax[i] = i;
                        break;
                    } else if (heights[j] == heights[i]) {
                        leftMax[i] = leftMax[j];
                        break;
                    } else {
                        leftMax[i] = leftMax[j];
                        if (leftMax[j] == j && j - 1 >= 0 && heights[j - 1] > heights[i]) {
                            j--;
                            leftMax[i] = j;
                        } else if (j - 1 >= 0) {
                            j = leftMax[j - 1];
                        } else {
                            break;
                        }
                    }
                }
            }
        }


        rightMax[len - 1] = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            if (heights[i + 1] < heights[i]) {
                rightMax[i] = i;
            } else if (heights[i + 1] == heights[i]) {
                rightMax[i] = rightMax[i + 1];
            } else {
                int j = i + 1;
                while (j < len && rightMax[j] < len) {
                    if (heights[j] < heights[i]) {
                        rightMax[i] = i;
                        break;
                    } else if (heights[j] == heights[i]) {
                        rightMax[i] = rightMax[j];
                        break;
                    } else {
                        rightMax[i] = rightMax[j];
                        if (rightMax[j] == j && j + 1 < len && heights[j + 1] > heights[i]) {
                            j++;
                            rightMax[i] = j;
                        } else if (j + 1 < len) {
                            j = rightMax[j + 1];
                        } else {
                            break;
                        }
                    }
                }
            }
        }

        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, heights[i] * (rightMax[i] - leftMax[i] + 1));
        }
        return maxArea;
    }

    public int largestRectangleArea3(int[] heights) {
        int len = heights.length;
        if (len == 0) {
            return 0;

        }
        if (len == 1) {
            return heights[0];
        }
        int[] leftMax = new int[len];

        leftMax[0] = 0;
        findLeftMax(heights, leftMax, 1, 0);
        int[] rightMax = new int[len];
        rightMax[len - 1] = len - 1;
        findRightMax(heights, rightMax, len - 2, len - 1);
        int maxArea = 0;
        for (int i = 0; i < len; i++) {
            maxArea = Math.max(maxArea, heights[i] * (rightMax[i] - leftMax[i] + 1));
        }
        return maxArea;
    }

    public void findRightMax(int[] heights, int[] rightMax, int i, int j) {
        if (j >= heights.length||i<0) {
            return;
        }
        if (heights[j] == heights[i]) {
            rightMax[i] = rightMax[j];
        } else if (heights[j] < heights[i]) {
            rightMax[i] = j - 1 == i ? i : j - 1;
        } else {
            rightMax[i] = rightMax[j];
            if (rightMax[j] == j) {
                findRightMax(heights, rightMax, i, j + 1);
            }
        }
        findRightMax(heights,rightMax,i-1,i);
    }

    public void findLeftMax(int[] heights, int[] leftMax, int i, int j) {
        if (j < 0||i>=heights.length) {
            return;
        }
        if (heights[j] == heights[i]) {
            leftMax[i] = leftMax[j];
        } else if (heights[j] < heights[i]) {
            leftMax[i] = j + 1 == i ? i : j + 1;
        } else {
            leftMax[i] = leftMax[j];
            if (leftMax[j] == j) {
                findLeftMax(heights, leftMax, i, j - 1);
            }
        }

        findLeftMax(heights,leftMax,i+1,i);
    }

    public static void main(String[] args) {
        new Code0084().largestRectangleArea3(new int[]{1,2,3,4,5});
    }


























    public int largestRectangleArea(int[] heights) {

        int len = heights.length;
        int[] leftMax = new int[len];
        int[] rightMax = new int[len];
        for (int i = 0; i < len; i++) {
            leftMax[i] = -1;
            rightMax[i] = -1;
        }
        Stack<List<Node>> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty()) {
                if (stack.peek().get(0).val == heights[i]) {
                    stack.peek().add(new Node(heights[i], i));
                } else if (stack.peek().get(0).val > heights[i]) {
                    ArrayList<Node> list = new ArrayList<Node>();
                    list.add(new Node(heights[i], i));
                    stack.push(list);
                } else {
                    List<Node> pop = stack.pop();
                    int lm = stack.isEmpty() ? -1 : stack.peek().get(0).index;
                    for (Node n : pop) {
                        leftMax[n.index] = lm;
                        rightMax[n.index] = i;

                    }
                }
            }
//            stack.push(new Node(heights[i],i));
        }
        return 0;
    }

    public static class Node {
        int val;
        int index;

        public Node(int v, int i) {
            val = v;
            index = i;
        }
    }
}
