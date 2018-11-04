package ru.nntu.vst.gorbatovskii.genetics.model;

import java.util.List;

public class Graph {
    List<Vertex> vertices;
    List<Edge> edges;

    public List<Vertex> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }
}
