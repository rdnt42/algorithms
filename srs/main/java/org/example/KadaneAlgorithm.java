package org.example;

public class KadaneAlgorithm {
    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(gitMaxSumForSubArray(arr));
    }


    // https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
    // Largest Sum Contiguous Subarray
    private static int gitMaxSumForSubArray(int[] nums) {
        int maxSum = nums[0];

        // current sequence sum
        int currentSum = 0;
        for(int num : nums) {
            currentSum += num;

            // check current max and total max
            maxSum = Math.max(currentSum, maxSum);

            // we do not interest values below zero, because 0 - it's minimum value for empty seq []
            if(currentSum < 0) {
                currentSum = 0;
            }
        }

        return maxSum;
    }
}