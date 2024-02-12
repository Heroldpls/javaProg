import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;

public class kth_javap_pathfinder {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        String input = scanner.nextLine();

        String[] inputArray = input.split(" ");
        int M = Integer.parseInt(inputArray[0]);
        int N = Integer.parseInt(inputArray[1]);

        char[][] map = new char[M][N];
        
        for(int i = 0; i < M; i++){
            String row = scanner.nextLine();
            map[i] = row.toCharArray(); // setting row nr i to be an array of chars that was gotten from the input string.
        }
        scanner.close();
                    
                
        HashSet<Character> alreadyClearedPaths = new HashSet<>(); //A Set of all the chars that have functioning paths.
        kth_javap_pathfinder pathfinder = new kth_javap_pathfinder();

        /*
            Currently using the Deque as a stack to perform DFS (Depth-FirstSearch)
            But if I change from .push() to .add() further down I instead use it as a queue and perform BFS (Breadth-First Search)     
       */
        Deque<int[]> tilesToCheck = new ArrayDeque<>();

        for (int i = 0; i < N; i++){ //For every letter in the first row (i.e. for every start tile)
            char letter = map[0][i];
            if(!(alreadyClearedPaths.contains(letter)) && letter != '*'){ //Only check the path from this starting tile if we haven't already found a path with this letter.

                tilesToCheck.add(new int[]{0, i});
                while(!tilesToCheck.isEmpty()){
                    
                    int[] nextTile = tilesToCheck.pop();
                    if (pathfinder.checkTile(nextTile[0], nextTile[1], letter, map, tilesToCheck)){ // We've reached the other side.
                        alreadyClearedPaths.add(letter); 
                        System.out.print(letter);
                        break;
                    }
                }
                
                tilesToCheck.clear();
            }
        }

        if(alreadyClearedPaths.isEmpty()){
            System.out.println(0);
        }        
    }

    private void getNextTile(int currentRow, int currentColumn, char[][] map, Deque<int[]> tilesToCheck, char letter){
        if(currentRow < map.length && currentRow >= 0 && currentColumn < map[0].length && currentColumn >= 0 
        && map[currentRow][currentColumn] == letter){  // If the tile is within the map and it contains the correct letter.
            tilesToCheck.push(new int[]{currentRow, currentColumn});
            map[currentRow][currentColumn] = '*'; //Change the tile to contain * in order to not add it to the stack again.
        }
    }

    private boolean checkTile( int currentRow, int currentColumn, char letter, char[][] map, Deque<int[]> tilesToCheck){ 
        //we're going to check the tile so we don't want to be able to check it again later.

        if(currentRow == map.length - 1){ //If we're at the bottom of the map.
            return true;
        }
        //Gets the neighboring tiles that have the same letter.
        // Since they're placed on the stack what will be placed on top is the Down direction.
        getNextTile(currentRow - 1, currentColumn, map, tilesToCheck, letter); // Up
        getNextTile(currentRow, currentColumn - 1, map, tilesToCheck, letter); // Left
        getNextTile(currentRow, currentColumn + 1, map, tilesToCheck, letter); // Right
        getNextTile(currentRow + 1, currentColumn, map, tilesToCheck, letter); // Down
        
        return false; // This specific tile isn't at the bottom of the map.
    }
}
