public class ProductPriceFinder {
    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int low = 150;
        int high = 300;

        int lowerIndex = lowerBound(prices, low);
        int upperIndex = upperBound(prices, high);

        int count = upperIndex - lowerIndex + 1;

        System.out.println("Lower bound index " + lowerIndex);
        System.out.println("Upper bound index " + upperIndex);
        System.out.println("Count " + count + " (products with prices 150, 150, 200, 300)");
    }

    private static int lowerBound(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int idx = arr.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] >= target) {
                idx = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return idx;
    }

    private static int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        int idx = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target) {
                idx = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return idx;
    }
}