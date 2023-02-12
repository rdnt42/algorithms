package org.example;

public class SlidingWindow {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("Hello world!"));
    }

    // https://itnext.io/sliding-window-algorithm-technique-6001d5fbe8b3#:~:text=The%20Sliding%20window%20is%20a,complexity%20to%20O(n).
    public static int lengthOfLongestSubstring(String s) {
        int maxCount = 0;
        // start for window
        int start = 0;
        // next index for current character
        int[] nextIndex = new int[128];

        for(int end = 0; end < s.length(); end++) {
            char val = s.charAt(end);
            // if character repeats, get next index
            start = Math.max(start, nextIndex[val]);

            int currCount = end - start + 1;
            maxCount = Math.max(currCount, maxCount);

            // update where we met current character
            nextIndex[val] = end + 1;
        }

        return maxCount;
    }
}