package structures;

import model.Location;
import java.util.HashMap;
import java.util.Map;

public class LocationDirectory {

    private Map<Integer, Location> locations;

    public LocationDirectory() {
        locations = new HashMap<>();
    }

    public void addLocation(Location location) {
        locations.put(location.getId(), location);
    }

    public Location findLocation(int id) {
        return locations.get(id);
    }

    public boolean containsLocation(int id) {
        return locations.containsKey(id);
    }

    public int size() {
        return locations.size();
    }

    public void displayLocations() {

        System.out.println("\nLocation Directory:");

        for (Location location : locations.values()) {
            System.out.println(
                location.getId() + " - " + location.getName()
            );
        }
    }
}
