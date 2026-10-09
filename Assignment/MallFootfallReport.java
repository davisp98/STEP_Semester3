import java.util.Arrays;

public class MallFootfallReport {
    public static long[] footfallReport(int[] visitors, int[][] queries) {
        if (visitors == null || visitors.length == 0 || queries == null) {
            return new long[0];
        }

        int n = visitors.length;
        long[] prefix = new long[n];
        prefix[0] = visitors[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + visitors[i];
        }

        long[] result = new long[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int start = queries[q][0];
            int end = queries[q][1];

            if (start == 0) {
                result[q] = prefix[end];
            } else {
                result[q] = prefix[end] - prefix[start - 1];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] visitors = {12, 7, 3, 9, 15, 4, 8};
        int[][] queries = {{0, 2}, {2, 5}, {4, 6}, {3, 3}};

        long[] report = footfallReport(visitors, queries);
        System.out.println(Arrays.toString(report));
    }
}