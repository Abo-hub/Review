
## 【题目】
输入两个链表，找出它们的第一个公共结点。
## 【解答】
### 方法一
因为输入的为两个单链表，如果有公共节点，那么此公共节点为两个链表共同指向的节点，公共节点后面的节点为两个链表公用的部分。
1. 分别求出两个链表的长度。然后求出两个链表的长度差记为len;
2. 先将较长的链表遍历len次，与另一个链表处于同一长度。
3. 两个链表一起遍历比较值，如果有公共点，返回即可。如果其中一个链表到达尾部，说明没有公共点。
详细过程看下面代码 
```java
public static class ListNode{
        public int val;
        ListNode next;

        public ListNode(int val){
            this.val = val;
        }
    }

    public ListNode findFirstCommonNode(ListNode pHead1,ListNode pHead2){
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

    public  int getListLength(ListNode head){
        int length=0;
        while (head != null){
            head = head.next;
            length++;
        }
        return length; 
    }
}
```