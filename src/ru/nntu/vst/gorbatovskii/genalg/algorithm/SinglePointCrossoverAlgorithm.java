package ru.nntu.vst.gorbatovskii.genalg.algorithm;

import org.springframework.util.Assert;
import ru.nntu.vst.gorbatovskii.genalg.model.Vertex;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SinglePointCrossoverAlgorithm implements CrossoverAlgorithm {

    @Override
    public List<List<Vertex>> executeCrossingOver(List<Vertex> parentA, List<Vertex> parentB) {
        Assert.isTrue(parentA.size() == parentB.size(), "Chromosomes' size should be the same");

        int crossoverPoint = Math.abs(new Random().nextInt() % parentA.size());

        List<List<Vertex>> children = new LinkedList<>();
        children.add(composeChild(parentA, parentB, crossoverPoint));
        children.add(composeChild(parentB, parentA, crossoverPoint));
        return children;
    }

    private List<Vertex> composeChild(List<Vertex> parentA, List<Vertex> parentB, int crossoverPoint) {
        List<Vertex> child = new LinkedList<>(parentA.subList(0, crossoverPoint));
        int i = crossoverPoint, j = i;
        int chromosomeSize = parentB.size();
        while (i < chromosomeSize) {
            Vertex v = parentB.get(j % chromosomeSize);
            if (!child.contains(v)) {
                child.add(i++, v);
            }
            j++;
        }
        return child;
    }

    public static void main(String[] args) {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        List<Vertex> parent1 = Arrays.asList(v1, v2, v3, v4);
        List<Vertex> parent2 = Arrays.asList(v2, v3, v1, v4);
        System.out.println(new SinglePointCrossoverAlgorithm().executeCrossingOver(parent1, parent2));
    }
}
