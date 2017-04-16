package by.bsu.tsp.algs;
import by.bsu.tsp.util.*;
import java.util.*;

/**
 * Created by Mikhail on 15.04.2017.
 */
public class Full {
    private static final int INFINITY = 1_000_000_000;
    private static List<List<Integer>> combinations = new ArrayList<>();

    public void run(){
        System.out.println("Enter the number of vertexes: ");
        int n;
        try(Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
        }

        int [][] arr = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i==j){
                    arr[i][j] = INFINITY;
                }else {
                    arr[i][j] = Math.abs(Util.random.nextInt(100));
                }
            }
        }
        printMatrix(arr);
        int[] sequence = new int[n];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = i;
        }
        generateAllCombinations(sequence, 0);
        calculate(arr);
    }

    private static void generateAllCombinations(int[] a, int k) {
        if (k == a.length) {
            List<Integer> vertexes = new LinkedList<>();
            vertexes.add(0);
            for (int anA : a) {
                if (anA != 0) {
                    vertexes.add(anA);
                }
            }
            vertexes.add(0);
            combinations.add(vertexes);
        }
        else {
            for (int i = k; i < a.length; i++) {
                int temp = a[k];
                a[k] = a[i];
                a[i] = temp;

                generateAllCombinations(a, k + 1);

                temp = a[k];
                a[k] = a[i];
                a[i] = temp;
            }
        }
    }

    private static void calculate(int [][] arr){
        int min = INFINITY;
        for (List<Integer> combination : combinations) {
            int cost = 0;
            for (int j = 0; j < combination.size() - 1; j++) {
                cost = cost + arr
                        [(combination.get(j))]
                        [(combination.get(j + 1))];

            }
            if (cost < min) {
                min = cost;
            }
        }
        System.out.println(min);
    }
    private static void printMatrix(int [][] arr) {
        int n = arr.length;
        for (int[] anArr : arr) {
            for (int j = 0; j < n; j++) {
                System.out.print(" [" + anArr[j] + "] ");
            }
            System.out.println();
        }
    }
}
