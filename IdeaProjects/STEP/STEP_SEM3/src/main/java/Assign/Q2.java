package Assign;


public class Q2 {
    static Node<Integer> mergeSorted(Node<Integer> a, Node<Integer> b) {
        Node<Integer> dummy = new Node<>(0);
        Node<Integer> tail = dummy;

        while (a != null && b != null) {
            if (a.data <= b.data) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }

        if (a != null)
            tail.next = a;
        else
            tail.next = b;

        return dummy.next;
    }

    static void printList(Node<Integer> head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null)
                System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node<Integer> a = new Node<>(3);
        a.next = new Node<>(8);
        a.next.next = new Node<>(15);

        Node<Integer> b = new Node<>(5);
        b.next = new Node<>(8);
        b.next.next = new Node<>(12);
        b.next.next.next = new Node<>(20);

        Node<Integer> result = mergeSorted(a, b);
        printList(result);
    }
}
