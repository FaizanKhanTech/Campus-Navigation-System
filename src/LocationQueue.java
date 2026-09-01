package structures;

import model.Location;
import java.util.LinkedList;
import java.util.Queue;

public class LocationQueue {

    private Queue<Location> queue;

    public LocationQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(Location location) {
        queue.add(location);
    }

    public Location dequeue() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.poll();
    }

    public Location peek() {
        if (queue.isEmpty()) {
            return null;
        }

        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void displayQueue() {

        System.out.println("\nLocation Queue:");

        for (Location location : queue) {
            System.out.println(location.getName());
        }
    }
}
