import java.util.HashMap;
import java.util.Map;

public class NetBalancePeriodCounter {
    public static int countPeriods(int[] transactions, long k) {
        if (transactions == null || transactions.length == 0) {
            return 0;
        }

        Map<Long, Integer> prefixSumMap = new HashMap<>();
        prefixSumMap.put(0L, 1);

        long runningSum = 0;
        int count = 0;

        for (int transaction : transactions) {
            runningSum += transaction;

            long targetPrefix = runningSum - k;
            if (prefixSumMap.containsKey(targetPrefix)) {
                count += prefixSumMap.get(targetPrefix);
            }

            prefixSumMap.put(runningSum, prefixSumMap.getOrDefault(runningSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] transactions1 = {3, 4, -7, 1, 3, 3, 1, -4};
        long k1 = 7;
        System.out.println(countPeriods(transactions1, k1));

        int[] transactions2 = {1, 2, 3};
        long k2 = 10;
        System.out.println(countPeriods(transactions2, k2));
    }
}