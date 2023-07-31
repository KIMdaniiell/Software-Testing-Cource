package main.csvInputOutput;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeMap;

public class CSVReader {
    private final String PATH;
    private File file;

    public CSVReader(String path) {
        this.PATH = path;
        this.file = new File(path);
    }

    public LinkedList<Double> readFromFile() {
        LinkedList<Double> linkedList = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.contains(",")) {
                    break;
                }
                Double d1 = Double.valueOf(line.strip().split(",")[0]);
                linkedList.add(d1);
                if (line.strip().split(",").length>1) {
                    Double d2 = Double.valueOf(line.strip().split(",")[1]);
                    linkedList.add(d2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return linkedList;
    }
}
