package fit.ome.algorithm.code31;
/**
 * 线段树
 *
 * 定义：支持在数组 上的一定范围内，进行批量操作，时间复杂度控制在O(logN)如：
 *
 * 1. 指定区间进行增加
 *
 * 2. 指定区间进行编辑
 *
 * 3. 指定区间进行查询
 *
 *
 * 改动：
 *
 * 最大值线段树
 *
 * 房屋颜色问题
 *
 * 不适用场景：
 * 要求对区间执行操作，和下游数据相关性不强的场景；
 *
 * 如：给定一个数组，
 *
 *
 * 原理：
 *
 * 数据结构
 * 将数组形成二叉树形状，叶子节点存放原数组值，每个非叶子节点存放左右子树的和
 *
 * 在非叶子节点，冗余相关操作的数据值；冗余值在被操作范围覆盖节点和值范围的时候，执行缓存
 * 对具体操作进行延迟操作
 */