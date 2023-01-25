package org.example;

import java.util.Random;

public class Main {

    // https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        for (int i = 0; i < 5; i++) {
            shuffle(arr);
            for (int e : arr) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }
    private static final Random random = new Random();
    private static void shuffle(int[] arr) {
        // move the "struck" numbers to the end of the list
        // by swapping them with the last unstruck number at each iteration
        for (int i = arr.length - 1; i >= 0; i--) {
            int nextInt = random.nextInt(i + 1);

            int temp = arr[i];
            arr[i] = arr[nextInt];
            arr[nextInt] = temp;
        }
    }
}