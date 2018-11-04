package ru.nntu.vst.gorbatovskii.genetics.algorithm;

import ru.nntu.vst.gorbatovskii.genetics.model.Graph;
import ru.nntu.vst.gorbatovskii.genetics.model.Vertex;

import java.util.List;

public interface ObjectiveFunction {

    int calculate(List<Vertex> chromosome, Graph graph);
}
