package Records;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class VegetableRecordsCollection {
    public static void main(String[] args) {

        HashMap<String, ArrayList<VegetableRecord>> records = readData();
        for(String country: records.keySet()){
            
            ArrayList<VegetableRecord> vegetables = records.get(country);
            for(int i = 0; i < vegetables.size(); i++){
                System.out.print(vegetables.get(i));
            }
        }
    }

    private static ArrayList<VegetableRecord> filterRecords(ArrayList<VegetableRecord>records){
        return new ArrayList<>();
    };

    private static HashMap<String, ArrayList<VegetableRecord>> readData() { // Reads the input data
        HashMap<String, ArrayList<VegetableRecord>> records = new HashMap<>();
        
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        while(scanner.hasNext()){
            counter++;

            String line = scanner.nextLine();
            String[] vegetableData = line.split(" ");

            VegetableRecord newVegetable = new VegetableRecord(vegetableData[0], vegetableData[1], Integer.parseInt(vegetableData[2]), vegetableData[3]);

            ArrayList<VegetableRecord> countryVegetables = records.get(newVegetable.getCountry());

            if(countryVegetables == null){ // If the country hasn't been registered yet we regster the country by adding it as a key.
                records.put(vegetableData[1], new ArrayList<VegetableRecord>());
            } else{
                boolean vegetableTypeFound = false;
                for (int i = 0; i < countryVegetables.size(); i++){
                    // We want to remove the old one and add the new one.
                    if(countryVegetables.get(i).getType() == vegetableData[0] && countryVegetables.get(i).getSize() < newVegetable.getSize()){
                        countryVegetables.remove(i);
                        countryVegetables.add(newVegetable);
                        vegetableTypeFound = true;
                        break;
                    }
                }
                if(!vegetableTypeFound){ // We want to add the vegetable in the case that the country didn't have any of that vegetable already registered as well.
                    countryVegetables.add(newVegetable);
                }   
            }
            if(counter == 2){ //TEMP
                break;
            }
        }
        return records;
    }
}
