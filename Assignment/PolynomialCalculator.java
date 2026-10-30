class TermNode {
    int coefficient;
    int exponent;
    TermNode next;

    public TermNode(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }
}

public class PolynomialCalculator {

    public static TermNode addPolynomials(TermNode p, TermNode q) {
        TermNode dummy = new TermNode(0, 0);
        TermNode tail = dummy;

        TermNode currP = p;
        TermNode currQ = q;

        while (currP != null && currQ != null) {
            if (currP.exponent > currQ.exponent) {
                tail.next = new TermNode(currP.coefficient, currP.exponent);
                tail = tail.next;
                currP = currP.next;
            } else if (currP.exponent < currQ.exponent) {
                tail.next = new TermNode(currQ.coefficient, currQ.exponent);
                tail = tail.next;
                currQ = currQ.next;
            } else {
                int sumCoeff = currP.coefficient + currQ.coefficient;
                if (sumCoeff != 0) {
                    tail.next = new TermNode(sumCoeff, currP.exponent);
                    tail = tail.next;
                }
                currP = currP.next;
                currQ = currQ.next;
            }
        }

        while (currP != null) {
            tail.next = new TermNode(currP.coefficient, currP.exponent);
            tail = tail.next;
            currP = currP.next;
        }

        while (currQ != null) {
            tail.next = new TermNode(currQ.coefficient, currQ.exponent);
            tail = tail.next;
            currQ = currQ.next;
        }

        return dummy.next;
    }

    public static void printPolynomial(TermNode head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        TermNode curr = head;
        boolean isFirst = true;

        while (curr != null) {
            if (curr.coefficient == 0) {
                curr = curr.next;
                continue;
            }

            if (!isFirst) {
                if (curr.coefficient > 0) {
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                }
            } else {
                if (curr.coefficient < 0) {
                    sb.append("-");
                }
            }

            int absCoeff = Math.abs(curr.coefficient);
            if (curr.exponent == 0) {
                sb.append(absCoeff);
            } else if (curr.exponent == 1) {
                if (absCoeff != 1) {
                    sb.append(absCoeff);
                }
                sb.append("x");
            } else {
                if (absCoeff != 1) {
                    sb.append(absCoeff);
                }
                sb.append("x^").append(curr.exponent);
            }

            isFirst = false;
            curr = curr.next;
        }

        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        TermNode p1 = new TermNode(5, 3);
        p1.next = new TermNode(4, 2);
        p1.next.next = new TermNode(2, 0);

        TermNode q1 = new TermNode(5, 2);
        q1.next = new TermNode(-3, 1);
        q1.next.next = new TermNode(1, 0);

        TermNode res1 = addPolynomials(p1, q1);
        printPolynomial(res1);

        TermNode p2 = new TermNode(3, 2);
        p2.next = new TermNode(1, 0);

        TermNode q2 = new TermNode(-3, 2);
        q2.next = new TermNode(4, 1);

        TermNode res2 = addPolynomials(p2, q2);
        printPolynomial(res2);
    }
}