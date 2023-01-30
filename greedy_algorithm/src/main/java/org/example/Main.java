package org.example;

public class Main {
    public static void main(String[] args) {
        String[] arr = new String[]{"aa", "ab", "ba"};
        int result = longestPalindrome(arr);
        System.out.println("result: " + result);


        char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B', 'C', 'C', 'C'};
        System.out.println("task scheduler result: " + taskScheduler(tasks, 2));
    }

    private static int longestPalindrome(String[] words) {
        int[][] counter = new int[26][26];

        int result = 0;
        for (String word : words) {
            int f = word.charAt(0) - 'a';
            int s = word.charAt(1) - 'a';

            // если для текущего слова есть палиндром, увеличивае результат (2 слова по 2 буквы)
            // и уменьшаем счетчик
            if (counter[f][s] > 0) {
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


    /**
     * 1 задача выполняется 1 единицу времени
     *
     * @param tasks список задач. Один символ A-Z
     * @param n     интервал простоя между одинаковыми задачами
     * @return общее время выполенения всех задач
     */
    public static int taskScheduler(char[] tasks, int n) {
        int[] counter = new int[26];

        // максимальное количество задач одного типа
        int maxCount = 0;

        // количество задач с максимальным значением
        int tasksWithMaxCount = 0;
        for (char task : tasks) {
            int idx = task - 'A';
            counter[idx]++;

            if (maxCount == counter[idx]) {
                tasksWithMaxCount++;
            } else if (maxCount < counter[idx]) {
                maxCount = counter[idx];
                tasksWithMaxCount = 1;
            }
        }

        // количество промежутков между максимально повторяемой задачей
        int partCount = maxCount - 1;
        int partLength = n - (tasksWithMaxCount - 1);
        int emptySlots = partCount * partLength;
        int availableTasks = tasks.length - maxCount * tasksWithMaxCount;

        // если пустых слотов между задачами больше, чем дотсупно задач,
        // то эти слоты нужно учитывать в конечном ответе
        int idles = Math.max(0, emptySlots - availableTasks);

        return tasks.length + idles;
    }
}