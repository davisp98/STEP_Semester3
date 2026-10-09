public class ExamScoreBandCounter {
    public static int countInBand(int[] scores, int low, int high) {
        if (scores == null || scores.length == 0 || low > high) {
            return 0;
        }

        int lowerIndex = findFirstGreaterOrEqual(scores, low);
        int upperIndex = findFirstGreater(scores, high);

        return upperIndex - lowerIndex;
    }

    private static int findFirstGreaterOrEqual(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int findFirstGreater(int[] scores, int target) {
        int left = 0;
        int right = scores.length;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] scores1 = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores1, 42, 58));

        int[] scores2 = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};
        System.out.println(countInBand(scores2, 90, 100));
    }
}