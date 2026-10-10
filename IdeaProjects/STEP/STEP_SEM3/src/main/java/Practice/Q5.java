package Practice;

public class Q5 {
    static int maxSumSubarray(int[] sales, int k) {
        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += sales[i];
        }

        int maxSum = sum;

        for (int i = k; i < sales.length; i++) {
            sum = sum - sales[i - k] + sales[i];

            if (sum > maxSum) {
                maxSum = sum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] sales = {2, 1, 5, 1, 3, 2};

        System.out.println(maxSumSubarray(sales, 3));
    }
}
