package com.example.astarpathfinding;

import java.util.ArrayList;

/**
 * Node --- class to store each vertex along with adjacent vertices and cost of the edges.
 */
public class Node {
    private String id;
    private int g, h, f, cost;
    //private Edge edge1, edge2, edge3;
    private ArrayList<Node> adjacencyList;
    private ArrayList<Edge> edgeList = new ArrayList<>();
    private Node parent;
    private boolean valid;

    public Node(String id){
        this.id = id;
        adjacencyList = new ArrayList<>();
        //this.cost = cost;
    }

    //public abstract int distanceTo(Node dest);

    //Getter method for start vertex
    public String getId(){
        return id;
    }

    public int getG(){
        return g;
    }

    public int getH(){
        return h;
    }

    public int getF(){
        return f;
    }

    public int setG(){
        for (Edge edge : this.edgeList) {
            if (edge.getNode1() == this.parent || edge.getNode2() == this.parent){
                this.cost = edge.getCost();
            }
        }
        this.g = this.parent.getG() + this.cost;
        return this.g;
    }

    public int getCost() {
        return cost;
    }

    public int getCost(Node current) {
        for (Edge edge : this.edgeList) {
            if (edge.getNode1() == current || edge.getNode2() == current) {
                return edge.getCost();
            }
        }
        return 0;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setG(int g){
        this.g = g;
    }

    public void setH(int h){
        this.h = h;
    }

    public void setF(int f){
        this.f = f;
    }

    public void addEdge(Edge edge) {
        this.edgeList.add(edge);
    }

    //Getter method for end vertex
    public ArrayList<Node> getAdjacencyList(){

        return adjacencyList;
    }

    //add node to the adajcent list
    public void addAdjacent(Node endVertex){
        adjacencyList.add(endVertex);
        parent = this;
    }

    public Node getParent(){
        return parent;
    }

    public void setParent(Node parent){
        this.parent = parent;
    }

    public boolean seen(Node node) {
        if(this == node)
            return true;
        else if(parent == null)
            return false;
        else
            return this.parent.seen(node);
    }

    public void setValid(boolean valid){
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}
