package ru.nntu.vst.gorbatovskii.genetics;

import ru.nntu.vst.gorbatovskii.genetics.algorithm.ObjectiveFunction;
import ru.nntu.vst.gorbatovskii.genetics.algorithm.impl.GeneticAlgorithm;
import ru.nntu.vst.gorbatovskii.genetics.model.Edge;
import ru.nntu.vst.gorbatovskii.genetics.model.Graph;
import ru.nntu.vst.gorbatovskii.genetics.model.Vertex;
import ru.nntu.vst.gorbatovskii.genetics.utils.ApplicationContextUtils;

import java.util.LinkedList;
import java.util.List;
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
        List<Vertex> result = algorithm.processForGraph(graph, 200);
        long t2 = System.currentTimeMillis();

        ObjectiveFunction function = (ObjectiveFunction) ApplicationContextUtils.getApplicationContext().getBean("objectiveFunction");
        System.out.println(function.calculate(result, graph) + " " + result);
        System.out.println(t2 - t1);
        System.out.println(generateModules(result, graph));
    }

    private static List<List<Vertex>> generateModules(List<Vertex> chromosome, Graph graph) {
        return generateModules(chromosome, graph, new LinkedList<>(), 0);
    }

    private static List<List<Vertex>> generateModules(List<Vertex> chromosome, Graph graph, List<List<Vertex>> result, int level) {
        if (chromosome.size() <= 2 ) {
            result.add(chromosome);
        } else {
            double[] arr = calculateRate(chromosome, graph);
            double mid = mid(arr);
            int minPos = min(arr);
            if (arr[minPos] < mid) {
                List<Vertex> l1 = chromosome.subList(0, minPos + 1);
                List<Vertex> l2 = chromosome.subList(minPos + 1, chromosome.size());

                generateModules(l1, graph, result, level + 1);
                generateModules(l2, graph, result, level + 1);
            } else {
                result.add(chromosome);
            }
        }
        return result;
    }


    private static double[] calculateRate(List<Vertex> chromosome, Graph graph) {
        double[] arr = new double[chromosome.size() - 1];
        for (Edge edge : graph.getEdges()) {
            int posA = chromosome.indexOf(edge.getVertexA());
            int posB = chromosome.indexOf(edge.getVertexB());
            if (posA >= 0 && posB >= 0) {
                double rate = 1d / Math.abs(posB - posA);
                for (int i = Math.min(posA, posB); i < Math.max(posA, posB); i++) {
                    arr[i] = arr[i] + rate;
                }
            }
        }
        return arr;
    }

    private static double mid(double[] arr) {
        double result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result / arr.length;
    }

    public static int min(double[] arr) {
        int minPos = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minPos]) {
                minPos = i;
            }
        }
        return minPos;
    }

}
