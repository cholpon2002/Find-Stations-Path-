package tests;

import aufgabe.Main;
import aufgabe.Station;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestAlgorithm {

    @Test
    public void testFindShortestPath() {
        Station a = new Station("A");
        Station b = new Station("B");
        Station c = new Station("C");
        Station d = new Station("D");

        a.addNeighbor(b);
        b.addNeighbor(c);
        c.addNeighbor(d);

        // Test for existing path
        List<Station> expectedPath = new ArrayList<>();
        expectedPath.add(a);
        expectedPath.add(b);
        expectedPath.add(c);
        expectedPath.add(d);

        List<Station> actualPath = Main.findShortestPath(a, d);
        assertEquals(expectedPath, actualPath);

        // Test for non-existing path
        Station e = new Station("E");
        assertNull(Main.findShortestPath(a, e));
    }
}