package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Edge;
import model.Location;

public class Graph {

    private Map<Location, List<Edge>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addLocation(Location location) {
        adjacencyList.putIfAbsent(
                location,
                new ArrayList<>()
        );
    }

    public void addEdge(
            Location source,
            Location destination,
            double distance) {

        adjacencyList
                .get(source)
                .add(new Edge(destination, distance));
    }

    public List<Edge> getNeighbors(Location location) {

        return adjacencyList.getOrDefault(
                location,
                new ArrayList<>()
        );
    }

    public Map<Location, List<Edge>> getAdjacencyList() {

        return adjacencyList;
    }

    public Set<Location> getLocations() {

        return adjacencyList.keySet();
    }

    public void displayGraph() {

        for (Location location :
                adjacencyList.keySet()) {

            System.out.print(
                    location.getName() + " -> "
            );

            for (Edge edge :
                    adjacencyList.get(location)) {

                System.out.print(
                        edge + " | "
                );
            }

            System.out.println();
        }
    }

    public String getGraphString() {

        StringBuilder result =
                new StringBuilder();

        for (Location location :
                adjacencyList.keySet()) {

            result.append(
                    location.getName()
            );

            result.append(" -> ");

            for (Edge edge :
                    adjacencyList.get(location)) {

                result.append(edge);
                result.append(" | ");
            }

            result.append("\n");
        }

        return result.toString();
    }
}