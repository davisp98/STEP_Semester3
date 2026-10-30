class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MergeSortedTickets {

    public static Node mergeSorted(Node a, Node b) {
        Node dummy = new Node(0);
        Node tail = dummy;

        Node currA = a;
        Node currB = b;

        while (currA != null && currB != null) {
            if (currA.data <= currB.data) {
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
            System.out.print(curr.data + (curr.next != null ? " -> " : ""));
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node a = new Node(3);
        a.next = new Node(8);
        a.next.next = new Node(15);

        Node b = new Node(5);
        b.next = new Node(8);
        b.next.next = new Node(12);
        b.next.next.next = new Node(20);

        Node merged = mergeSorted(a, b);
        printList(merged);

        Node emptyA = null;
        Node nonEmptyB = new Node(4);
        Node mergedEmpty = mergeSorted(emptyA, nonEmptyB);
        printList(mergedEmpty);
    }
}