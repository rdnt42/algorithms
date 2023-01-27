package org.example;

public class Main {
    public static void main(String[] args) {
        String[] arr = new String[]{"aa", "ab", "ba"};
        int result = longestPalindrome(arr);
        System.out.println("result: " + result);
    }

    private static int longestPalindrome(String[] words) {
        int[][] counter = new int[26][26];

        int result = 0;
        for(String word : words) {
            int f = word.charAt(0) - 'a';
            int s = word.charAt(1) - 'a';

            // если для текущего слова есть палиндром, увеличивае результат (2 слова по 2 буквы)
            // и уменьшаем счетчик
            if(counter[f][s] > 0) {
                result += 4;
                counter[f][s]--;
            } else {
                // если для текущего слова нет соответсвующего палиндрома, увеличиваем счетчик необходимых
                counter[s][f]++;
            }
        }

        // если есть хотя бы одна одинаковая пара 'aa', 'bb' и т.д., то учитываем ее в середине палиндрома
        for (int i = 0; i < 26; i++) {
            if (counter[i][i] > 0) {
                result += 2;
                break;
            }
        }

        return result;
    }
}