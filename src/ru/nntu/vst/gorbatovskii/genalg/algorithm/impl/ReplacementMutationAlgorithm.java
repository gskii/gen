package ru.nntu.vst.gorbatovskii.genalg.algorithm.impl;

import ru.nntu.vst.gorbatovskii.genalg.algorithm.MutationAlgorithm;
import ru.nntu.vst.gorbatovskii.genalg.model.Vertex;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ReplacementMutationAlgorithm implements MutationAlgorithm {

    @Override
    public List<Vertex> executeMutation(List<Vertex> chromosome) {
        Random random = new Random();
        int chromosomeSize = chromosome.size();
        int posA = Math.abs(random.nextInt() % chromosomeSize);
        int posB = Math.abs(random.nextInt() % chromosomeSize);
        Vertex temp = chromosome.set(posA, chromosome.get(posB));
        chromosome.set(posB, temp);
        return chromosome;
    }

    public static void main(String[] args) {
        Vertex v1 = new Vertex("1");
        Vertex v2 = new Vertex("2");
        Vertex v3 = new Vertex("3");
        Vertex v4 = new Vertex("4");
        List<Vertex> parent1 = Arrays.asList(v1, v2, v3, v4);
        System.out.println(new ReplacementMutationAlgorithm().executeMutation(parent1));
    }
}
