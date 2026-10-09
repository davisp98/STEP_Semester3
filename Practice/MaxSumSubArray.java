public class MaxSumSubArray {
    public static int maxSumSubarray(int[] sales, int k) {
        if (sales == null || sales.length < k || k <= 0) {
            return 0;
        }

        int currentWindowSum = 0;
        for (int i = 0; i < k; i++) {
            currentWindowSum += sales[i];
        }

        int maxSum = currentWindowSum;

        for (int i = k; i < sales.length; i++) {
            currentWindowSum += sales[i] - sales[i - k];
            if (currentWindowSum > maxSum) {
                maxSum = currentWindowSum;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] sales = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println(maxSumSubarray(sales, k));
    }
}