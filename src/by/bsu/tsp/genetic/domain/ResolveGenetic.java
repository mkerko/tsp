package by.bsu.tsp.genetic.domain;

import by.bsu.tsp.util.Util;

import java.util.Arrays;

/**
 * Created by Mikhail on 09.04.2017.
 */
public class ResolveGenetic {

    private int [] vars = new int [3];

    public ResolveGenetic() {}

    public int[] getVars() {
        return vars;
    }

    public void setVars(int[] vars) {
        this.vars = vars;
    }

    public void setVariable(int index, int variable) {
        this.vars[index] = variable;
    }

    @Override
    public String toString() {
        return "ResolveGenetic{" +
                "vars=" + Arrays.toString(vars) +
                ", fitness = " + Util.calculateFitness(this) + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResolveGenetic that = (ResolveGenetic) o;
        return Arrays.equals(getVars(), that.getVars());
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getVars());
    }
}
