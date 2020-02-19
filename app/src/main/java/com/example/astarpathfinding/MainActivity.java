package com.example.astarpathfinding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private int pathsCounter = 0;
    ArrayList<Node> finalPath = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");

        Edge edgeAB = new Edge(A, B, 1);
        Edge edgeBA = new Edge(B, A, 1);
        Edge edgeAD = new Edge(A, D, 5);
        Edge edgeDA = new Edge(D, A, 5);

        Edge edgeBD = new Edge(B, D, 2);
        Edge edgeDB = new Edge(D, B, 2);

        Edge edgeCD = new Edge(C, D, 1);
        Edge edgeDC = new Edge(D, C, 1);

        A.addEdge(edgeAB);
        A.addEdge(edgeAD);

        B.addEdge(edgeBD);
        B.addEdge(edgeBA);

        C.addEdge(edgeCD);

        D.addEdge(edgeDA);
        D.addEdge(edgeDB);
        D.addEdge(edgeDC);

        A.addAdjacent(B);
        A.addAdjacent(D);
        B.addAdjacent(A);
        B.addAdjacent(D);
        C.addAdjacent(D);
        D.addAdjacent(A);
        D.addAdjacent(C);

        /*A.setG();
        B.setG();
        C.setG();
        D.setG();*/

        A.setH(0);
        B.setH(0);
        C.setH(0);
        D.setH(0);
        /*B.setParent(A);
        C.setParent(D);
        D.setParent(A);*/

        //Log.d("MainActivity", "s adjacent: " + s.getAdjacencyList().get(0) + " | m adjacent: " + m.getAdjacencyList().get(0) +" , " + m.getAdjacencyList().get(1) + " | d adjacent: " + d.getAdjacencyList().get(0));

        AStarSearch aStarSearch = new AStarSearch(A, C);
        while (pathsCounter < 2) {
            finalPath = aStarSearch.search();
            if (finalPath.size() == 3)
            Log.d("MainActivity", finalPath.get(2).getId() + " -> " + finalPath.get(1).getId() + " -> " + finalPath.get(0).getId());
            if (finalPath.size() == 4)
                Log.d("MainActivity", finalPath.get(3).getId() + " -> " + finalPath.get(2).getId() + " -> " + finalPath.get(1).getId() + " -> " + finalPath.get(0).getId());
            pathsCounter++;
        }
        //Log.d("MainActivity", "Final path: " + finalPath);

        /*List<Node> path = aStarSearch.nextShortestPath();
        while(path != null) {
            pathCounter++;
            Log.d("MainActivity", path + " ");
            if (pathCounter == 2) path = aStarSearch.nextShortestPath();
        }*/
       /* if (finalPath != null) {
            Log.d("MainActivity", finalPath.get(2).getId() + " -> " + finalPath.get(1).getId() + " -> " + finalPath.get(0).getId());
            //Log.d("MainActivity", finalPath.get(1).get(2).getId() + " -> " + finalPath.get(1).get(1).getId() + " -> " + finalPath.get(1).get(0).getId());
    }
        else Log.d("MainActivity", "Final path is null ");*/
        }
    }
