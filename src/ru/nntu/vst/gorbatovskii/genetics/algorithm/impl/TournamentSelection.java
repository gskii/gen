package ru.nntu.vst.gorbatovskii.genetics.algorithm.impl;

import org.jgrapht.alg.util.Pair;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;
import ru.nntu.vst.gorbatovskii.genetics.algorithm.ObjectiveFunction;
import ru.nntu.vst.gorbatovskii.genetics.algorithm.SelectionAlgorithm;
import ru.nntu.vst.gorbatovskii.genetics.model.Graph;
import ru.nntu.vst.gorbatovskii.genetics.model.Vertex;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TournamentSelection implements SelectionAlgorithm {

    private ObjectiveFunction objectiveFunction;
    private final int tournamentRange;

    public TournamentSelection(int tournamentRange) {
        this.tournamentRange = tournamentRange;
    }

    @Override
    public List<List<Vertex>> executeSelection(List<List<Vertex>> chromosomes, int population, Graph graph) {
        Assert.isTrue(chromosomes.size() >= tournamentRange,
                "Tournament range can't be greater than current population size");

        return executeTournament(chromosomes, population, graph);
    }

    private List<List<Vertex>> executeTournament(final List<List<Vertex>> chromosomes,
                                                 final int population,
                                                 final Graph graph) {

        final List<Pair<Integer, List<Vertex>>> valuedChromosomes = valueChromosome(chromosomes, graph);
        return IntStream.range(0, population).mapToObj(
                new IntFunction<List<Vertex>>() {

                    @Override
                    public List<Vertex> apply(int value) {
                        return composeParticipants(chromosomes.size()).stream()
                                .map(valuedChromosomes::get)
                                .min(Comparator.comparing(Pair::getFirst))
                                .get().getSecond();
                    }
                }).collect(Collectors.toList());
    }

    private List<Pair<Integer, List<Vertex>>> valueChromosome(List<List<Vertex>> chromosomes, Graph graph) {
        return chromosomes.stream().map(new Function<List<Vertex>, Pair<Integer, List<Vertex>>>() {

            @Override
            public Pair<Integer, List<Vertex>> apply(List<Vertex> vertices) {
                return new Pair<>(objectiveFunction.calculate(vertices, graph), vertices);
            }
        }).collect(Collectors.toList());
    }

    private List<Integer> composeParticipants(int chromosomesCount) {
        Random random = new Random();

        List<Integer> tournamentParticipants = new ArrayList<>(tournamentRange);
        for (int j = 0; j < tournamentRange; j++) {

            while (true) {
                int chromosomeNumber = Math.abs(random.nextInt() % chromosomesCount);
                if (!tournamentParticipants.contains(chromosomeNumber)) {
                    tournamentParticipants.add(chromosomeNumber);
                    break;
                }
            }
        }
        return tournamentParticipants;
    }

    @Required
    public void setObjectiveFunction(ObjectiveFunction objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }
}
