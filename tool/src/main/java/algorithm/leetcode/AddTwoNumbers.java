package algorithm.leetcode;

import java.util.ArrayList;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carryFlag = 0, num1 = 0, num2 = 0, sum = 0, targetVal = 0;
        ArrayList<ListNode> result = new ArrayList<>();
        ListNode tmp = null;
        int i = 0;
        while (l1 != null || l2 != null) {
            num1 = (l1 != null) ? l1.val : 0;
            num2 = (l2 != null) ? l2.val : 0;
            sum = num1 + num2 + carryFlag;
            targetVal = sum % 10;
            carryFlag = sum / 10;
            tmp = new ListNode(targetVal);
            result.add(tmp);
            if (i != 0) {
                result.get(i - 1).next = tmp;
            }
            i++;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        //处理最后可能产生的进位
        if (carryFlag != 0) {
            result.get(result.size() - 1).next = new ListNode(carryFlag);
        }
        return result.get(0);
    }
}

