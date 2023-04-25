package tests;

import aufgabe.Line;
import aufgabe.Station;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLine {
    @Test
    public void testGetName() {
        Line line = new Line("Red");
        assertEquals("Red", line.getName());
    }

    @Test
    public void testGetStations() {
        Line line = new Line("Blue");
        Station s1 = new Station("A");
        Station s2 = new Station("B");
        Station s3 = new Station("C");

        line.addStation(s1);
        line.addStation(s2);
        line.addStation(s3);

        List<Station> stations = line.getStations();

        assertEquals(3, stations.size());
        assertTrue(stations.contains(s1));
        assertTrue(stations.contains(s2));
        assertTrue(stations.contains(s3));
    }

    @Test
    public void testAddStation() {
        Line line = new Line("Green");
        Station s1 = new Station("X");
        Station s2 = new Station("Y");

        line.addStation(s1);
        line.addStation(s2);

        List<Station> stations = line.getStations();

        assertEquals(2, stations.size());
        assertTrue(stations.contains(s1));
        assertTrue(stations.contains(s2));
    }
}