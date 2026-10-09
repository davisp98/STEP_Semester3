public class PairSum {
    public static String pairSumSorted(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return "Not Found";
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left < right) {
            int currentSum = nums[left] + nums[right];
            if (currentSum == target) {
                return "(" + nums[left] + ", " + nums[right] + ")";
            } else if (currentSum < target) {
                left++;
            } else {
                right--;
            }
        }
        
        return "Not Found";
    }

    public static void main(String[] args) {
        int[] nums1 = {-4, -1, 0, 3, 5, 9};
        System.out.println(pairSumSorted(nums1, 4));

        int[] nums2 = {1, 2, 3};
        System.out.println(pairSumSorted(nums2, 180));
    }
}