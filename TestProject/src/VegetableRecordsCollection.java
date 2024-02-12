

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class VegetableRecordsCollection {
    public static void main(String[] args) {

        ArrayList<String> data = readData();
        HashMap<String, ArrayList<VegetableRecord>> records = extractVegetables(data);
        ArrayList<VegetableRecord> sortedRecords = sortRecords(records);

        //Print the records.
        for(int i = 0; i < sortedRecords.size(); i++){
            System.out.println(sortedRecords.get(i));
        }
    }

    private static ArrayList<VegetableRecord> sortRecords(HashMap<String, ArrayList<VegetableRecord>> records){ //This returns the records as an ArrayList that is sorted properly.
        ArrayList<VegetableRecord> sortedRecords =  new ArrayList<>();
        //Add all records to the ArrayList.
        for(String country: records.keySet()){
            sortedRecords.addAll(records.get(country));
        }
        Collections.sort(sortedRecords); // Uses the compareTo() method in the VegetableRecord class.
        return sortedRecords;
    };

    private static ArrayList<String> readData() { // Reads the input data
        //HashMap<String, ArrayList<VegetableRecord>> records = new HashMap<>();
        
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<String> lines = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            if(line.isEmpty()){
                break;
            }
            lines.add(line);
        }
        scanner.close();
        
        return lines;
    }

    private static HashMap<String, ArrayList<VegetableRecord>> extractVegetables(ArrayList<String> data){
        HashMap<String, ArrayList<VegetableRecord>> records = new HashMap<>();
        
        for(String line: data){
            String[] vegetableData = line.split(" ");

            VegetableRecord newVegetable;
            if(vegetableData.length == 4){ //If the country name consists of only one word.
                newVegetable = new VegetableRecord(vegetableData[0], vegetableData[1], Integer.parseInt(vegetableData[2]), vegetableData[3]);
            }
            else{ //If the length of vegetableData isn't 4 (i.e the country name consists of multiple words.)
                int countryWordCount = vegetableData.length - 3; // The country name consists of all but three of the array elements.
                String countryName = "";
                for (int i = 0; i < countryWordCount; i++){
                    countryName += vegetableData[1 + i]; // Index 1 is always the vegetable type.
                    
                    if(i != countryWordCount - 1){ //Add a space if we're not on the last loop.
                        countryName += " ";
                    }
                }

                // If we get the input: gurka Amerikas Förenade Stater 25 cm, the size will be on index 4 since countryWordCount = 3
                newVegetable = new VegetableRecord(vegetableData[0], countryName, Integer.parseInt(vegetableData[1 + countryWordCount]), vegetableData[2 + countryWordCount]);
            }

            ArrayList<VegetableRecord> countryVegetables = records.get(newVegetable.getCountry());

            if(countryVegetables == null){ // If the country hasn't been registered yet we regster the country by adding it as a key.
                records.put(newVegetable.getCountry(), new ArrayList<VegetableRecord>());
                countryVegetables = records.get(newVegetable.getCountry());
            }
            boolean vegetableTypeFound = false;
            for (int i = 0; i < countryVegetables.size(); i++){
                
                VegetableRecord currentVegetable = countryVegetables.get(i);
            
                // We want to remove the old one and add the new one.
                if (currentVegetable.getType().equals(newVegetable.getType())){
                    vegetableTypeFound = true;
                    if(currentVegetable.getSize() < newVegetable.getSize()){
                        countryVegetables.remove(i);
                        countryVegetables.add(newVegetable);
                        vegetableTypeFound = true;
                    }
                    break;
                }

            }
            if(!vegetableTypeFound){ // We want to add the vegetable in the case that the country didn't have any of that vegetable already registered as well.
                countryVegetables.add(newVegetable);
            }   
        }
        
        return records;

    }
}
