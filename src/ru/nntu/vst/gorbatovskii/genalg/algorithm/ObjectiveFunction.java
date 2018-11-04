package ru.nntu.vst.gorbatovskii.genalg.algorithm;

import ru.nntu.vst.gorbatovskii.genalg.model.Graph;
import ru.nntu.vst.gorbatovskii.genalg.model.Vertex;

import java.util.List;

public interface ObjectiveFunction {

    int calculate(List<Vertex> chromosome, Graph graph);
}
