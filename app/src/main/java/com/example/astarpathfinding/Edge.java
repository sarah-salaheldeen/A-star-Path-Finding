package com.example.astarpathfinding;

/**
 * Edge --- class to store an edge between End vertex and weight of the edge
 */
public class Edge {
    private Node node1, node2;
    private int cost;

    public Edge(Node node1, Node node2, int cost){
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
    }

    public Node getNode1(){
        return node1;
    }

    public Node getNode2(){
        return node2;
    }

    public int getCost(){
        return cost;
    }
}
