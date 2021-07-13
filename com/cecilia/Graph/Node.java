package com.cecilia.Graph;

import java.util.LinkedList;
import java.util.List;

public class Node {
    public int value;
    private List<Node> neighbors = new LinkedList<>();

    public Node(int v) {
        this.value = v;
    }

    public List<Node> getNeighbors() {
        return this.neighbors;
    }
}
