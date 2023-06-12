package com.mxw.leetcode.反转单链表;

import com.mxw.leetcode.链表.entity.ListNode;

public class A2反转链表前N个节点 {
    // 后驱节点
    ListNode successor = null;
    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }

        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);
        head.next.next = head;

        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;

    }
}
