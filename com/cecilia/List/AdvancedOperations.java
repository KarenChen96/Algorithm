package com.cecilia.List;

public class AdvancedOperations {

    /**
     * MergeSort Linked List
     * @param head
     * @return
     */
    public static ListNode mergeSort(ListNode head) {
        // Time complexity: findMiddleNode() also takes O(N) in each recursion level.
        // Time complexity = O(N*logN + N*logN) => O(N*logN)
        if (head == null || head.next == null) {
            return head;
        }

        ListNode middleNode = BasicOperations.findMiddleNode(head);

        ListNode secondHalf = middleNode.next;
        middleNode.next = null;
        return BasicOperations.merge2SortedLists(mergeSort(head), mergeSort(secondHalf));
    } 

    /**
     * Given two linked lists representing two non-negative numbers. The digits are stored in
     * the reverse order and each of their nodes contain a single bit. Add the two 
     * numbers and return it as a linked list.
     */
    public static ListNode addNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode curr = dummyHead;
        int value = 0;
        while (l1 != null || l2 != null || value > 0) {   
            if (l1 != null) {
                value += l1.value;
                l1 = l1.next;
            } 
            if (l2 != null) {
                value += l2.value;
                l2 = l2.next;
            } 
            curr.next = new ListNode(value%10);
            value = value/10;
            curr = curr.next;
        }
        return dummyHead.next;
    }

    /**
     * Follow up: The digits are stored in the normal order.
     * Idea: reverse two lists, add, reverse the result.
     * Any better solution?
     */
    public static ListNode addNumbersII(ListNode l1, ListNode l2) {
        l1 = ReverseList.reverseLinkedListRecursively(l1);
        l2 = ReverseList.reverseLinkedListIteratively(l2);
        return ReverseList.reverseLinkedListIteratively(addNumbers(l1, l2));
    }

    /**
     * Check if a linked list is a palindrome.
     * 
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        // Clarification: 
        //  1. Can we change the input list structure?
        //  2. Is additional space allowed to use? (If we can use additional space -> using a stack.)

        if (head == null || head.next == null) {
            return true;
        }
        ListNode middleNode = BasicOperations.findMiddleNode(head);
        ListNode secondHalf = ReverseList.reverseLinkedListRecursively(middleNode.next);
        middleNode.next = null;
        while (secondHalf != null) {
            if (head.value != secondHalf.value) {
                return false;
            }
            head = head.next;
            secondHalf = secondHalf.next;
        }
        return true;
    }


    /**
     * Remove all nodes with target value
     * @param head
     * @param target
     * @return
     */
    public static ListNode removeElements(ListNode head, int target) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode curr = dummyHead;
        while (curr.next != null) {
            if (curr.next.value == target) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return dummyHead.next;
    }
    
}
