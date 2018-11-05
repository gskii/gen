package ru.nntu.vst.gorbatovskii.genetics;

import javafx.util.Pair;
import ru.nntu.vst.gorbatovskii.genetics.algorithm.ObjectiveFunction;
import ru.nntu.vst.gorbatovskii.genetics.algorithm.impl.GeneticAlgorithm;
import ru.nntu.vst.gorbatovskii.genetics.model.Edge;
import ru.nntu.vst.gorbatovskii.genetics.model.Graph;
import ru.nntu.vst.gorbatovskii.genetics.model.Vertex;
import ru.nntu.vst.gorbatovskii.genetics.utils.ApplicationContextUtils;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        GeneticAlgorithm algorithm = (GeneticAlgorithm) ApplicationContextUtils.getApplicationContext().getBean("geneticAlgorithm");
        Graph graph = new Graph();
        graph.setEdges(ApplicationContextUtils.getApplicationContext()
                .getBeansOfType(Edge.class).values().stream().collect(Collectors.toList()));
        graph.setVertices(ApplicationContextUtils.getApplicationContext()
                .getBeansOfType(Vertex.class).values().stream().collect(Collectors.toList()));
        long t1 = System.currentTimeMillis();
        List<Vertex> result = algorithm.processForGraph(graph, 50);
        long t2 = System.currentTimeMillis();

        ObjectiveFunction function = (ObjectiveFunction) ApplicationContextUtils.getApplicationContext().getBean("objectiveFunction");
        System.out.println(function.calculate(result, graph) + " " + result);
        System.out.println(t2 - t1);
    }
}
