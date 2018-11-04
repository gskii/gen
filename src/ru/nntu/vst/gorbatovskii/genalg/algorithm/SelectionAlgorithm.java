package ru.nntu.vst.gorbatovskii.genalg.algorithm;

import ru.nntu.vst.gorbatovskii.genalg.model.Graph;
import ru.nntu.vst.gorbatovskii.genalg.model.Vertex;

import java.util.List;

public interface SelectionAlgorithm {

    List<List<Vertex>> executeSelection(List<List<Vertex>> chromosomes, int population, Graph graph);
}
