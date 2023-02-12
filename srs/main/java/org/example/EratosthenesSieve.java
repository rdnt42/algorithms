package org.example;

public class EratosthenesSieve {

    // нахождение простых числе в последовательности
    public static void main(String[] args) {
        System.out.println(getPrimeCount(30));
    }

    public static int getPrimeCount(int n) {
        if(n < 2) return 0;
        // массив булевых значений, чтобы не хоанить в памяти числа
        boolean[] isCombineNums = new boolean[n - 1];
        int currNum = 1;

        // если квардратный корень числа больше n, значит все остальыне числа уже проверены
        while (Math.pow(currNum, 2) < n) {
            currNum++;
            // если текущее числа составное - пропускаем
            if(isCombineNums[currNum - 2]) continue;

            // currNum может быть простым, поэтому начинаем с currNum * 2 с шагом currNum
            // все эти числа уже является составными с делителем currNum
            for (int i = currNum * 2; i <= n; i += currNum) {
                isCombineNums[i - 2] = true;
            }
        }

        // подсчитываем все оставшиеся
        int resultCount = 0;
        for (boolean isCombine : isCombineNums) {
            if (!isCombine) {
                resultCount++;
            }
        }

        return resultCount;
    }
}
