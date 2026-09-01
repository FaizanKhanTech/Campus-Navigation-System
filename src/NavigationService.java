package services;

import algorithms.Dijkstra;
import graph.Graph;
import model.Location;

public class NavigationService {

    private Graph graph;

    public NavigationService(Graph graph) {
        this.graph = graph;
    }

    public void findShortestRoute(
            Location source,
            Location destination) {

        System.out.println(
            "\nFinding shortest route from "
            + source.getName()
            + " to "
            + destination.getName()
        );

        Dijkstra.findShortestPath(
            graph,
            source,
            destination
        );
    }
}
