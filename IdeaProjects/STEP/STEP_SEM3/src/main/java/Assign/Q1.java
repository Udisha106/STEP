package Assign;


public class Q1 {
    static Node<Integer> removeKthFromEnd(Node<Integer> head, int k) {
        Node<Integer> dummy = new Node<>(0);
        dummy.next = head;

        Node<Integer> lead = dummy;
        Node<Integer> trail = dummy;

        for (int i = 0; i <= k; i++) {
            lead = lead.next;
        }

        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        trail.next = trail.next.next;

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
        Node<Integer> head = new Node<>(10);
        head.next = new Node<>(20);
        head.next.next = new Node<>(30);
        head.next.next.next = new Node<>(40);
        head.next.next.next.next = new Node<>(50);

        head = removeKthFromEnd(head, 2);
        printList(head);
    }
}
