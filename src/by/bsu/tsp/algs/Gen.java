package by.bsu.tsp.algs;
import by.bsu.tsp.algs.domain.Resolve;
import by.bsu.tsp.util.*;
import java.util.*;

/**
 * Created by Mikhail on 16.04.2017.
 */
public class Gen {
    List<Resolve> population = new LinkedList<>();
    private static final Random rand = new Random();
    private static int[][] matrix;
    private static final int PIVOT = 3;
    private static final double MUTATION_RATE = 0.2;
    private static final int P_SIZE = 20;
    private static final int T_SIZE = 6;
    private static final int MAX_ITERATIONS = 100;
    private static final int MIN_VERTEX = 2;

    public Gen() {}

    public Gen(int[][] graph) {
        matrix = graph;
        for (int i = 0; i < P_SIZE; i++) {
            Resolve resolve= new Resolve();
            resolve.generateRandomPopulation(matrix.length);
            int fitness = Util.getFitness(resolve.getVertexes(), matrix);
            resolve.setLength(fitness);
            population.add(resolve);
        }
        Collections.sort(population, new ResolveComparator());
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
            Gen gen = new Gen(arr);
            gen.doAlgorithm();
        }

    public void evolve() {
            print();
            Resolve parent1 = new Resolve();
            Resolve parent2 = parent1;
            while (parent1 == parent2){
                parent1 = tournamentSelection(population);
                parent2 = tournamentSelection(population);
            }
            Resolve child1, child2;
            child1 = crossover(parent1, parent2);
            child2 = crossover(parent2, parent1);
            mutate(child1);
            mutate(child2);

            if (isChildLengthLessThenParentLength(child1, parent1) && isChildLengthLessThenParentLength(child2, parent2) ){
                population.set(P_SIZE - 1, child1);
                population.set(P_SIZE - 2, child2);
            } else if (isChildLengthLessThenParentLength(child1, parent1)) {
                population.set(P_SIZE - 1, child1);
            } else if (isChildLengthLessThenParentLength(child2, parent2)){
                population.set(P_SIZE - 1, child2);
            }
            Collections.sort(population, new ResolveComparator());
            print();
        }

    private Resolve tournamentSelection(List<Resolve> population) {
        List<Resolve> tournament = new ArrayList<>();
        for (int i = 0; i < T_SIZE; i++) {
            int randomIndex = rand.nextInt(P_SIZE);
            tournament.add(i, population.get(randomIndex));
        }
        return Resolve.getResolveWithMinimumFitness(population);
    }



    private Resolve crossover(Resolve parent1, Resolve parent2) {
            Resolve child = new Resolve();
            List<Integer> newWay = new ArrayList<>();
            for (int i = 0; i< PIVOT; i++){
                int parentValue = parent1.getVertexes().get(i);
                newWay.add(parentValue);
            }
            for (int j = PIVOT; j< parent1.getVertexes().size() -1; j++){
                int p1val = parent2.getVertexes().get(j);
                int p2val = parent1.getVertexes().get(j);
                if (!newWay.contains(p1val)){
                    newWay.add(p1val);
                } else if (!newWay.contains(p2val) && newWay.contains(p1val)){
                    newWay.add(p2val);
                } else {
                    int vertex = getRandomIndex();
                    while (newWay.contains(vertex)){
                        vertex = getRandomIndex();
                    }
                    newWay.add(vertex);
                }
            }
            newWay.add(1);
            child.setVertexes(newWay);
            Util.getFitness(child.getVertexes(), matrix);
            return child;
        }

    private void mutate(Resolve resolve) {
            if (Math.random() <= MUTATION_RATE) {
                int index1 = getRandomIndex();
                int index2 = getRandomIndex();
                int vertex1 = resolve.getVertexes().get(index1);
                int vertex2 = resolve.getVertexes().get(index2);
                resolve.setVertex(index1, vertex2);
                resolve.setVertex(index2, vertex1);
            }
        }

    private boolean isChildLengthLessThenParentLength(Resolve child, Resolve parent1){
            return child.getLength() <= parent1.getLength();
        }

    public void doAlgorithm() {
            int i = 0;
            while (i != MAX_ITERATIONS) {
                i++;
                evolve();
                System.out.println("Generation №" + i);
            }
            print();
        }

    private void print() {
        System.out.println(population.toString());
    }

    public static int getRandomIndex(){
        return rand.nextInt(matrix.length - MIN_VERTEX + 1) + MIN_VERTEX;
    }
}


