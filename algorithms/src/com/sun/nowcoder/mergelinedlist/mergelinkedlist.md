# 合并两个有序的单链表

## 【题目】
给定两个有序单链表head1和head2，请合并两个两个有序链表，合并后的链表依然有序，并返回合并后链表的头节点。
例如：
```java
0->2->3->7->null
1->3->5->7->9->null
```
合并后的链表为：0->1->2->3->3->5->7->7->9 

## 【解答】
1. 如果两个链表中有一个为空，说明无需合并过程，返回另一个链表头即可。
2. 比较head1 和 head2 的值，小的节点也就是合并后链表的最小节点，这个节点就是合并后链表的头节点，我们记为head；在之后的步骤里，那个链表的头节点的值更小，另一个链表的所有节点都会依次插入到这个链表中。
3. 我们让head1 和 head2 从头开始一起遍历，比较每次遍历到的两个节点的值，记为cur1和cur2，然后根据大小关系做出不同的调整，同时用一个变量pre表示上次比较较小的值。
4.如果其中一条链表走完，那就把另一条链表剩下的部分直接拼到最后，调整结束。
5. 返回合并后链表的头节点head即可。

```java
//链表结构
class  Node {
    public int value;
    public Node next;

    public Node(int data){
        this.value = data;
    }
}
public class MergeLinkedList {
    public Node merge(Node head1,Node head2){
        //如果其中一条链表为空，直接返回另一条链表的头节点
        if(head1 == null || head2 == null){
            return head1 !=null ? head1 : head2;
        }
        //将头节点较小的链表赋给head
        Node head = head1.value < head2.value ? head1 : head2;
        //较小链表的节点值
        Node cur1 = head == head1 ? head1 : head2;
        //另一条链表的节点值
        Node cur2 = head == head1 ? head2: head1;
        //保存上次比较时较小的值
        Node pre = null;
        //保存cur2的下个节点
        Node next = null;
        while (cur1 != null && cur2 != null){
            //如果cur1<cur2 将cur1往后移
            if(cur1.value <= cur2.value){
                pre = cur1;
                cur1 = cur1.next;
            }else{  //如果cur1>cur2 将cur2插入到head链表
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2: cur1;
        return head;
    }
}


```