import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Scanner;

public class kth_javap_commonsub {
    
    public static void main(String[] args){

        //Get input
        Scanner scanner = new Scanner(System.in);

        //Length of substring
        int n = Integer.parseInt(scanner.nextLine());

        String S = scanner.nextLine(); 
        scanner.close();

        //<substring, occurences>
        Map<String, Integer> mySubStrings = new HashMap<>(); // A dictionary

        for (int i = 0; i < S.length(); i++){
            // Found out later that EOF means End-Of-File :)
            if(S.charAt(i) == 'E' && S.charAt(i + 1) == 'O' && S.charAt(i + 2) == 'F'){
                break;
            }

            try{ //At the end of the string this will cause an error.
                String currentSubString = S.substring(i, i + n); //Add one to the amount
                if (mySubStrings.get(currentSubString) == null){ //I.e. it's the substrings first occurence.
                    mySubStrings.put(currentSubString, 1);
                }
                else{
                    mySubStrings.put(currentSubString, mySubStrings.get(currentSubString) + 1); //If it already exists we add 1 to the amount.
                }
            }
            catch (Exception e){ //If an error happens, we know that there are no substrings of length 3 left, so we can simply break the loop.
                break;
            }
            
        }

        String d = getMostCommonSubString(mySubStrings);
        System.out.println(d);

    }

    public static String getMostCommonSubString(Map<String, Integer> mySubStrings){
        Set<String> keys = mySubStrings.keySet();

        //Need initial values for comparisons.
        String substring = "";
        int substringOccurences = 0;

        for (String currentSubString: keys){
            int occurences = mySubStrings.get(currentSubString); //Gets the value of the substring, i.e the amount of times it was found.
            if (substringOccurences < occurences){
                substring = currentSubString;
                substringOccurences = occurences;
            }
            else if (substringOccurences == occurences){
                int comparison = substring.compareTo(currentSubString);
                if (comparison > 0){ // if substring is after the compared to string alphabetically we overwrite it.
                    substring = currentSubString;
                    substringOccurences = occurences;
                }
            }
        }

        return substring;
    }
    

}
