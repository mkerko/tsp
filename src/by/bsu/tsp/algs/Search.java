package by.bsu.tsp.algs;
import by.bsu.tsp.algs.domain.Resolve;
import by.bsu.tsp.util.*;
import java.util.*;

/**
 * Created by Mikhail on 16.04.2017.
 */
public class Search {
    int matrix[][];

    public Search() {
    }

    public Search(int[][] graph) {
        matrix = graph;
    }

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
        Search search = new Search(arr);
        System.out.println(search.doAlgorithm());
    }
    public Resolve doAlgorithm(){
        Resolve resolve = new Resolve();
        resolve.generateRandomPopulation(matrix.length);
        int currentLength = Util.getFitness(resolve.getVertexes(), matrix);

        List<Integer> vertexes = resolve.getVertexes();
        int maxLength = resolve.getVertexes().size();
        for (int i = 1 ; i < maxLength; i++) {
            Set<List<Integer>> combinations = new LinkedHashSet<>();
            for (int j = i + 1; j < maxLength - 1; j++) {
                int vertex1 = vertexes.get(i);
                int vertex2 = vertexes.get(j);
                vertexes.set(i, vertex2);
                vertexes.set(j, vertex1);
                combinations.add(vertexes);
            }
            int unsuitableAmount = 0;
            for (List<Integer> combination: combinations){
                int newLength = Util.getFitness(combination, matrix);
                if (newLength < currentLength) {
                    currentLength = newLength;
                    vertexes = combination;
                } else {
                    unsuitableAmount++;
                }
            }
            if (unsuitableAmount==combinations.size()) break;
        }
        return new Resolve(Util.getFitness(vertexes, matrix), vertexes);
    }
}
