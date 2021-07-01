package com.cecilia.List;

public class ReverseList {
    public ListNode reverseLinkedListIteratively(ListNode head) {
        // Myth: Treat the to-ne-returned list as a new one
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newListHead = null;
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = newListHead;
            newListHead = head;
            head = next;
        }
        return newListHead;
    }

    public ListNode reverseLinkedListRecursively(ListNode head) {
        // Myth:
        // 1. Base case clarify:
        //   head == null is not the base case as it won't be used in the recursive rule, it's just a special case.
        // 2. Key point:
        //   head.next pointer still remains when the last recursion finishes,
        //   hence we don't need to find the tail of the partially reversed new list.

        // Base case: only one node, return directly
        // Recursive rule: head.next.next = head; head.next= null
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseLinkedListRecursively(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public ListNode fancyReverse(ListNode head) {
        // Input:  N1 -> N2 -> N3 -> N4 -> N5 -> N6
        // Output: N2 -> N1 -> N4 -> N3 -> N6 -> N5
        if (head == null || head.next == null) {
            return head;
        }
        
        // 这一例与上一例的区别在于，上例中的每次reverse返回的list head都保持不变，因此null head不能作为base case。
        // 而此例中上一次recursion返回的list head仅被用作next赋值。Empty list & one-node list均为base case.
        // base case
        // if (head.next.next == null) {
        //     head.next.next = head;
        //     head = head.next;
        //     head.next.next = null;
        //     return head;
        // }

        //cursive rule
        ListNode newHead = fancyReverse(head.next.next);
        head.next.next = head;
        head = head.next;
        head.next.next = newHead;
        
        return head;
    }

    class ListNode {
        String name;
        int value;
        ListNode next;
    }
}
