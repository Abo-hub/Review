package com.sun.nowcoder.findKthToTail;

import java.util.LinkedList;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-07-31 21:11
 **/

public class FindKthToTail {
    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode findKthToTail(ListNode head, int k) {
        ListNode p, q;
        p = q = head;
        int i = 0;
        for (; p != null; i++) {
            if (i >= k) {
                q = q.next;
            }
            p = p.next;
        }
        return i < k ? null : q;
    }
}
