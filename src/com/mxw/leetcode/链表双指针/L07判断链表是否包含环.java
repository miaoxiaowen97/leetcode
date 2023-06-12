package com.mxw.leetcode.链表双指针;

import com.mxw.leetcode.链表.entity.ListNode;

public class L07判断链表是否包含环 {
    /**
     * 每当慢指针 slow 前进一步，快指针 fast 就前进两步。
     * 如果 fast 最终遇到空指针，说明链表中没有环；如果fast最终和slow相遇，那肯定是fast超过了slow一圈，说明链表中含有环。
     *
     */

    boolean hasCycle(ListNode head) {
        // 快慢指针初始化指向 head
        ListNode slow = head, fast = head;
        // 快指针走到末尾时停止
        while (fast != null && fast.next != null) {
            // 慢指针走一步，快指针走两步
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇，说明含有环
            if (slow == fast) {
                return true;
            }
        }
        // 不包含环
        return false;
    }
}
