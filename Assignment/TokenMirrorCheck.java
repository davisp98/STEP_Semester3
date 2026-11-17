class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class TokenMirrorCheck {

    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) {
            return true;
        }

        Node slow = head;
        Node fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalfHead = reverse(slow.next);
        Node p1 = head;
        Node p2 = secondHalfHead;
        boolean result = true;

        while (p2 != null) {
            if (p1.data != p2.data) {
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        slow.next = reverse(secondHalfHead);

        return result;
    }

    private static Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
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
        Node tokens1 = new Node(4);
        tokens1.next = new Node(7);
        tokens1.next.next = new Node(9);
        tokens1.next.next.next = new Node(7);
        tokens1.next.next.next.next = new Node(4);

        System.out.println("Palindrome: " + isPalindrome(tokens1));
        System.out.print("List after check: ");
        printList(tokens1);

        System.out.println();

        Node tokens2 = new Node(4);
        tokens2.next = new Node(7);
        tokens2.next.next = new Node(7);
        tokens2.next.next.next = new Node(9);

        System.out.println("Palindrome: " + isPalindrome(tokens2));
        System.out.print("List after check: ");
        printList(tokens2);
    }
}