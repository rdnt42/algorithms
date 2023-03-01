package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
    public static void main(String[] args) {
        int[][] heights = new int[][]{{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        List<List<Integer>> lists = heightsBfs(heights);
        for (List<Integer> list : lists) {
            System.out.println("x: " + list.get(0) + ", y: " + list.get(1));
        }
    }


    // доступные направления
    static int[][] dirs = new int[][]{ {1,0}, {-1,0}, {0,1}, {0,-1}};

    // найти пересекающиеся максимумы для левой и верхней границы с нижней и правой
    public static List<List<Integer>> heightsBfs(int[][] heights) {
        List<List<Integer>> result = new LinkedList<>();
        int n = heights.length;
        int m = heights[0].length;

        boolean[][] pVisited = new boolean[n][m];
        boolean[][] aVisited = new boolean[n][m];

        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();

        // вертикальная граница
        for(int i = 0; i < n; i++) {
            // левый элемент
            pQueue.offer(new int[]{i, 0});
            // правый элемент
            aQueue.offer(new int[]{i, m - 1});

            pVisited[i][0] = true;
            aVisited[i][m - 1] = true;
        }

        // горизонтальная граница
        for(int i = 0; i < m; i++) {
            // верхний элемент
            pQueue.offer(new int[]{0, i});
            // нижний элемент
            aQueue.offer(new int[]{n - 1, i});

            pVisited[0][i] = true;
            aVisited[n - 1][i] = true;
        }

        bfs(heights, pQueue, pVisited);
        bfs(heights, aQueue, aVisited);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(pVisited[i][j] && aVisited[i][j]) {
                    List<Integer> point = new ArrayList<>(2);
                    point.add(i);
                    point.add(j);

                    result.add(point);
                }
            }
        }

        return result;
    }

    // обход в ширину
    // последними будут посещены самые высокие точки для текущего направления
    // т.е. до границы раздела
    private static void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited){
        int n = matrix.length;
        int m = matrix[0].length;

        while(!queue.isEmpty()) {
            int[] curr = queue.poll();
            for(int[] dir : dirs) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];

                // выход за границы, уже посещенная вершина или ниже текущего положения (текущая точка выше)
                if(x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || matrix[x][y] < matrix[curr[0]][curr[1]]){
                    continue;
                }

                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
}
