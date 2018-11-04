package ru.nntu.vst.gorbatovskii.genalg.algorithm;

import ru.nntu.vst.gorbatovskii.genalg.model.Edge;
import ru.nntu.vst.gorbatovskii.genalg.model.Graph;
import ru.nntu.vst.gorbatovskii.genalg.model.Vertex;

import java.util.List;

public class ObjectiveFunctionImpl implements ObjectiveFunction {

    @Override
    public int calculate(List<Vertex> chromosome, Graph graph) {
        int result = 0;
        for (Edge edge : graph.getEdges()) {
            int posA = chromosome.indexOf(edge.getVertexA());
            int posB = chromosome.indexOf(edge.getVertexB());
            result += Math.abs(posB - posA);
        }
        return result;
    }
}
