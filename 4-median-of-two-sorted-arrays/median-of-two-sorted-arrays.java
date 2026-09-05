class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array to optimize binary search and avoid out-of-bounds
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int low = 0;
        int high = m;

        while (low <= high) {
            // Partition both arrays
            int partitionX = (low + high) / 2;
            int partitionY = (m + n + 1) / 2 - partitionX;

            // Handle edge cases where partition is at the start or end of the arrays
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == m) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == n) ? Integer.MAX_VALUE : nums2[partitionY];

            // Check if we have found the correct partition
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                
                // If the total length is even
                if ((m + n) % 2 == 0) {
                    return ((double) Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } 
                // If the total length is odd
                else {
                    return (double) Math.max(maxLeftX, maxLeftY);
                }
                
            } 
            // We are too far on the right side of nums1, move left
            else if (maxLeftX > minRightY) {
                high = partitionX - 1;
            } 
            // We are too far on the left side of nums1, move right
            else {
                low = partitionX + 1;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted or are invalid.");
    }
}