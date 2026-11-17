class Node {
    String title;
    int dueDay;
    Node next;

    public Node(String title, int dueDay) {
        this.title = title;
        this.dueDay = dueDay;
        this.next = null;
    }
}

public class LibraryReturnsSorter {

    public static Node sortByDueDay(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node mid = getMiddle(head);
        Node nextOfMid = mid.next;
        mid.next = null;

        Node left = sortByDueDay(head);
        Node right = sortByDueDay(nextOfMid);

        return sortedMerge(left, right);
    }

    private static Node getMiddle(Node head) {
        if (head == null) {
            return head;
        }
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static Node sortedMerge(Node a, Node b) {
        Node dummy = new Node("", 0);
        Node tail = dummy;

        Node currA = a;
        Node currB = b;

        while (currA != null && currB != null) {
            if (currA.dueDay <= currB.dueDay) {
                tail.next = currA;
                currA = currA.next;
            } else {
                tail.next = currB;
                currB = currB.next;
            }
            tail = tail.next;
        }

        if (currA != null) {
            tail.next = currA;
        } else {
            tail.next = currB;
        }

        return dummy.next;
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.title + ":" + curr.dueDay + (curr.next != null ? " -> " : " -> null"));
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node returns = new Node("Java", 15);
        returns.next = new Node("DSA", 3);
        returns.next.next = new Node("OS", 9);
        returns.next.next.next = new Node("DBMS", 3);
        returns.next.next.next.next = new Node("CN", 12);
        returns.next.next.next.next.next = new Node("AI", 1);

        Node sorted = sortByDueDay(returns);
        printList(sorted);

        Node emptyReturns = null;
        Node sortedEmpty = sortByDueDay(emptyReturns);
        System.out.println(sortedEmpty);
    }
}