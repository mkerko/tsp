package by.bsu.tsp.algs;
import by.bsu.tsp.algs.domain.Resolve;
import by.bsu.tsp.util.*;
import java.util.*;

/**
 * Created by Mikhail on 16.04.2017.
 */
public class Burn {
    private static int[][] matrix;
    private static final double MAX_TEMPERATURE = 250;
    private static final double MIN_TEMPERATURE = 1;
    private static final int INDEX_TO_SWAP_1 = 1;
    private static final int INDEX_TO_SWAP_2 = 3;

    public Burn() {
    }

    public Burn(int[][] arr) {
        matrix = arr;
    }

    public Resolve run(){
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
        Burn burn = new Burn(arr);
        Resolve resultResolve = burn.run();
        System.out.println(resultResolve + "  " + Util.getFitness(resultResolve.getVertexes(), matrix));
        Resolve resolve1 = new Resolve();
        resolve1.generateRandomPopulation(matrix.length);
        int length = Util.getFitness(resolve1.getVertexes(), matrix);
        resolve1.setLength(length);
        Resolve resolve;
        double t = MAX_TEMPERATURE;
        while (t > MIN_TEMPERATURE){
            resolve = changeResolveState(resolve1);
            int length1 = resolve.getLength();
            int length2 = resolve1.getLength();
            int delta = length1- length2;
            if (delta <= 0){
                resolve1 = resolve;
            } else if (delta > 0){
                double p = Math.pow(Math.E, -(delta/t));
                if (Math.random()<p){
                    resolve1 = resolve;
                }
            }
            t = 3*t/4+4;
        }
        return resolve1;
    }

    public Resolve changeResolveState(Resolve resolve2){
        Resolve resolve = new Resolve();
        resolve.setVertexes(resolve2.getVertexes());
        int vertex1 = resolve.getVertexes().get(INDEX_TO_SWAP_1);
        resolve.setVertex(INDEX_TO_SWAP_1, resolve.getVertexes().get(INDEX_TO_SWAP_2));
        resolve.setVertex(INDEX_TO_SWAP_2, vertex1);
        Util.getFitness(resolve.getVertexes(), matrix);
        return resolve;
    }
}
