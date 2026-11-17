class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class ExamScriptBatches {

    public static Node reverseInGroups(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }

        Node dummy = new Node(0);
        dummy.next = head;
        Node groupPrev = dummy;

        while (true) {
            Node cursor = groupPrev;
            for (int i = 0; i < k; i++) {
                cursor = cursor.next;
                if (cursor == null) {
                    return dummy.next;
                }
            }

            Node prev = null;
            Node curr = groupPrev.next;
            Node next = null;
            Node tail = curr;

            for (int i = 0; i < k; i++) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            Node connection = groupPrev.next;
            groupPrev.next = prev;
            tail.next = curr;
            groupPrev = tail;
        }
    }

    public static void printList(Node head) {
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.data + (curr.next != null ? " -> " : " -> null"));
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node scripts1 = new Node(1);
        scripts1.next = new Node(2);
        scripts1.next.next = new Node(3);
        scripts1.next.next.next = new Node(4);
        scripts1.next.next.next.next = new Node(5);
        scripts1.next.next.next.next.next = new Node(6);
        scripts1.next.next.next.next.next.next = new Node(7);
        scripts1.next.next.next.next.next.next.next = new Node(8);

        Node res1 = reverseInGroups(scripts1, 3);
        printList(res1);

        Node scripts2 = new Node(11);
        scripts2.next = new Node(12);

        Node res2 = reverseInGroups(scripts2, 3);
        printList(res2);
    }
}