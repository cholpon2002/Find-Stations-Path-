package aufgabe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Main {
    static ArrayList<Line> lineArrayList;
    public static Station findStationByNameOrCreate(String name){
        for (Line line:
                lineArrayList) {
            for (Station st:
                    line.getStations()) {
                if(st.getName().equals(name)){
                    return st;
                }
            }
        }
        return new Station(name);
    }

    public static void main(String[] args) throws IOException{
        //ein Dateiobjekt aus dem Ordner Lines erstellen
        File dir = new File("Lines");
        //alle Dateien aus dem Ordner ziehen
        File[] files = dir.listFiles();
        //eine leere Liste initialisiert
        lineArrayList = new ArrayList<>();
        //Leerheitstest machen
        assert files != null;
        for (File file : files) {
            if(file.isFile()) {
                BufferedReader inputStream = null;
                String line;
                try {
                    inputStream = new BufferedReader(new FileReader(file, StandardCharsets.UTF_16));
                    Line myLine = new Line(file.getName().replace(".txt", ""));
                    Station oldStation = null;

                    while ((line = inputStream.readLine()) != null) {
                        Station myStation = findStationByNameOrCreate(line);
                        if (oldStation != null)
                            myStation.addNeighbor(oldStation); //fügt den vorherigen Sender als Nachbarn hinzu
                        oldStation = myStation; //setzt die vorherige Station mit der neuen Station gleich
                        myLine.addStation(myStation);
                    }
                    lineArrayList.add(myLine); //die Liste mit Linien aus dem txt auffüllen
                }catch(IOException e) {
                    System.out.println(e);
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        }

        for (Line line:
             lineArrayList) {
            System.out.println(line.getName());
            for (Station st:
                 line.getStations()) {
                System.out.print(st.getName() + " : ");

                for (Station neighbor:
                     st.getNeighbors()) {
                    System.out.print(neighbor.getName() + " --- ");
                }

                System.out.println();
            }
            System.out.println();
        }


        Station start;
        Station finish;
        Scanner scanner = new Scanner(System.in);
        System.out.println("=========================================");
        System.out.println("Bitte geben Sie einen Startpunkt ein: ");
        start = findStationByNameOrCreate(scanner.nextLine());
        System.out.println(start.getName());

        System.out.println("=========================================");
        System.out.println("Bitte geben Sie einen Endpunkt ein: ");
        finish = findStationByNameOrCreate(scanner.nextLine());

        List<Station> path = findShortestPath(start, finish);
        if (path != null){
            System.out.println("Juhu!\nKurz Weg wurde gefunden:\n=========================================");

            for (Station st:
                    path) {
                System.out.println(st.getName());
            }
        }
        else{
            System.out.println("Weg ist leider nicht gefunden :(");
        }
}

    public static List<Station> findShortestPath (Station start, Station end){
        // Erstellen Sie eine Warteschlange für den Algorithmus und setzen Sie sie mit den besuchten Stationen
        // Erstellen Sie eine Warteschlange für den BFS-Algorithmus und einen Satz von besuchten Stationen, um die bereits besuchten Stationen zu verfolgen.
        Queue<List<Station>> queue = new LinkedList<>();
        Set<Station> visited = new HashSet<>();

        // Erstellen Sie eine Liste, um den aktuellen Pfad zu speichern, und fügen Sie ihr einen Startbahnhof hinzu.
        List<Station> initialPath = new ArrayList<>();

        initialPath.add(start);
        queue.add(initialPath);

        while (!queue.isEmpty()) {
            List<Station> path = queue.remove();
            Station lastStation = path.get(path.size() - 1);
            if (lastStation.equals(end)) {
                return path;
            }
            visited.add(lastStation);
            for (Station neighbor : lastStation.getNeighbors()) {
                if (!visited.contains(neighbor)) {
                    List<Station> newPath = new ArrayList<>(path);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        return null;
    }
}