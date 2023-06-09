package aufgabe;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private List<Station> stations;

    public Line(String name) {
        this.name = name;
        stations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void addStation(Station station) {
        stations.add(station);
    }
}
