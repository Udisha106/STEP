package Assign;


class Term {
    int coefficient;
    int exponent;

    Term(int coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }
}

public class Q5 {
    static Node<Term> addPolynomials(Node<Term> p, Node<Term> q) {
        Node<Term> dummy = new Node<>(new Term(0, 0));
        Node<Term> tail = dummy;

        while (p != null && q != null) {
            if (p.data.exponent > q.data.exponent) {
                tail.next = new Node<>(new Term(
                        p.data.coefficient, p.data.exponent));
                p = p.next;
                tail = tail.next;
            } else if (q.data.exponent > p.data.exponent) {
                tail.next = new Node<>(new Term(
                        q.data.coefficient, q.data.exponent));
                q = q.next;
                tail = tail.next;
            } else {
                int sum = p.data.coefficient + q.data.coefficient;

                if (sum != 0) {
                    tail.next = new Node<>(new Term(
                            sum, p.data.exponent));
                    tail = tail.next;
                }

                p = p.next;
                q = q.next;
            }
        }

        while (p != null) {
            tail.next = new Node<>(new Term(
                    p.data.coefficient, p.data.exponent));
            tail = tail.next;
            p = p.next;
        }

        while (q != null) {
            tail.next = new Node<>(new Term(
                    q.data.coefficient, q.data.exponent));
            tail = tail.next;
            q = q.next;
        }

        return dummy.next;
    }

    static void printPolynomial(Node<Term> head) {
        if (head == null) {
            System.out.println("0");
            return;
        }

        boolean first = true;

        while (head != null) {
            int c = head.data.coefficient;
            int e = head.data.exponent;

            if (!first)
                System.out.print(c > 0 ? " + " : " - ");
            else if (c < 0)
                System.out.print("-");

            int absC = Math.abs(c);

            if (e == 0 || absC != 1)
                System.out.print(absC);

            if (e > 0) {
                System.out.print("x");
                if (e > 1)
                    System.out.print("^" + e);
            }

            first = false;
            head = head.next;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Node<Term> p = new Node<>(new Term(5, 3));
        p.next = new Node<>(new Term(4, 2));
        p.next.next = new Node<>(new Term(2, 0));

        Node<Term> q = new Node<>(new Term(5, 2));
        q.next = new Node<>(new Term(-3, 1));
        q.next.next = new Node<>(new Term(1, 0));

        Node<Term> result = addPolynomials(p, q);
        printPolynomial(result);
    }
}
