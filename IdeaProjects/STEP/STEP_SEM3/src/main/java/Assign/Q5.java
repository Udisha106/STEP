package Assign;

import java.util.*;

public class Q5 {
    interface PricingPlan {
        double price(double amount);
    }

    static class DayScholar implements PricingPlan {
        public double price(double amount) { return amount; }
    }

    static class Hosteller implements PricingPlan {
        public double price(double amount) { return amount * 0.90; }
    }

    static class Staff implements PricingPlan {
        public double price(double amount) { return amount * 0.80; }
    }

    static class Transaction {
        String item;
        double amount;
        boolean refunded = false;

        Transaction(String item, double amount) {
            this.item = item;
            this.amount = amount;
        }
    }

    static class SmartCard {
        private String id;
        private PricingPlan plan;
        private double balance = 0;
        private boolean blocked = false;
        private List<Transaction> transactions = new ArrayList<>();

        SmartCard(String id, PricingPlan plan) {
            this.id = id;
            this.plan = plan;
        }

        void topUp(double amount) {
            if (blocked) {
                System.out.println("Top-up rejected: Card is blocked.");
                return;
            }

            if (amount < 100 || balance + amount > 5000) {
                System.out.println("Top-up rejected: Amount must be at least "
                        + "₹100 and balance cannot exceed ₹5000.");
                return;
            }

            transactions.add(new Transaction("Top-up", amount));
            balance += amount;

            System.out.printf(Locale.US,
                    "%s topped up with ₹%.2f. Balance: ₹%.2f.%n",
                    id, amount, balance);
        }

        Transaction purchase(String item, double listedPrice) {
            if (blocked) {
                System.out.println("Purchase failed: Card is blocked.");
                return null;
            }

            double amount = plan.price(listedPrice);

            if (balance < amount) {
                System.out.printf(Locale.US,
                        "Purchase failed: Insufficient balance (required ₹%.2f, available ₹%.2f).%n",
                        amount, balance);
                return null;
            }

            Transaction t = new Transaction(item, -amount);
            transactions.add(t);
            balance -= amount;

            System.out.printf(Locale.US,
                    "%s purchased for ₹%.2f. Balance: ₹%.2f.%n",
                    item, amount, balance);

            return t;
        }

        void refund(Transaction t) {
            if (blocked) {
                System.out.println("Refund rejected: Card is blocked.");
                return;
            }

            if (t == null || !transactions.contains(t) || t.amount >= 0) {
                System.out.println("Refund rejected: Invalid purchase.");
                return;
            }

            if (t.refunded) {
                System.out.println("Refund rejected: " + t.item
                        + " has already been refunded.");
                return;
            }

            double amount = -t.amount;
            t.refunded = true;
            transactions.add(new Transaction("Refund: " + t.item, amount));
            balance += amount;

            System.out.printf(Locale.US,
                    "Refund of ₹%.2f for %s processed. Balance: ₹%.2f.%n",
                    amount, t.item, balance);
        }

        void block() {
            blocked = true;
            System.out.println(id + " blocked.");
        }

        void unblock() {
            blocked = false;
            System.out.println(id + " unblocked.");
        }

        void miniStatement() {
            System.out.print("Mini-statement for " + id + ": ");
            double sum = 0;

            for (int i = 0; i < transactions.size(); i++) {
                double amount = transactions.get(i).amount;
                sum += amount;

                System.out.printf(Locale.US, "%s%.2f",
                        amount >= 0 ? "+" : "", amount);

                if (i < transactions.size() - 1) System.out.print(", ");
            }

            System.out.printf(Locale.US, " = ₹%.2f.%n", sum);
        }
    }

    public static void main(String[] args) {
        SmartCard card = new SmartCard("C-2045", new Hosteller());

        card.topUp(500);
        Transaction thali = card.purchase("Veg Thali", 120);
        card.purchase("Cold Coffee", 60);
        card.purchase("Food items", 400);
        card.refund(thali);
        card.refund(thali);
        card.miniStatement();
    }
}
