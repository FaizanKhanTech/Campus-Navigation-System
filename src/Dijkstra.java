package algorithms;

import graph.Graph;
import model.Edge;
import model.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

    public static String findShortestPath(
            Graph graph,
            Location start,
            Location target) {

        Map<Location, Double> distances = new HashMap<>();
        Map<Location, Location> previous = new HashMap<>();

        Set<Location> visited = new HashSet<>();

        PriorityQueue<Location> queue =
                new PriorityQueue<>(
                        (a, b) -> Double.compare(
                                distances.get(a),
                                distances.get(b)
                        )
                );

        for (Location location : graph.getLocations()) {
            distances.put(
                    location,
                    Double.POSITIVE_INFINITY
            );
        }

        distances.put(start, 0.0);
        queue.add(start);

        while (!queue.isEmpty()) {

            Location current = queue.poll();

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);

            if (current.equals(target)) {
                break;
            }

            for (Edge edge : graph.getNeighbors(current)) {

                Location neighbor =
                        edge.getDestination();

                if (visited.contains(neighbor)) {
                    continue;
                }

                double newDistance =
                        distances.get(current)
                        + edge.getDistance();

                if (newDistance <
                        distances.get(neighbor)) {

                    distances.put(
                            neighbor,
                            newDistance
                    );

                    previous.put(
                            neighbor,
                            current
                    );

                    queue.add(neighbor);
                }
            }
        }

        if (distances.get(target) ==
                Double.POSITIVE_INFINITY) {

            return "\nNo path found from "
                    + start.getName()
                    + " to "
                    + target.getName()
                    + "\n";
        }

        StringBuilder result =
                new StringBuilder();

        result.append("Shortest Path:\n");

        buildPath(
                previous,
                start,
                target,
                result
        );

        result.append(
                "\nTotal Distance: "
        );

        result.append(
                distances.get(target)
        );

        result.append(" meters\n");

        return result.toString();
    }

    private static void buildPath(
            Map<Location, Location> previous,
            Location start,
            Location current,
            StringBuilder result) {

        if (!current.equals(start)) {

            buildPath(
                    previous,
                    start,
                    previous.get(current),
                    result
            );
        }

        result.append(
                "→ "
        );

        result.append(
                current.getName()
        );

        result.append("\n");
    }
}