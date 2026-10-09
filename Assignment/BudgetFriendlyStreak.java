import java.util.Arrays;

public class BudgetFriendlyStreak {
    public static int[] longestStreak(int[] costs, long budget) {
        if (costs == null || costs.length == 0) {
            return new int[]{0, -1};
        }

        int maxLength = 0;
        int bestStartIndex = -1;
        long currentSum = 0;
        int left = 0;

        for (int right = 0; right < costs.length; right++) {
            currentSum += costs[right];

            while (currentSum > budget && left <= right) {
                currentSum -= costs[left];
                left++;
            }

            int currentLength = right - left + 1;
            if (currentSum <= budget && currentLength > maxLength) {
                maxLength = currentLength;
                bestStartIndex = left;
            }
        }

        return new int[]{maxLength, bestStartIndex};
    }

    public static void main(String[] args) {
        int[] costs1 = {4, 2, 1, 7, 3, 1, 2, 1, 5};
        long budget1 = 8;
        System.out.println(Arrays.toString(longestStreak(costs1, budget1)));

        int[] costs2 = {9, 10};
        long budget2 = 8;
        System.out.println(Arrays.toString(longestStreak(costs2, budget2)));
    }
}