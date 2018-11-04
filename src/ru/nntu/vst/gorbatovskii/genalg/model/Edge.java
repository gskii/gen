package ru.nntu.vst.gorbatovskii.genalg.model;

public class Edge {

    private Vertex vertexA;
    private Vertex vertexB;

    public Edge(Vertex vertexA, Vertex vertexB) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
    }

    public Vertex getVertexA() {
        return vertexA;
    }

    public Vertex getVertexB() {
        return vertexB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge edge = (Edge) o;

        return vertexB.equals(edge.vertexB) && vertexA.equals(edge.vertexA);
    }

    @Override
    public int hashCode() {
        int result = vertexA.hashCode();
        result = 31 * result + vertexB.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Edge{" + vertexA + ", " + vertexB + '}';
    }
}
