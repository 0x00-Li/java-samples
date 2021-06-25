package fit.ome.leetcode_day;

import java.util.*;

/**
 * 打开转盘锁
 */
public class Code_0752 {
    public static void main(String[] args) {
        new Code_0752().openLock(new String[]{"0201", "0101", "0102", "1212", "2002"}, "0202");
    }

    public int openLock(String[] deadends, String target) {
        Set<String> deadSet = new HashSet<>();
        for (String d : deadends) {
            deadSet.add(d);
        }
        if (deadSet.contains("0000")) {
            return -1;
        }
        if ("0000".equals(target)) {
            return 0;
        }

        int step = 0;
        Queue<String> path = new LinkedList<>();
        path.add("0000");
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        while (!path.isEmpty()) {
            ++step;
            int size = path.size();
            for (int i = 0; i < size; i++) {
                String status = path.poll();
                for (String nextStatus : getNextStatus(status)) {
                    if (!seen.contains(nextStatus) && !deadSet.contains(nextStatus)) {
                        if (nextStatus.equals(target)) {
                            return step;
                        }
                        path.offer(nextStatus);
                        seen.add(nextStatus);
                    }
                }
            }
        }
        return -1;

    }

    public List<String> getNextStatus(String status) {
        List<String> res = new ArrayList<>();
        char[] arr = status.toCharArray();
        for (int i = 0; i < 4; i++) {
            char num = arr[i];
            arr[i] = numPrev(num);
            res.add(new String(arr));
            arr[i] = numSucc(num);
            res.add(new String(arr));
            arr[i] = num;
        }
        return res;
    }

    public char numPrev(char n) {
        return n == '9' ? '0' : (char) (n + 1);
    }

    public char numSucc(char n) {
        return n == '0' ? '9' : (char) (n - 1);
    }

    public int openLock1(String[] deadends, String target) {

//        Node tree = new Node();
//        for (String d : deadends) {
//            Node t = tree.next[d.charAt(0) - '0'];
//            if (t == null) {
//                t = new Node();
//                tree.next[d.charAt(0) - '0'] = t;
//            }
//            Node t=tree;
//            for (int i = 0; i < 4; i++) {
//                if (t.next[d.charAt(i) - '0'] == null) {
//                    t.next[d.charAt(i) - '0'] = new Node();
//                }
//                t = t.next[d.charAt(i) - '0'];
//
//            }
//        }
        int[][] bit = new int[deadends.length - 1][10];
        for (String d : deadends) {
            for (int j = 0; j < 4; j++) {
                bit[j][d.charAt(j) - '0'] = 1;
            }
        }


        int minReachIndex = -1;
        int count = 0;
        for (int i = 0; i < target.length(); i++) {
            // 死亡范围
            int vIndex = target.charAt(i) - '0';
            // 判断死亡范围
            boolean dead = false;
            if (vIndex - 1 < 0) {
                dead = bit[i][vIndex + 1] == 1;
            } else if (vIndex + 1 >= 10) {
                dead |= bit[i][vIndex - 1] == 1;
            } else {
                dead |= bit[i][vIndex - 1] == 1 || bit[i][vIndex + 1] == 1;
            }

            if (dead) {
                count += target.charAt(i) - '0';
            } else if (minReachIndex == -1) {
                minReachIndex = i;
            } else if (target.charAt(i) < target.charAt(minReachIndex)) {
                // 交换暂停位置
                count -= target.charAt(minReachIndex) - '0';
                count += target.charAt(i) - '0';
                minReachIndex = i;
            } else {
                count += target.charAt(i) - '0';
            }
        }
        return minReachIndex == -1 ? -1 : count + target.charAt(minReachIndex) - '0';
    }

    public static class Node {

        Node[] next;

        public Node() {
            next = new Node[10];
        }

        /**
         * 不经过死亡可达
         *
         * @param c
         * @return
         */
        public boolean reachable(char c) {
            return next[c - '0'] == null;
        }
    }
}
