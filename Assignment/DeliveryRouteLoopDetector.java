class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class DeliveryRouteLoopDetector {

    public static String detectLoop(Node head) {
        if (head == null || head.next == null) {
            return "Loop: no";
        }

        Node slow = head;
        Node fast = head;
        boolean hasLoop = false;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasLoop = true;
                break;
            }
        }

        if (!hasLoop) {
            return "Loop: no";
        }

        int loopLength = 1;
        Node curr = slow.next;
        while (curr != slow) {
            loopLength++;
            curr = curr.next;
        }

        Node p1 = head;
        Node p2 = slow;
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return "Loop: yes, starts at stop " + p1.data + ", length " + loopLength;
    }

    public static void main(String[] args) {
        Node head1 = new Node(10);
        head1.next = new Node(20);
        head1.next.next = new Node(30);
        head1.next.next.next = new Node(40);
        head1.next.next.next.next = new Node(50);
        head1.next.next.next.next.next = head1.next.next; // points to 30
        System.out.println(detectLoop(head1));

        Node head2 = new Node(10);
        head2.next = new Node(20);
        head2.next.next = new Node(30);
        System.out.println(detectLoop(head2));

        Node head3 = new Node(7);
        head3.next = head3; // points to self
        System.out.println(detectLoop(head3));
    }
}