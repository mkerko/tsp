package by.bsu.tsp;

import by.bsu.tsp.algs.*;
import by.bsu.tsp.genetic.GeneticAlgorithm;

public class Main {

    private static Burn burn = new Burn();
    private static Full full = new Full();
    private static Gen gen = new Gen();
    private static Insatiable insatiable = new Insatiable();
    private static Search search = new Search();
    private static GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

    public static void main(String args[]) {
//        burn.run();
//        full.run();
//        gen.run();
//        insatiable.run();
//        search.run();
        geneticAlgorithm.run();
    }
}