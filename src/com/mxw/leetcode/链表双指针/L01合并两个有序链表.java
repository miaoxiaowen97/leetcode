package com.mxw.leetcode.链表双指针;

import com.mxw.leetcode.链表.entity.ListNode;

public class L01合并两个有序链表 {
    /**
     * 题目概述：
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     *
     * 输入：l1 = [1,2,4], l2 = [1,3,4]
     * 输出：[1,1,2,3,4,4]
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 虚拟头节点
        ListNode dummy=new ListNode(-1);
        // 虚拟链表指针
        ListNode p=dummy;
        // 两个链表的指针
        ListNode p1=l1,p2=l2;

        // 遍历链表
        while (p1!=null&&p2!=null){
            // 比较p1和p2两个指针
            // 将值比较小的节点赋值给p指针
            if (p1.value> p2.value){
                p.next=p2;
                p2=p2.next;
            }else {
                p.next=p1;
                p1=p1.next;
            }
            //p指针移动一位
            p=p.next;
        }

        //移到最后一位，存在有一个为null，边界判断
        if (p1!=null){
            p.next=p1;
        }
        if (p2!=null){
            p.next=p2;
        }

        // 返回结果dummy
        return dummy.next;
    }
}
