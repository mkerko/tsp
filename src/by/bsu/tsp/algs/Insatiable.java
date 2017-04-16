package by.bsu.tsp.algs;
import by.bsu.tsp.util.*;
import java.util.*;

/**
 * Created by Mikhail on 15.04.2017.
 */
public class Insatiable {
    private List<Integer> way = new ArrayList<>();
    private int wayLength;

    public void run() {
        System.out.println("Enter the number of vertexes: ");
        int n;
        try(Scanner sc = new Scanner(System.in)) {
            n = sc.nextInt();
        }

        int [][] arr = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i==j){
                    arr[i][j] = Util.INFINITY;
                }else {
                    arr[i][j] = Math.abs(Util.random.nextInt(100));
                }
            }
        }
        System.out.println(doAlgorithm(arr) + ", " + wayLength);
    }

    public List<Integer> doAlgorithm(int[][] matrix){
        int size = matrix.length;
        int minPoint = size;
        int minValue = Integer.MAX_VALUE;
        way.add(1);
        int previous = 1;

        while (way.size()!=size){
            for (int j=0 ; j< size; j++){
                if (j + 1!=previous && matrix[previous-1][j]!=-1 && !way.contains(j+1)){
                    if (matrix[previous-1][j] <= minValue){
                        minValue = matrix[previous-1][j];
                        minPoint = j + 1;
                    }
                }
                if (j == size - 1){
                    wayLength += minValue;
                    way.add(minPoint);
                    previous = minPoint;
                    minValue = Integer.MAX_VALUE;
                }
            }
        }

        if (way.size() == size){
            way.add(1);
            wayLength += matrix[previous-1][0];
        }
        return way;
    }
}
