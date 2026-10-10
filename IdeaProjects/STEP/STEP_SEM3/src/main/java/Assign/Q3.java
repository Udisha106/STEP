package Assign;


import java.util.HashMap;
import java.util.Map;

public class Q3 {
    static int countPeriods(int[] transactions, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();

        frequency.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int value : transactions) {
            prefixSum += value;

            count += frequency.getOrDefault(prefixSum - k, 0);

            frequency.put(
                    prefixSum,
                    frequency.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions = {3, 4, -7, 1, 3, 3, 1, -4};

        System.out.println(countPeriods(transactions, 7));

        System.out.println(
                countPeriods(new int[]{1, 2, 3}, 10)
        );
    }
}
