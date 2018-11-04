package ru.nntu.vst.gorbatovskii.genalg.algorithm;

import ru.nntu.vst.gorbatovskii.genalg.model.Vertex;

import java.util.List;

public interface CrossoverAlgorithm {

    List<List<Vertex>> executeCrossingOver(List<Vertex> parentA, List<Vertex> parentB);
}
