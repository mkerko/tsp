package by.bsu.tsp.algs.domain;

import by.bsu.tsp.util.Util;

import java.util.*;

/**
 * Created by Mikhail on 16.04.2017.
 */
public class Resolve {
    private static final int MIN_VERTEX = 2;
    private List<Integer> vertexes;
    private int length;

    public Resolve() {
        vertexes = new ArrayList<>();
        vertexes.add(1);
    }

    public Resolve(int length, List<Integer> vertexes) {
        this.length = length;
        this.vertexes = vertexes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Integer> getVertexes() {
        return vertexes;
    }

    public void setVertexes(List<Integer> vertexes) {
        this.vertexes = vertexes;
    }

    public void setVertex( int vertex, int index){
        vertexes.set(index, vertex);
    }

    public static Resolve getResolveWithMinimumFitness(List<Resolve> population) {
        Resolve fittest = population.get(0);
        for (Resolve resolve : population) {
            if (fittest.getLength() <= resolve.getLength()) {
                fittest = resolve;
            }
        }
        return fittest;
    }

    public void generateRandomPopulation(int populationSize) {
        while (vertexes.size() != populationSize) {
            int randomVertexNumber = Util.random.nextInt(populationSize - MIN_VERTEX + 1) + MIN_VERTEX;
            if (vertexes.contains(randomVertexNumber)) {
                while (vertexes.contains(randomVertexNumber)) {
                    randomVertexNumber = Util.random.nextInt(populationSize - MIN_VERTEX + 1) + MIN_VERTEX;
                }
            }
            vertexes.add(randomVertexNumber);
        }
        vertexes.add(1);
    }

}
