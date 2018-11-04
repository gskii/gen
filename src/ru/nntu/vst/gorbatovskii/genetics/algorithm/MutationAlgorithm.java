package ru.nntu.vst.gorbatovskii.genetics.algorithm;

import ru.nntu.vst.gorbatovskii.genetics.model.Vertex;

import java.util.List;

public interface MutationAlgorithm {

    List<Vertex> executeMutation(List<Vertex> chromosome);
}
