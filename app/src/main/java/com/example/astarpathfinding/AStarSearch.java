package com.example.astarpathfinding;

import android.util.Log;

import java.util.ArrayList;

public class AStarSearch {
    private Node startNode, endNode, currentNode, openANode;
    private ArrayList<Node> openList;
    private ArrayList<Node> closedList;
    private ArrayList<Node> adjacentNodes,path;

    public AStarSearch(Node start, Node end){
        this.startNode = start;
        this.endNode = end;

        openList = new ArrayList<>();
        closedList = new ArrayList<>();
        adjacentNodes = new ArrayList<>();
    }

    ArrayList<Node> search(){
        openList.add(startNode);
        startNode.setG(0);
        startNode.setF(startNode.getG() + startNode.getH());
        while (!openList.isEmpty()){
            getNodeWithLowestFScore(); //set currentNode to the node with the lowest f score
            closedList.add(currentNode);
            //Log.d("AStarSearch", "closed List: " + closedList);
            openList.remove(currentNode);
            if (closedList.contains(endNode)){
                Log.d("AStarSearch", "closed List: " + closedList.get(0).getId() + " " + closedList.get(1).getId() + " " + closedList.get(2).getId() + " " + closedList.get(3).getId());
                //Log.d("AStarSearch", "Why we are here!!!");
                //a path is found
                for (Node node : closedList){
                    if (node.getId().equals(endNode.getId())) return constructPath(node);
                }
                //break; //break the loop
            }
            adjacentNodes = getAdjacentNodes();
            Log.d("AStarSearch", "adjacentNodes: " + adjacentNodes.get(0));
            for(Node aNode : adjacentNodes){
                if(!closedList.contains(aNode)){
                    aNode.setParent(currentNode);
                    int g = aNode.setG();
                    aNode.setF( g + aNode.getH());
                    if (!openList.contains(aNode)){
                        openList.add(aNode);
                    }else {
                        for (Node node : openList){
                            if (node == aNode){ openANode = node; }
                        }
                        if (aNode.getG() < openANode.getG()){
                            openANode.setG(aNode.getG());
                            openANode.setParent(currentNode);
                        }
                    }
                }
            }
        }

        return null;
    }

    private void getNodeWithLowestFScore() {
        currentNode = openList.get(0);
        for (Node node : openList) {
            if (node.getF() < currentNode.getF()) {
                currentNode = node;
            }
        }
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
                return path;
        }
    }