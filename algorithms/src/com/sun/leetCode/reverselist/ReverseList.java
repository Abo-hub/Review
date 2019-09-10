package com.sun.leetCode.reverselist;

/**
 * @program: Review
 * @author: SunBo
 * @create: 2019-09-10 16:34
 **/

public class ReverseList {

    public static void main(String[] args) {
        Node  node1 = new Node(1);
        Node  node2 = new Node(2);
        Node  node3 = new Node(3);
        Node  node4 = new Node(4);
        Node  node5 = new Node(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        Node res = reverseList(node1);
        System.out.println(res.value);

    }

    public static Node reverseList(Node head){
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
class Node{
    public int value;
    public Node next;

    public Node(int value){
        this.value = value;
    }
}
