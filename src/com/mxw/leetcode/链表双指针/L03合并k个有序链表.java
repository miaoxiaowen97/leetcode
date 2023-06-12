package com.mxw.leetcode.链表双指针;

import com.mxw.leetcode.链表.entity.ListNode;

import java.util.PriorityQueue;

public class L03合并k个有序链表 {

    /**
     *
     *给你一个链表数组，每个链表都已经按升序排列。
     *请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     *题目描述：
     * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
     * 输出：[1,1,2,3,4,4,5,6]
     * 解释：链表数组如下：
     * [
     *   1->4->5,
     *   1->3->4,
     *   2->6
     * ]
     * 将它们合并到一个有序链表中得到。
     * 1->1->2->3->4->4->5->6
     *
     * 用到 优先级队列（二叉堆） 这种数据结构，
     * 把链表节点放入一个最小堆，就可以每次获得 k 个节点中的最小节点：
     *
     * [1],4,5
     * [1],3,4
     * [2,6]
     *
     * =>p:1,        p:1,1     p:1,1,2    p:1,1,2,3
     *1,[4],5       1,[4],5    1,[4],5    1,[4],5
     * [1],3,4    =>1,[3],4  =>1,[3],4=>1,3,[4]
     * [2,6]          [2],6    2,[6]      2,[6]
     */

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length==0){
            return null;
        }
        //虚拟头节点
        ListNode dummy=new ListNode(-1);
        ListNode p=dummy;

        //优先级队列，构建最小堆
        PriorityQueue<ListNode> pq=new PriorityQueue<>(
                lists.length,(a,b)->(a.value-b.value)
        );
        //将k个链表的头节点加入最小堆
        for (ListNode head : lists) {
            if (head!=null){
                pq.add(head);
            }
        }

        // 遍历队列，找到队列中最小的节点，复制给新节点
        while (!pq.isEmpty()){
            //获取最小节点，接到结果链表中
            ListNode node=pq.poll();
            p.next=node;
            // 将该链表的下一个节点加入队列中
            if (node.next!=null){
                pq.add(node.next);
            }
            //p指针不断前进
            p=p.next;
        }

        return dummy.next;

    }
}
