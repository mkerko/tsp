package by.bsu.tsp.genetic;

import by.bsu.tsp.genetic.domain.ResolveGenetic;
import by.bsu.tsp.util.ResolveGeneticComparator;
import by.bsu.tsp.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mikhail on 09.04.2017.
 */
public class GeneticAlgorithm {
    private static final int POPULATION_SIZE = 60;
    private static final int TOURNAMENT_SIZE = 7;
    private static final int VARIABLES_SIZE = 3;
    private static final int PIVOT  = 1;
    private static final int MIN_RANDOM = 0;
    private static final int MAX_RANDOM = 8;
    public static final int MAX_ITERATIONS = 200;
    private static final double MUTATION_RATE = 0.5;
    List<ResolveGenetic> population = new ArrayList<>();

    public void run() {
        for (int i = 1; i <= MAX_ITERATIONS; i++){
            evolve();
            System.out.println("######" + i);
            System.out.println(population.get(0));
            System.out.println();
        }
    }

    public GeneticAlgorithm() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            ResolveGenetic resolveGenetic = new ResolveGenetic();
            int[] vars = Util.generateVariables(MIN_RANDOM, MAX_RANDOM);
            resolveGenetic.setVars(vars);
            population.add(resolveGenetic);
        }
        Collections.sort(population, new ResolveGeneticComparator());
    }

    private ResolveGenetic tournament(List<ResolveGenetic> population) {
        List<ResolveGenetic> tournament = new ArrayList<>();
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomIndex = Util.random.nextInt(POPULATION_SIZE);
            tournament.add(i, population.get(randomIndex));
        }
        return Util.getResolveWithBestFitness(tournament);
    }

    private ResolveGenetic crossover(ResolveGenetic parent1, ResolveGenetic parent2) {
        ResolveGenetic child = new ResolveGenetic();
        int [] array = new int[VARIABLES_SIZE];
        System.arraycopy(parent1.getVars(), 0, array, 0, PIVOT);
        System.arraycopy(parent2.getVars(), PIVOT, array, PIVOT, child.getVars().length - 1);
        child.setVars(array);
        return child;
    }

    private void evolve() {
        ResolveGenetic parent1 = tournament(population);
        ResolveGenetic parent2 = tournament(population);
        ResolveGenetic child1 = crossover(parent1, parent2);
        ResolveGenetic child2 = crossover(parent2, parent1);
        mutate(child1);
        mutate(child2);
        if (isChildFitnessLessThenParentFitness(child1, parent1)
                && isChildFitnessLessThenParentFitness(child2, parent2) ){
            population.set(POPULATION_SIZE - 1, child1);
            population.set(POPULATION_SIZE - 2, child2);
        } else if (isChildFitnessLessThenParentFitness(child1, parent1)) {
            population.set(POPULATION_SIZE - 1, child1);
        } else if (isChildFitnessLessThenParentFitness(child2, parent2)){
            population.set(POPULATION_SIZE - 1, child2);
        }
        Collections.sort(population, new ResolveGeneticComparator());
    }

    private void mutate(ResolveGenetic resolveGenetic) {
        if (Math.random() <= MUTATION_RATE) {
            int index1 = 0;
            int index2 = 2;
            int oneVariable = resolveGenetic.getVars()[index1];
            resolveGenetic.setVariable(index1, resolveGenetic.getVars()[index2]);
            resolveGenetic.setVariable(index2, oneVariable);
        }
    }

    private boolean isChildFitnessLessThenParentFitness(ResolveGenetic child, ResolveGenetic parent1){
        return Util.calculateFitness(child) <= Util.calculateFitness(parent1);
    }
}
