
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ArrayList;

public class VegetableRecordsCollection {
    public static void main(String[] args) {
        
        ArrayList<VegetableRecord> vegetableRecords = readVegetableRecordsFromFile("vegetable_data.txt");

        // Sort the list with vegetable records.
        ArrayList<VegetableRecord> filteredRecords = filterRecords(vegetableRecords);

                // Skriv ut resultaten
        for (VegetableRecord record : vegetableRecords) {
                    System.out.println(record);
        }
            
    }

    private static ArrayList<VegetableRecord> filterRecords(ArrayList<VegetableRecord>records){
        return new ArrayList<>();
    };

    private static ArrayList<VegetableRecord> readVegetableRecordsFromFile(String fileName) { // Reads the data from the file.
        
        ArrayList<VegetableRecord> records = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            System.out.println("Reading");
            String currentLine;
            while ((currentLine = reader.readLine()) != null) { // We continue reading until there's nothing left to read.
                // The data in the file is formated as: type, country, size, unit.
                
                String[] parts = currentLine.split(" ");
                if (parts.length == 4) { // I.e. if all of the information for the vegetable is present, if for example unit is missing length isn't 4
                System.out.println(parts[0]);
                    String vegetableType = parts[0];
                    String country = parts[1];
                    int recordSize = Integer.parseInt(parts[2]);
                    String unit = parts[3];

                    // Att this new Vegetable to the list of records.
                    records.add(new VegetableRecord(vegetableType, country, recordSize, unit));
                }
            }
        } catch (Exception e) { // If an error occurs we simply don't add the vegetable.
            //e.printStackTrace();
            System.out.println("Error");
        }

        return records;
    }
}
