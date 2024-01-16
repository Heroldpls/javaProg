import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class kth_javap_permutations {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine(); 
        scanner.close();

        //Using a set since it doesn't allow duplicate items, making the "unique" criteria for the assignment easier.
        Set<String> permutations = new HashSet<>();

        /*Since the parameter called permutations in the method is an alias of our 
        permutations Set that is declared in main, the changes the method does will persist outside of it. */
        generatePermutations(permutations, "", S); 

        //Convert to an arraylist to make sorting it easier.
        ArrayList<String> permutationsList = new ArrayList<>(permutations);
        Collections.sort(permutationsList);

        for (String permutation : permutationsList){
            System.out.println(permutation);
        }
    }

    //constructed is the string that contains the already constructed part of the permutation, 
    //remainder is what letters are left to be used the permutation.
    private static void generatePermutations(Set<String> permutations, String constructed, String remainder){ // Recursive function.

        if(remainder.length() == 0) { // Base case, when this is reached we end the recursion, meaning this particaular permutation if complete.
            permutations.add(constructed);
        }
        else{
            for (int i = 0; i < remainder.length(); i++){
                String furtherConstructed = constructed + remainder.charAt(i); // We att the current letter we're on to this new Prefix

                //Below we basically remove the current letter from the remaining string (since i is not included in the first, nor second substring)
                String newRemainingString = remainder.substring(0, i) + remainder.substring(i + 1);
                generatePermutations(permutations, furtherConstructed, newRemainingString);
            }
        }
    }
}
