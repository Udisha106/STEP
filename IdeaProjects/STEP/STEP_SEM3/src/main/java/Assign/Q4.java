package Assign;


import java.util.*;

public class Q4 {
    interface CreditPolicy {
        int limit();
        String name();
    }

    static class Regular implements CreditPolicy {
        public int limit() { return 24; }
        public String name() { return "Regular"; }
    }

    static class Honors implements CreditPolicy {
        public int limit() { return 28; }
        public String name() { return "Honors"; }
    }

    static class Exchange implements CreditPolicy {
        public int limit() { return 20; }
        public String name() { return "Exchange"; }
    }

    static class Student {
        String name;
        CreditPolicy policy;
        int credits;

        Student(String name, CreditPolicy policy, int credits) {
            this.name = name;
            this.policy = policy;
            this.credits = credits;
        }
    }

    static class Elective {
        String name;
        int credits, capacity;
        Set<Student> enrolled = new LinkedHashSet<>();
        Queue<Student> waitlist = new ArrayDeque<>();

        Elective(String name, int credits, int capacity) {
            this.name = name;
            this.credits = credits;
            this.capacity = capacity;
        }

        void enroll(Student s) {
            if (enrolled.contains(s) || waitlist.contains(s)) {
                System.out.println("Enrollment failed: " + s.name
                        + " is already enrolled or waitlisted.");
                return;
            }

            if (s.credits + credits > s.policy.limit()) {
                System.out.println("Enrollment failed: " + s.name
                        + " would exceed the " + s.policy.name()
                        + " credit limit (" + (s.credits + credits)
                        + "/" + s.policy.limit() + ").");
                return;
            }

            if (enrolled.size() == capacity) {
                waitlist.add(s);
                System.out.println(name + " is full.");
                System.out.println(s.name + " added to waitlist (position "
                        + waitlist.size() + ").");
                return;
            }

            enrolled.add(s);
            s.credits += credits;
            printCredits(s, "enrolled in " + name);
        }

        void printCredits(Student s, String message) {
            System.out.println(s.name + " " + message + " (credits: "
                    + s.credits + "/" + s.policy.limit() + ").");
        }

        void drop(Student s) {
            if (!enrolled.remove(s)) {
                System.out.println("Drop failed: Student not enrolled.");
                return;
            }

            s.credits -= credits;
            printCredits(s, "dropped " + name);
            promote();
        }

        void promote() {
            while (enrolled.size() < capacity && !waitlist.isEmpty()) {
                Student s = waitlist.poll();

                if (s.credits + credits <= s.policy.limit()) {
                    enrolled.add(s);
                    s.credits += credits;
                    printCredits(s, "promoted from waitlist and enrolled in " + name);
                    return;
                }

                System.out.println(s.name
                        + " skipped: Credit limit exceeded during promotion.");
            }
        }
    }

    public static void main(String[] args) {
        Elective cloud = new Elective("Cloud Computing", 4, 2);

        Student asha = new Student("Asha", new Regular(), 20);
        Student ravi = new Student("Ravi", new Honors(), 22);
        Student neha = new Student("Neha", new Exchange(), 12);
        Student kiran = new Student("Kiran", new Regular(), 22);

        cloud.enroll(asha);
        cloud.enroll(ravi);
        cloud.enroll(neha);
        cloud.enroll(kiran);
        cloud.drop(asha);
    }
}
