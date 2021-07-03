package com.cecilia.List;

public class BasicOperations {
    /**
     * Find the middle node of a linked list
     * @param head
     * @return
     */
    public static ListNode findMiddleNode(ListNode head) {
        // Extend reading: online algorithm VS. offline algorithm
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next !=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * Check if a linked list has a cycle using fast & slow pointers.
     * @param head
     * @return
     */
    public static boolean ifCircular(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // Analysis: n = list.length times of iterations are needed if is circular.
        // Time complexity: O(N)
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * Insert a node in a sorted linked list (simple version: traverse one by one).
     * @param head
     * @param node
     */
    public static ListNode insertNodeInSortedList(ListNode head, ListNode node) {
        // Before implementation：clarification! 
        //  1. what if the insert value is duplicated? 2. ...
        // Myth: List head may be changed, so must return head!
        if (head == null || head.value >= node.value) {
            node.next = head;
            return node;
        }

        ListNode curr = head;
        while (curr.next != null && curr.next.value < node.value) {
            curr = curr.next;
        }
        node.next = curr.next;
        curr.next = node;
        return head;
    }

    /**
     * Insert a node in a sorted linked list with using dummy node(simple version: traverse one by one).
     * @param head
     * @param node
     */
    public static ListNode insertNodeInSortedListWithDummy(ListNode head, ListNode node) {
        // Note: In the case list head is not determined, using dummy node will be helpful.
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode curr = dummy;
        while (curr.next != null && curr.next.value < node.value) {
            curr = curr.next;
        }
        node.next = curr.next;
        curr.next = node;
        return dummy.next;
    }

    /**
     * Insert a node in a circular sorted linked list.
     * @param head
     * @param node
     * @return
     */
    public static ListNode insertNodeInCircularSortedList(ListNode head, ListNode node) {
        // Myth: The very special position is the one between the head and the tail.
        // The node's value can be larger than the tails' or smaller than the heads', need to differentiate the two cases.
        
        // Exceptional case. But then the input and output list are not circular linked list neither.
        if (head == null) {
            return node;
        }
        // Find the tail that has the same functionality as dummyHead here.
        ListNode tail = head;
        while (tail.next != head) {
            // When traversing the list, also check if the node can be inserted t the middle part of the list
            if (tail.next.value >= node.value) {
                node.next = tail.next;
                tail.next = node;
                return head;
            }
            tail = tail.next;
        }

        // Otherwise, the node will be inserted the position between the tail and the head.
        node.next = tail.next;
        tail.next = node;
        return node.value > tail.value ? node.next : node;
    }

    /**
     * 
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode merge2SortedLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode curr = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.value <= l2.value) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            curr.next = l1;
        } else if (l2 != null) {
            curr.next = l2;
        }
        return dummyHead.next;
    }
    
    /**
     * Convert a list: from N1 -> N2 -> N3 -> N4 -> N5 -> N6 -> ... -> Nn-> null
     *                  to  (N1->Nn) -> (N2->Nn-1) -> ...
     * @param head
     * @return
     */
    public static ListNode reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        ListNode middleNode = findMiddleNode(head);
        ListNode secondHalfList = middleNode.next;
        secondHalfList = ReverseList.reverseLinkedListRecursively(secondHalfList);
        middleNode.next = null;
        return merge2SortedLists(head, secondHalfList);
    }

    /**
     * Given a linked list and a target value x, partition it such that all nodes less than x are listed 
     * before the nodes larger than or equal to target value. Keep the original relative order of nodes 
     * in each of the two partitions.
     */
    public static ListNode partitionList(ListNode head, int target) {
        // Note: Key point: how to avoid cycle when changing node's next pointer.
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHead = new ListNode();
        ListNode pos = dummyHead;
        ListNode tail = dummyHead;
        ListNode curr = head;
        while (curr != null) {
            if(curr.value < target) {
                ListNode temp = pos.next;
                pos.next = curr;
                curr = curr.next;
                pos.next.next = temp;
                pos = pos.next;
            } else {
                tail.next = curr;
                curr = curr.next;
                tail.next.next = null;
                tail = tail.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode partitionList2(ListNode head, int target) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode dummyHeadSmall = new ListNode();
        ListNode dummyHeadLarge = new ListNode();
        ListNode smallTail = dummyHeadSmall;
        ListNode largeTail = dummyHeadLarge;
        ListNode curr = head;
        while (curr != null) {
            if(curr.value < target) {
                smallTail.next = curr;
                smallTail = smallTail.next;
            } else {
                largeTail.next = curr;
                largeTail = largeTail.next;
            }
            curr = curr.next; // curr.next doesn't change in above process
        }
        largeTail.next = null; // Without this step might cause cycle.
        smallTail.next = dummyHeadLarge.next;
        return dummyHeadSmall.next;
    }
}
