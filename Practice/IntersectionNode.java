public class IntersectionNode {
    public static Node findIntersection(Node a, Node b) {
        if (a == null || b == null) {
            return null;
        }

        Node pA = a;
        Node pB = b;

        while (pA != pB) {
            pA = (pA == null) ? b : pA.next;
            pB = (pB == null) ? a : pB.next;
        }

        return pA;
    }
}