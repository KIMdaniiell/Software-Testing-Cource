package main.csvInputOutput;

import java.io.*;
import java.util.Scanner;

public class CSVWriter {
    private final String PATH;
    private Scanner scanner ;

    public CSVWriter(String path) {
        this.PATH = path;
    }

    public void writeToFile(String string) {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(PATH, true)));) {

            pw.println(string);
        } catch (FileNotFoundException e) {
            //
        } catch (IOException e) {
            //
        }
    }
}
