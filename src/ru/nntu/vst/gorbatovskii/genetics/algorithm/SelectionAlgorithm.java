package ru.nntu.vst.gorbatovskii.genetics.algorithm;

import ru.nntu.vst.gorbatovskii.genetics.model.Graph;
import ru.nntu.vst.gorbatovskii.genetics.model.Vertex;

import java.util.List;

public interface SelectionAlgorithm {

    List<List<Vertex>> executeSelection(List<List<Vertex>> chromosomes, int population, Graph graph);
}
