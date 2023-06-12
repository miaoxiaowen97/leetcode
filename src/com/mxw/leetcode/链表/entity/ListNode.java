package com.mxw.leetcode.链表.entity;

public class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int x) {
           value = x;
          next = null;
      }

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    public ListNode() {
    }
}
