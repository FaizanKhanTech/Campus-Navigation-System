package algorithms;

import graph.Graph;
import model.Edge;
import model.Location;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {

    public static String traverse(Graph graph, Location start) {

        Queue<Location> queue = new LinkedList<>();
        Set<Location> visited = new HashSet<>();

        StringBuilder result = new StringBuilder();

        queue.add(start);
        visited.add(start);

        result.append("BFS Traversal:\n");

        while (!queue.isEmpty()) {

            Location current = queue.poll();

            result.append(current.getName()).append("\n");

            for (Edge edge : graph.getNeighbors(current)) {

                Location neighbor = edge.getDestination();

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return result.toString();
    }
}
