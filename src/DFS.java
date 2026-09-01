package algorithms;

import graph.Graph;
import model.Edge;
import model.Location;

import java.util.HashSet;
import java.util.Set;

public class DFS {

    public static String traverse(Graph graph, Location start) {

        Set<Location> visited = new HashSet<>();
        StringBuilder result = new StringBuilder();

        result.append("DFS Traversal:\n");

        traverseRecursive(
                graph,
                start,
                visited,
                result
        );

        return result.toString();
    }

    private static void traverseRecursive(
            Graph graph,
            Location current,
            Set<Location> visited,
            StringBuilder result) {

        visited.add(current);

        result.append(current.getName()).append("\n");

        for (Edge edge : graph.getNeighbors(current)) {

            Location neighbor =
                    edge.getDestination();

            if (!visited.contains(neighbor)) {

                traverseRecursive(
                        graph,
                        neighbor,
                        visited,
                        result
                );
            }
        }
    }
}