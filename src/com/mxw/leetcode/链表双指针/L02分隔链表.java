package com.mxw.leetcode.链表双指针;

import com.mxw.leetcode.链表.entity.ListNode;

public class L02分隔链表 {
    /**
     * 题目描述：
     * 给你一个链表的头节点 head 和一个特定值 x ，
     * 请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
     * 你应当 保留 两个分区中每个节点的初始相对位置。
     * 输入：head = [1,4,3,2,5,2], x = 3
     * 输出：[1,2,2,4,3,5]
     * 具体来说，我们可以把原链表分成两个小链表，一个链表中的元素大小都小于 x（1，2，2），
     * 另一个链表中的元素都大于等于 x（4，3，5），最后再把这两条链表接到一起，就得到了题目想要的结果。
     */

    public ListNode partition(ListNode head, int x) {
        // 存放小于x的链表的虚拟头节点
        ListNode dummy1=new ListNode(-1);
        // 存放大于等于x的链表的虚拟头节点
        ListNode dummy2=new ListNode(-1);
        // p1,p2指针负责生成结果链表
        ListNode p1=dummy1;
        ListNode p2=dummy2;
        // 指针P负责遍历原链表，类似合并两个有序链表的逻辑
        //这里是将一个链表分解成两个链表
        ListNode p=head;
        while (p!=null){
            if (p.value>=x){
                p2.next=p;
                p2=p2.next;
            }else {
                p1.next=p;
                p1=p1.next;
            }
            // 断开原链表中的每个节点的next指针
            ListNode temp=p.next;
            p.next=null;
            p=temp;
        }
        //连接两个链表
        p1.next=dummy2.next;
        return dummy1.next;
    }

}
