package structures;

import model.Location;
import java.util.Stack;

public class LocationStack {

    private Stack<Location> stack;

    public LocationStack() {
        stack = new Stack<>();
    }

    public void push(Location location) {
        stack.push(location);
    }

    public Location pop() {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.pop();
    }

    public Location peek() {
        if (stack.isEmpty()) {
            return null;
        }

        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }

    public void displayHistory() {

        System.out.println("\nLocation History:");

        for (Location location : stack) {
            System.out.println(location.getName());
        }
    }
}
