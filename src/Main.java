import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

//        int[] array = {1, 3, 5, 2, 3, 2};
//        int oldNum = 0;
//        for (int i:
//             array) {
//            System.out.println(i+oldNum);
//            oldNum = i;
//        }

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

        File dir = new File("Lines");
        File[] files = dir.listFiles();

        lineArrayList = new ArrayList<>();

        for (File file : files) {
            if(file.isFile()) {
                BufferedReader inputStream = null;
                String line;
                try {
                    inputStream = new BufferedReader(new FileReader(file, StandardCharsets.UTF_16));
                    Line myLine = new Line(file.getName().replace(".txt", ""));
//                    System.out.println(myLine.getName());
                    Station oldStation = null;
                    while ((line = inputStream.readLine()) != null) {
//                        System.out.println(line);
                        Station myStation = findStationByNameOrCreate(line);
                        if (oldStation != null)
                            myStation.addNeighbor(oldStation);
                        oldStation = myStation;
                        myLine.addStation(myStation);
//                        System.out.println(myStation.getName());
                    }
                    lineArrayList.add(myLine);
//                    System.out.println();
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

    }
}