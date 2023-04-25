package tests;

import aufgabe.Station;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestStation {

    @Test
    public void testGetName() {
        Station station = new Station("Station A");
        assertEquals("Station A", station.getName());
    }

    @Test
    public void testGetNeighbors() {
        Station stationA = new Station("Station A");
        Station stationB = new Station("Station B");
        stationA.addNeighbor(stationB);
        assertTrue(stationA.getNeighbors().contains(stationB));
        assertTrue(stationB.getNeighbors().contains(stationA));
    }

    @Test
    public void testAddNeighbor() {
        Station stationA = new Station("Station A");
        Station stationB = new Station("Station B");
        stationA.addNeighbor(stationB);
        assertTrue(stationA.getNeighbors().contains(stationB));
        assertTrue(stationB.getNeighbors().contains(stationA));
    }
}