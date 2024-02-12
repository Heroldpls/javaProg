import java.util.Scanner;

public class kth_javap_alphasub {
    
    public static void main(String[] args){
        int L = 1; //Since technically a single letter is always sorted, we can start it at one.
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine(); //Get input
        scanner.close();

        // Just an int that is lower than the lowest unicode to start of with.
        int previousCharUnicode = -1;

        int lengthCounter = 0;
        for (int i = 0; i < S.length(); i++){
    
            int unicode = Character.codePointAt(new char[]{S.charAt(i)}, 0);
            if(S.charAt(i) == 'E' && S.charAt(i + 1) == 'O' && S.charAt(i + 2) == 'F'){ // If we find "EOF"
                if (L < lengthCounter){ 
                    L = lengthCounter;
                }
                break;
            }
            if(unicode >= previousCharUnicode){ // The current letter comes after the last one in unicode, i.e., they're ordered.
                lengthCounter += 1;
            }

            else{ // Reset if the the current letter is not ordered if compared to the previous one.
                if (L < lengthCounter){ //If our current substring is longer than the last one we overwrite L, otherwise we simply reset.
                    L = lengthCounter;
                }
                lengthCounter = 1; // Starts at one since we consider the current letter to be the first in the upcoming string.
            }
            previousCharUnicode = unicode; //Store this letter for the next loop.

            if (L < lengthCounter){ // If we've reached a longer length than what we've reached before.
                L = lengthCounter;
            }
        }
        System.out.println(L);
    }
    
}
