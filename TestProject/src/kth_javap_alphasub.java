import java.util.Scanner;

public class kth_javap_alphasub {
    /* 
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
            if(unicode >= previousCharUnicode){
                lengthCounter += 1;
            }

            else{ // Reset
                if (L < lengthCounter){
                    L = lengthCounter;
                }
                lengthCounter = 1;
            }
            previousCharUnicode = unicode;

            // Had to put this here aswell since I previously forgot to set 
            // this value if we were done looping through the string
            if (L < lengthCounter){
                L = lengthCounter;
            }
        }
        System.out.println(L);
    }
    */
}
