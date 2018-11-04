package ru.nntu.vst.gorbatovskii.genalg.algorithm.impl;

import org.springframework.beans.factory.annotation.Required;
import ru.nntu.vst.gorbatovskii.genalg.algorithm.CrossoverAlgorithm;
import ru.nntu.vst.gorbatovskii.genalg.algorithm.MutationAlgorithm;
import ru.nntu.vst.gorbatovskii.genalg.algorithm.ObjectiveFunction;
import ru.nntu.vst.gorbatovskii.genalg.algorithm.SelectionAlgorithm;
import ru.nntu.vst.gorbatovskii.genalg.model.Graph;
import ru.nntu.vst.gorbatovskii.genalg.model.Vertex;
import ru.nntu.vst.gorbatovskii.genalg.utils.RandomUtils;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class GeneticAlgorithm {

    private final int populationSize;
    private final double mutationRate;

    private CrossoverAlgorithm crossoverAlgorithm;
    private SelectionAlgorithm selectionAlgorithm;
    private MutationAlgorithm mutationAlgorithm;
    private ObjectiveFunction objectiveFunction;

    public GeneticAlgorithm(int populationSize, double mutationRate) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
    }

    public List<Vertex> processForGraph(Graph graph, int populationCount) {
        List<List<Vertex>> population = composeInitialPopulation();
        for (int populationNumber = 0; populationNumber < populationCount; populationNumber++) {
            population = executeSelection(executeMutation(executeReproduction(population)), graph);
        }

        return population.stream().min(new Comparator<List<Vertex>>() {
            @Override
            public int compare(List<Vertex> o1, List<Vertex> o2) {
                int o1Value = objectiveFunction.calculate(o1, graph);
                int o2Value = objectiveFunction.calculate(o2, graph);
                return Integer.compare(o1Value, o2Value);
            }
        }).get();
    }

    private List<List<Vertex>> composeInitialPopulation() {
        return null;
    }

    private List<List<Vertex>> executeReproduction(List<List<Vertex>> population) {
        List<List<Vertex>> newPopulation = new LinkedList<>(population);
        for (List<Vertex> parentA : population) {
            List<Vertex> parentB = population.get(RandomUtils.getPositiveRandom(populationSize));
            newPopulation.addAll(crossoverAlgorithm.executeCrossingOver(parentA, parentB));
        }

        return newPopulation;
    }

    private List<List<Vertex>> executeMutation(List<List<Vertex>> population) {
        population.parallelStream().forEach(new Consumer<List<Vertex>>() {

            @Override
            public void accept(List<Vertex> chromosome) {
                if (RandomUtils.getBoolean(mutationRate)) {
                    mutationAlgorithm.executeMutation(chromosome);
                }
            }
        });
        return population;
    }

    private List<List<Vertex>> executeSelection(List<List<Vertex>> population, Graph graph) {
        return selectionAlgorithm.executeSelection(population, populationSize, graph);
    }

    @Required
    public void setCrossoverAlgorithm(CrossoverAlgorithm crossoverAlgorithm) {
        this.crossoverAlgorithm = crossoverAlgorithm;
    }

    @Required
    public void setSelectionAlgorithm(SelectionAlgorithm selectionAlgorithm) {
        this.selectionAlgorithm = selectionAlgorithm;
    }

    @Required
    public void setMutationAlgorithm(MutationAlgorithm mutationAlgorithm) {
        this.mutationAlgorithm = mutationAlgorithm;
    }

    @Required
    public void setObjectiveFunction(ObjectiveFunction objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }
}
