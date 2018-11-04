package ru.nntu.vst.gorbatovskii.genalg.algorithm;

import ru.nntu.vst.gorbatovskii.genalg.model.Vertex;

import java.util.List;

public interface MutationAlgorithm {

    List<Vertex> executeMutation(List<Vertex> chromosome);
}
