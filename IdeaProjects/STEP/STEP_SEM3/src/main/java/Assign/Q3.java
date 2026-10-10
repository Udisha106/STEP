package Assign;


public class Q3 {
    Node<String> current;

    Q3(String homepage) {
        current = new Node<>(homepage);
    }

    void visit(String url) {
        Node<String> newNode = new Node<>(url);

        current.next = null;
        newNode.prev = current;
        current.next = newNode;
        current = newNode;
    }

    String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.data;
    }

    String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.data;
    }

    public static void main(String[] args) {
        Q3 browser = new Q3("srm.edu");

        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        System.out.println(browser.back(1));
        System.out.println(browser.back(1));
        System.out.println(browser.forward(1));

        browser.visit("d.com");

        System.out.println(browser.forward(2));
        System.out.println(browser.back(2));
        System.out.println(browser.back(7));
    }
}
