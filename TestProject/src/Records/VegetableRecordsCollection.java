package Records;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class VegetableRecordsCollection {
    public static void main(String[] args) {
        
        ArrayList<VegetableRecord> vegetableRecords = readData("vegetable_data.txt");

        /*
        // Sort the list with vegetable records.
        ArrayList<VegetableRecord> filteredRecords = filterRecords(vegetableRecords);

                // Print the results
        for (VegetableRecord record : vegetableRecords) {
                    System.out.println(record);
                }
        */
    }

    private static ArrayList<VegetableRecord> filterRecords(ArrayList<VegetableRecord>records){
        return new ArrayList<>();
    };

    private static ArrayList<VegetableRecord> readData(String fileName) { // Reads the data from the file.
        ArrayList<VegetableRecord> records = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()){
            String line = scanner.nextLine();
            System.out.println(line);
        }
        return records;
    }
}
