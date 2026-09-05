class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        
        // Array to store the minimum value from index i to the end
        int[] rightMin = new int[n];
        rightMin[n - 1] = nums[n - 1];
        
        // Precompute the suffix minimums
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], nums[i]);
        }
        
        int leftMax = 0;
        
        // Traverse to find the first index where instability score <= k
        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, nums[i]);
            
            if (leftMax - rightMin[i] <= k) {
                return i; // Smallest stable index found
            }
        }
        
        return -1; // No stable index exists
    }
}