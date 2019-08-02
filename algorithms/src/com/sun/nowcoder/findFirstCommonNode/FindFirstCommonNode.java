package com.sun.nowcoder.findFirstCommonNode;

import java.util.LinkedList;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-27 20:18
 **/

public class FindFirstCommonNode {

    public static class ListNode{
        public int val;
        ListNode next;

        public ListNode(int val){
            this.val = val;
        }
    }

    public static ListNode findFirstCommonNode(ListNode pHead1,ListNode pHead2){
        if (pHead1 == null || pHead2 == null){
            return null;
        }
        int listLength1 = getListLength(pHead1);
        int listLength2 = getListLength(pHead2);
        ListNode cur1 = pHead1;
        ListNode cur2 = pHead2;
       int len = listLength1 - listLength2;
       if (len<0){
           cur1 = pHead2;
           cur2 = pHead1;
       }
        for (int i = 0; i < len; i++) {
            cur1 = cur1.next;
        }
        while (cur1 != null && cur2 != null && cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    public static int getListLength(ListNode head){
        int length=0;
        while (head != null){
            head = head.next;
            length++;
        }
        return length;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(7);

        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(6);
        ListNode node8 = new ListNode(7);

        ListNode node9 = new ListNode(8);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node8;
        node8.next = node9;

        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;

        ListNode res = findFirstCommonNode(node1, node5);
        System.out.println(res.val);


    }
}
