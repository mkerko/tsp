package by.bsu.tsp.util;

import by.bsu.tsp.genetic.domain.ResolveGenetic;

import java.util.List;
import java.util.Random;

/**
 * Created by Mikhail on 16.04.2017.
 */
public class Util {
    public static Random random = new Random();
    public static final int INFINITY = -1;

    public static int calculateFitness(ResolveGenetic resolveGenetic) {
        int[] vars = resolveGenetic.getVars();
        int fitness = (int) (2*Math.pow(vars[0], 2) -
                3*Math.pow(vars[1], 3) + 4*Math.pow(vars[2], 2) - 30);
        return Math.abs(fitness);
    }
    public static int getFitness(List<Integer> vertexes, int[][] matrix) {
        int fitness = 0;
        int prev = 1;
        for (int i= 1 ; i< vertexes.size(); i++){
            int j = vertexes.get(i);
            fitness += matrix[prev-1][j-1];
            prev = j;
        }
        return fitness;
    }
    public static int[] generateVariables(int min, int max) {
        int vars[] = new int[3];
        vars[0] = random.nextInt(max - min + 1) + min;
        vars[1] = random.nextInt(max - min + 1) + min;
        vars[2] = random.nextInt(max - min + 1) + min;
        return vars;
    }

    public static ResolveGenetic getResolveWithBestFitness(List<ResolveGenetic> population) {
        ResolveGenetic bestFitnessResolve = population.get(0);
        for (ResolveGenetic resolveGenetic : population) {
            if (calculateFitness(bestFitnessResolve) <= calculateFitness(resolveGenetic)) {
                bestFitnessResolve = resolveGenetic;
            }
        }
        return bestFitnessResolve;
    }

}
