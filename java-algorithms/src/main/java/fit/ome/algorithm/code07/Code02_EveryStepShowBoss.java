package fit.ome.algorithm.code07;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 手动改写堆题目练习
 * <p>
 * <p>
 * 给定一个整型数组，int[] arr；和一个布尔类型数组，boolean[] op
 * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
 * arr = [ 3   ,   3   ,   1   ,  2,      1,      2,      5…
 * op = [ T   ,   T,      T,     T,      F,      T,       F…
 * 依次表示：3用户购买了一件商品，3用户购买了一件商品，1用户购买了一件商品，2用户购买了一件商品，1用户退货了一件商品，2用户购买了一件商品，5用户退货了一件商品
 * <p>
 * <p>
 * 一对arr[i]和op[i]就代表一个事件：
 * 用户号为arr[i]，op[i] == T就代表这个用户购买了一件商品
 * op[i] == F就代表这个用户退货了一件商品
 * 现在你作为电商平台负责人，你想在每一个事件到来的时候，
 * 都给购买次数最多的前K名用户颁奖。
 * 所以每个事件发生后，你都需要一个得奖名单（得奖区）。
 * <p>
 * <p>
 * 得奖系统的规则：
 * 1，如果某个用户购买商品数为0，但是又发生了退货事件，
 * 则认为该事件无效，得奖名单和上一个事件发生后一致，例子中的5用户
 * 2，某用户发生购买商品事件，购买商品数+1，发生退货事件，购买商品数-1
 * 3，每次都是最多K个用户得奖，K也为传入的参数
 * 如果根据全部规则，得奖人数确实不够K个，那就以不够的情况输出结果
 * <p>
 * 4，得奖系统分为得奖区和候选区，任何用户只要购买数>0，
 * 一定在这两个区域中的一个
 * 5，购买数最大的前K名用户进入得奖区，
 * 在最初时如果得奖区没有到达K个用户，那么新来的用户直接进入得奖区
 * 6，如果购买数不足以进入得奖区的用户，进入候选区
 * <p>
 * 7，如果候选区购买数最多的用户，已经足以进入得奖区，
 * 该用户就会替换得奖区中购买数最少的用户（大于才能替换），
 * 如果得奖区中购买数最少的用户有多个，就替换最早进入得奖区的用户
 * 如果候选区中购买数最多的用户有多个，机会会给最早进入候选区的用户
 * <p>
 * <p>
 * 8，候选区和得奖区是两套时间，
 * 因用户只会在其中一个区域，所以只会有一个区域的时间，另一个没有
 * 从得奖区出来进入候选区的用户，得奖区时间删除，
 * 进入候选区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i）
 * 从候选区出来进入得奖区的用户，候选区时间删除，
 * 进入得奖区的时间就是当前事件的时间（可以理解为arr[i]和op[i]中的i
 * <p>
 * <p>
 * <p>
 * 9，如果某用户购买数==0，不管在哪个区域都离开，区域时间删除，
 * 离开是指彻底离开，哪个区域也不会找到该用户
 * 如果下次该用户又发生购买行为，产生>0的购买数，
 * 会再次根据之前规则回到某个区域中，进入区域的时间重记
 *
 * @version 0.0.1-SNAPSHOT
 * @auther Zero
 * @date 2021/4/11
 **/
public class Code02_EveryStepShowBoss {
    /**
     * k 名用户
     *
     * @param arr
     * @param op
     * @param k
     * @return
     */
    public static List<List<Integer>> topK(int[] arr, boolean[] op, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        WhosYourAward whosYourAward = new WhosYourAward(k);
        for (int i = 0; i < arr.length; i++) {
            whosYourAward.operate(i, arr[i], op[i]);
            ans.add(whosYourAward.getAwards());
        }
        return ans;


    }

    public static class WhosYourAward {
        Map<Integer, Customer> userMap = new HashMap<>();
        EnhanceHeap<Customer> candHeap = new EnhanceHeap<>(new CandidateComparator());
        EnhanceHeap<Customer> awardHeap = new EnhanceHeap<>(new AwardComparator());
        private final int awardLimit;

        public WhosYourAward(int limit) {
            awardLimit = limit;
        }

        public void operate(int time, int id, boolean buy) {
            if (!buy && !userMap.containsKey(id)) {
                // 忽略买记录，退货的情况
                return;
            }
            // 处理新买的情况
            if (!userMap.containsKey(id)) {
                userMap.put(id, new Customer(id, 0, 0));
            }
            Customer customer = userMap.get(id);
            // 维护购买数量
            if (buy) {
                customer.buyCount++;
            } else {
                customer.buyCount--;
            }
            // 维护用户基础信息
            if (customer.buyCount == 0) {

                userMap.remove(id);

            }
            //
            // 讨论在各奖区中的状态信息
            //
// 如果之前没在这两个区域，说明，用户是新买用户
            if (!candHeap.contain(customer) && !awardHeap.contain(customer)) {
                // 不在两个奖区中

                if (awardHeap.size() < awardLimit) {
                    customer.enterTime = time;
                    awardHeap.push(customer);
                } else {
                    customer.enterTime = time;
                    candHeap.push(customer);
                }


            } else if (candHeap.contain(customer)) {
                // 在候选区
                if (customer.buyCount > 0) {
                    candHeap.resign(customer);
                } else {
                    candHeap.remove(customer);
                }

            } else {
                // 在获奖区域
                if (customer.buyCount > 0) {
                    awardHeap.resign(customer);
                } else {
                    awardHeap.remove(customer);
                }
            }

            // 讨论获奖区域和候选区域的移动问题
            awardMove(time);

        }

        /**
         * 需要当前时间传进来
         * <p>
         * 在候选区和获奖区域交换成员的时候，需要保留当前时间
         *
         * @param time
         */
        public void awardMove(int time) {
            // 候选区无人员，不需要讨论
            if (candHeap.isEmpty()) {
                return;
            }
            if (awardHeap.size() < awardLimit) {
                Customer c = candHeap.pop();
                awardHeap.push(c);
            } else {
                if (candHeap.peek().buyCount > awardHeap.peek().buyCount) {
                    Customer cand = candHeap.pop();
                    cand.enterTime = time;
                    Customer award = awardHeap.pop();
                    award.enterTime = time;
                    candHeap.push(award);
                    awardHeap.push(cand);
                }
            }

        }

        public List<Integer> getAwards() {
            return awardHeap.getAll().stream().map(c -> c.id).collect(Collectors.toList());
        }


    }

    /**
     * 用户对象
     */
    public static class Customer {
        int id;
        int buyCount;
        int enterTime;

        public Customer(int id, int c, int t) {
            this.id = id;
            this.buyCount = c;
            enterTime = t;
        }
    }

    /**
     * 奖励名单区域比较器
     * <p>
     * 增强堆的堆属性和系统一致属于小根堆
     * <p>
     * 比较器原则根据火箭原则设计
     * 先比较购买数量
     * 然后比较进入奖单区域的时间
     */
    public static class AwardComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyCount < o2.buyCount ? -1 : (o1.buyCount > o2.buyCount ? 1 : o1.enterTime < o2.enterTime ? -1 : 1);
        }
    }

    /**
     * 候选区比较器
     * <p>
     * 增强堆属于小根堆
     * <p>
     * 根据候选区原则
     * 先比较购买数量
     * 再比较既然怒候选区的时间
     */
    public static class CandidateComparator implements Comparator<Customer> {

        @Override
        public int compare(Customer o1, Customer o2) {
            return o1.buyCount > o2.buyCount ? -1 : (o1.buyCount < o2.buyCount ? 1 : (o1.enterTime < o2.enterTime ? -1 : 1));
        }
    }
// ----------------------------- 加强堆，方法结束
    // 开始暴力解

    /**
     * 不进行任何优化，暴力维护
     *
     * @param arr
     * @param op
     * @param k
     * @return
     */
    public static List<List<Integer>> topK2(int[] arr, boolean[] op, int k) {
        HashMap<Integer, Customer> map = new HashMap<>();
        ArrayList<Customer> cands = new ArrayList<>();
        ArrayList<Customer> daddy = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            boolean buy = op[i];
            int id = arr[i];
            if (!buy && !map.containsKey(id)) {
                ans.add(getCurrAward(daddy));
                continue;
            }

            if (!map.containsKey(id)) {
                map.put(id, new Customer(id, 0, 0));
            }
            Customer c = map.get(id);
            if (buy) {
                c.buyCount++;
            } else {
                c.buyCount--;
            }

            if (c.buyCount == 0) {
                map.remove(id);
            }

            if (!cands.contains(c) && !daddy.contains(c)) {
                // 新买用户
                if (daddy.size() < k) {
                    c.enterTime = i;
                    daddy.add(c);
                } else {
                    c.enterTime = i;
                    cands.add(c);
                }
            }

            clearZeroBuy(daddy);
            clearZeroBuy(cands);
            daddy.sort(new AwardComparator());
            cands.sort(new CandidateComparator());

            move(cands, daddy, k, i);
            ans.add(getCurrAward(daddy));

        }
        return ans;
    }

    public static List<Integer> getCurrAward(List<Customer> list) {
        return list.stream().map(c -> c.id).collect(Collectors.toList());
    }

    public static void clearZeroBuy(List<Customer> list) {
        List<Customer> noZero = new ArrayList<>();
        noZero.addAll(list.stream().filter(c -> c.buyCount > 0).collect(Collectors.toList()));
        list.clear();
        list.addAll(noZero);
    }

    public static void move(List<Customer> cand, List<Customer> award, int k, int time) {
        if (cand.isEmpty()) {
            return;
        }
        if (award.size() < k) {
            Customer e = cand.get(0);
            e.enterTime = time;
            award.add(e);
            cand.remove(0);
        } else {
            if (cand.get(0).buyCount > award.get(0).buyCount) {
                Customer newAward = cand.get(0);
                cand.remove(0);
                Customer oldAward = award.get(0);
                award.remove(0);
                newAward.enterTime = time;
                oldAward.enterTime = time;
                award.add(newAward);
                cand.add(oldAward);

            }
        }
    }
    // -------------------- 暴力方法结束
    // 对数器验证

    // for test
    public static class Data {
        int[] arr;
        boolean[] op;

        public Data(int[] a, boolean[] o) {
            arr = a;
            op = o;
        }
    }

    // for test
    public static Data generateData(int maxVal, int maxSize) {
        int len = maxRandom(maxSize);
        int[] arr = new int[len];
        boolean[] op = new boolean[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = maxRandom(maxVal);
            op[i] = Math.random() < 0.5;
        }
        return new Data(arr, op);
    }

    public static int maxRandom(int mx) {
        return (int) (Math.random() * mx + 1);
    }

    public static boolean isSameAns(List<List<Integer>> ans1, List<List<Integer>> ans2) {
        if (ans1.size() != ans2.size()) {
            return false;
        }
        for (int i = 0; i < ans1.size(); i++) {
            List<Integer> a1 = ans1.get(i);
            List<Integer> a2 = ans2.get(i);
            if (a1.size() != a2.size()) {
                return false;
            }
            a1.sort((a, b) -> a - b);
            a2.sort((a, b) -> a - b);
            for (int j = 0; j < a1.size(); j++) {
                if (a1.get(j) != a2.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

//        int[] arr = new int[]{8,
//                9,
//                9,
//                9,
//                9};
//        boolean[] op = new boolean[]{false
//                , true
//                , false
//                , false
//                , true};
//        topK2(arr,op,1);

        int maxValue = 100;
        int maxSize = 20;
        int maxK = 6;
        int opTimes = 100000;
        for (int i = 0; i < opTimes; i++) {
            int k = maxRandom(maxK);
            Data data = generateData(maxValue, maxSize);
            int[] arr = data.arr;
            boolean[] op = data.op;
            List<List<Integer>> lists = topK(arr, op, k);
            List<List<Integer>> lists1 = topK2(arr, op, k);
            if (!isSameAns(lists1, lists)) {
                System.out.println("Oops!!!");
                for (int j = 0; j < arr.length; j++) {
                    System.out.println(arr[j] + "," + op[j]);
                }
                System.out.println(k);
                System.out.println(lists);
                System.out.println(lists1);
                break;
            }
        }
        System.out.println("finish");
    }

}
