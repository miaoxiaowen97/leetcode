package com.mxw.leetcode.数组双指针;

import java.util.*;

public class A12O1时间插入删除和获取随机元素 {

    /**
      实现RandomizedSet 类：
     
      RandomizedSet() 初始化 RandomizedSet 对象
      bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
      bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
      int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
      你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。

     输入
     ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
     [[], [1], [2], [2], [], [1], [2], []]
     输出
     [null, true, false, true, 2, true, false, 2]

     解释
     RandomizedSet randomizedSet = new RandomizedSet();
     randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
     randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
     randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
     randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
     randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
     randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
     randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
     **/

    class RandomizedSet {
        // 存储元素的值
        List<Integer> nums;

        // 记录每个元素对应在 nums 中的索引
        Map<Integer, Integer> valToIndex;

        public RandomizedSet() {
            nums = new ArrayList<>();
            valToIndex = new HashMap<>();
        }

        public boolean insert(int val) {
            // 若 val 已存在，不用再插入
            if (valToIndex.containsKey(val)) {
                return false;
            }
            // 若 val 不存在，插入到 nums 尾部，
            // 并记录 val 对应的索引值
            valToIndex.put(val, nums.size());
            nums.add(val);
            return true;
        }

        public boolean remove(int val) {
            // 若 val 不存在，不用再删除
            if (!valToIndex.containsKey(val)) {
                return false;
            }
            // 先拿到 val 的索引
            int index = valToIndex.get(val);
            // 将最后一个元素对应的索引修改为 index
            valToIndex.put(nums.get(nums.size() - 1), index);
            // 交换 val 和最后一个元素
            Collections.swap(nums, index, nums.size() - 1);
            // 在数组中删除元素 val
            nums.remove(nums.size() - 1);
            // 删除元素 val 对应的索引
            valToIndex.remove(val);
            return true;
        }

        public int getRandom() {
            // 随机获取 nums 中的一个元素
            return nums.get((int) (Math.random() * nums.size()));
        }
    }
}
