package Assign;


public class Q4 {
    static Node<Integer> reverse(Node<Integer> head) {
        Node<Integer> prev = null;
        Node<Integer> current = head;

        while (current != null) {
            Node<Integer> nextNode = current.next;
            current.next = prev;
            prev = current;
            current = nextNode;
        }

        return prev;
    }

    static boolean isPalindrome(Node<Integer> head) {
        if (head == null || head.next == null)
            return true;

        Node<Integer> slow = head;
        Node<Integer> fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node<Integer> secondHalf = reverse(slow.next);
        Node<Integer> first = head;
        Node<Integer> second = secondHalf;
        boolean result = true;

        while (second != null) {
            if (!first.data.equals(second.data)) {
                result = false;
                break;
            }
            first = first.next;
            second = second.next;
        }

        slow.next = reverse(secondHalf);
        return result;
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
        Node<Integer> head = new Node<>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(2);
        head.next.next.next = new Node<>(1);

        System.out.println(isPalindrome(head));
        printList(head);
    }
}
