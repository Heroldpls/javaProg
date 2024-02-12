import java.util.ArrayList;
import java.util.Scanner;

public class kth_javap_basen {
    public static void main(String[] args){
        //OBS: I use the long datatype in multiple places to avoid problems with overflow.

        // Get input
        Scanner scanner = new Scanner(System.in);
        String basesInput = scanner.nextLine();
        String firstNumber = scanner.nextLine();
        String secondNumber = scanner.nextLine();
        scanner.close();


        String[] basesStr = basesInput.split(" ");
        int[] bases = new int[2];
        for (int i = 0; i < basesStr.length; i++){
            bases[i] = Integer.parseInt(basesStr[i]);
        }
        
        long product = convertBaseToTen(firstNumber, bases[0]) * convertBaseToTen(secondNumber, bases[0]); // bases[0] is the old base
        
        System.out.println(convertBaseFromTen(product, bases[1])); // bases[1] is the new base
    }

    private static long convertBaseToTen(String number, int oldBase){
        long numberBaseTen = 0;
        for(int i = 0; i < number.length(); i++){ //Have to check individual chars in the case the the base is >10 (If we find A,B and so on...)
            
            //Get digit in base 10 (second argument is the prior base)
            int digit = Integer.parseInt("" + number.charAt(i), oldBase); //"" + ... in order to convert the char to a String.

            numberBaseTen += digit * Math.pow(oldBase, number.length() - i - 1);
        }
        return numberBaseTen;
    }

    private static String convertBaseFromTen(long number , int newBase){
        String numberNewBase = "";

        ArrayList<Long> rests = new ArrayList<>();
        long numberNext = number;

        int i=0;
        while (numberNext != 0){ 
            rests.add(numberNext % newBase); //Get the rest of the digits, we will then add them to eachother, starting with the last rest.

            //We use the most resent rest.
            numberNext = (long) numberNext / newBase; // Converting to int floors the number.

            i++;
        }

        for(int j = rests.size() - 1; j >= 0; j--){ //Start at the end of the list.
            if(rests.get(j) > 9){ //10 -> A, meaning we add 55 to it and then convert it to a unicode (since A has the unicode 65 in Dec).
                char restNewBase =(char)(rests.get(j) + 55); //Convert it to its corresponding character.
                numberNewBase += restNewBase;
            }
            else{
                numberNewBase += rests.get(j);
            }
        }
        return numberNewBase;
    }
}
