package com.mxw.leetcode.链表双指针;

import com.mxw.leetcode.链表.entity.ListNode;

public class L10删除排序链表中的重复元素 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head==null){
            return null;
        }
        ListNode slow=head,fast=head;

        while (fast!=null){
            if (fast.value!= slow.value){
                slow.next=fast;
                slow=slow.next;
            }

            fast=fast.next;

        }

        slow.next=null;
        return head;
    }

}
