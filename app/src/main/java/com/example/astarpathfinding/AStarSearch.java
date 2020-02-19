package com.example.astarpathfinding;

import android.util.Log;

import java.util.ArrayList;

public class AStarSearch {
    private Node startNode, endNode, currentNode, openANode, parent;
    private ArrayList<Node> openList;
    private ArrayList<Node> path = new ArrayList<>();
    private ArrayList<Node> visitedNodes = new ArrayList<>();
    private int tentative_g_score;

    public AStarSearch(Node start, Node end){
        this.startNode = start;
        this.endNode = end;
    }

    ArrayList<Node> search() {
        openList = new ArrayList<>();
        ArrayList<Node> closedList = new ArrayList<>();

        openList.add(startNode);
        while(!openList.isEmpty()){
            getNodeWithLowestFScore();
            if (currentNode.equals(endNode)){
                ArrayList<Node> finalPath = constructPath(currentNode);
                for (Node  node : finalPath){
                    if (node.getParent() != null && node.getAdjacencyList().size() != 1 && node != startNode && node != endNode){
                        if (node.getAdjacencyList().contains(endNode)) continue;
                        visitedNodes.add(node);
                    }
                }
                Log.d("AStarSearch", "Final path: " + finalPath.get(2).getId() + " -> " + finalPath.get(1).getId() + " -> " + finalPath.get(0).getId());
                        return finalPath;
                }

            openList.remove(currentNode);
            closedList.add(currentNode);


            ArrayList<Node> adjacentNodes = getAdjacentNodes();
            for (Node aNode : adjacentNodes) {
                if (!visitedNodes.contains(aNode)){
                    if (closedList.contains(aNode)) {
                        continue;
                    }

                int tempScore = currentNode.getCost() + aNode.getCost(currentNode);

                if (openList.contains(aNode)) {
                    if (tempScore < aNode.getCost()) {
                        aNode.setCost(tempScore);
                        aNode.setParent(currentNode);
                    }
                } else {
                    aNode.setCost(tempScore); //this is supposed to be setG();
                    openList.add(aNode);
                    aNode.setParent(currentNode);
                }
                aNode.setF(aNode.getCost() + aNode.getH()); //this is supposed to be getG();
            }
            }
        }
        return null;
    }

    private void getNodeWithLowestFScore() {
        this.currentNode = openList.get(0);
            for (Node node : openList) {
                if (node.getF() < this.currentNode.getF()) {
                    this.currentNode = node;
                }
            }
        }

    private boolean isNotVisited(Node node){
        if (path != null)
        for (Node node1 : path) {
            if (node1 == node && node1.getParent() != null) return false;
        }
        return true;
    }

    private ArrayList<Node> getAdjacentNodes(){
        return currentNode.getAdjacencyList();
    }

        /*public int heuristic(Node node, Node goal){
        return Math.sqrt()
        }*/

    private ArrayList<Node> constructPath(Node node){
        path = new ArrayList<>();
        path.add(node);
        Log.d("AStarSearch", "node1: " + node.getId());
        while (node.getParent() != null){
            Log.d("AStarSearch", "Why we are here!!!");
            node = node.getParent();
            Log.d("AStarSearch", "node.getParent() " + node.getParent().getId());
            Log.d("AStarSearch", "node: " + node.getId() + "\n");
            path.add(node);
            if (node == startNode) break;
        }
        //Log.d("AStarSearch", "Final path: " + path.get(2).getId() + " -> " + path.get(1).getId() + " -> " + path.get(0).getId());
        return path;
    }

    private int dist(Node aNode, Node currentNode){
        aNode.setParent(currentNode);
        int g = aNode.setG();
        return g;
    }
}