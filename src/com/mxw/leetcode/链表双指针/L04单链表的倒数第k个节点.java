package com.mxw.leetcode.链表双指针;

import com.mxw.leetcode.链表.entity.ListNode;

public class L04单链表的倒数第k个节点 {

    /**
     * 假设链表有 n 个节点，倒数第 k 个节点就是正数第 n - k + 1 个节点.
     * 如何遍历一次得到第k个节点：使用双指针
     * 先让一个指针 p1 指向链表的头节点 head，然后走 k 步，现在的 p1，只要再走 n - k 步，就能走到链表末尾的空指针了。
     * 再用一个指针 p2 指向链表头节点 head，让 p1 和 p2 同时向前走，p1 走到链表末尾的空指针时前进了 n - k 步，
     * p2 也从 head 开始前进了 n - k 步，停留在第 n - k + 1 个节点上，即恰好停链表的倒数第 k 个节点上。这样，只遍历了一次链表，就获得了倒数第 k 个节点 p2。
     *
     */
    // 返回链表的倒数第 k 个节点
    ListNode findFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        // p1 先走 k 步
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }
        ListNode p2 = head;
        // p1 和 p2 同时走 n - k 步
        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        // p2 现在指向第 n - k + 1 个节点，即倒数第 k 个节点
        return p2;
    }
}
