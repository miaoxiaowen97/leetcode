package com.mxw.leetcode.反转单链表;

import com.mxw.leetcode.链表.entity.ListNode;

public class A4K个一组翻转链表 {
    /**
      给你链表的头节点 head ，每k个节点一组进行翻转，请你返回修改后的链表。
      k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
      你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

     输入：head = [1,2,3,4,5], k = 2
     输出：[2,1,4,3,5]

     输入：head = [1,2,3,4,5], k = 3
     输出：[3,2,1,4,5]

     */

    /** 反转区间 [a, b) 的元素，注意是左闭右开 */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

    ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }
}
