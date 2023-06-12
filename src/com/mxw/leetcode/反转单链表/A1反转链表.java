package com.mxw.leetcode.反转单链表;

import com.mxw.leetcode.链表.entity.ListNode;

public class A1反转链表 {

    /**
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     *
     * 输入：head = [1,2,3,4,5，6]
     * 输出：[5,4,3,2,1]
     *
     * 输入：head = [1,2]
     * 输出：[2,1]
     */

    ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 反转 last=6 head=1
        ListNode last = reverse(head.next);
        // 执行完后变成：6->5->4->3->2->null
        // 1->2->null
        head.next.next = head;
        // 1->2->1
        head.next = null;
        // 1->null
        // 6->5->4->3->2->1->null
        return last;
    }
}
