import java.util.ArrayList;
import java.util.List;

public class Station {
    private String name;
    private List<Station> neighbors;

    public Station(String name) {
        this.name = name;
        neighbors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Station> getNeighbors() {
        return neighbors;
    }

    public void addNeighbor(Station neighbor) {
        neighbors.add(neighbor);
        neighbor.neighbors.add(this);
    }

}



