package com.mxw.leetcode.回文链表;

import com.mxw.leetcode.链表.entity.ListNode;

public class A1回文链表 {

    /**
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     * <p>
     * 输入：head = [1,2,2,1]
     * 输出：true
     */

    // 左侧指针
    ListNode left;

    /**递归
     * @param head
     * @return
     */
    boolean isPalindrome(ListNode head) {
        left = head;
        return traverse(head);
    }

    boolean traverse(ListNode right) {
        if (right == null) {
            return true;
        }
        boolean res = traverse(right.next);
        // 后序遍历代码
        res = res && (right.value == left.value);
        left = left.next;
        return res;
    }

    /**
     * 双指针版本
     */
    boolean isPalindrome2(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        // 找中点 slow
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (fast != null) {
            slow = slow.next;
        }

        ListNode left = head;
        // 找到中点后半部分进行反转，与前一部分进行对比
        ListNode right = reverse(slow);
        while (right != null) {
            if (left.value != right.value) {
                return false;
            }
            left = left.next;
            right = right.next;
        }
        return true;
    }

    /**
     * 反转
     * @param head
     * @return
     */
    ListNode reverse(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
