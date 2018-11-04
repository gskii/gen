package ru.nntu.vst.gorbatovskii.genetics.algorithm;

import ru.nntu.vst.gorbatovskii.genetics.model.Vertex;

import java.util.List;

public interface CrossoverAlgorithm {

    List<List<Vertex>> executeCrossingOver(List<Vertex> parentA, List<Vertex> parentB);
}
