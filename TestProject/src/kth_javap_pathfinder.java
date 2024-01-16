import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class kth_javap_pathfinder {
    private int[] DOWN = new int[]{1, 0};
    private int[] UP = new int[]{-1, 0};
    private int[] RIGHT = new int[]{0, 1};
    private int[] LEFT = new int[]{0, -1};

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
        
        /*
        //Temp:
        map = new char[][]{
        {'A', 'A', 'C', 'A', 'C', 'D'}, 
        {'A', 'B', 'B', 'A', 'B', 'D'}, 
        {'A', 'B', 'A', 'A', 'A', 'D'}, 
        {'A', 'B', 'A', 'B', 'A', 'D'}, 
        {'A', 'F', 'A', 'B', 'A', 'D'}, 
        {'A', 'B', 'B', 'B', 'B', 'D'}};
        */
        

        boolean[][] alreadyCheckedTiles = new boolean[M][N]; //So that we don't check the same tile multiple times.
        HashSet<Character> alreadyClearedPaths = new HashSet<>(); //A Set of all the chars that have functioning paths.
        kth_javap_pathfinder pathfinder = new kth_javap_pathfinder();

        for (int i = 0; i < N; i++){ //For every letter in the first row (i.e. for every start tile)
            char letter = map[0][i];

            if(!(alreadyClearedPaths.contains(letter))){ //Only check the path from this starting tile if we haven't already found a path with this letter.

            /*Currently using it as a stack to perform DFS (Depth-FirstSearch)
            //But if I change from .push() to .add() further down I instead use it as a queue and perform BFS (Breadth-First Search)
             */
            Deque<int[]> tilesToCheck = new ArrayDeque<>();

            tilesToCheck.add(new int[]{0, i});
                while(!tilesToCheck.isEmpty()){
                    int[] nextTile = tilesToCheck.poll();
                    if (pathfinder.checkTile(alreadyCheckedTiles, nextTile[0], nextTile[1], letter, map, tilesToCheck)){
                        alreadyClearedPaths.add(letter); 
                        System.out.print(letter);
                        break;
                    }
                    alreadyCheckedTiles[nextTile[0]][nextTile[1]] = true;
                }
            }
        }

        if(alreadyClearedPaths.isEmpty()){
            System.out.println(0);
        }
        
    }

    // Checks if any of the tiles we're currently on is in the bottom row.
    private boolean foundBottom(int currentRow, int finalRowIndex){
        return currentRow == finalRowIndex;
    }


    private void getNextTiles(int currentRow, int currentColumn, boolean[][] alreadyCheckedTiles, char[][] map, Deque<int[]> tilesToCheck){
        
        int[][] directionsToCheck = new int[][]{}; //Empty to start with.
        if(currentRow == 0 && currentColumn == 0){ //Don't have to check either up or left.
            directionsToCheck = new int[][] {this.DOWN, this.RIGHT};
        }
        else if(currentRow == 0 && currentColumn == map[0].length - 1){ // Not up or right.
            directionsToCheck = new int[][]{this.DOWN, this.LEFT};
        }
        else if (currentRow == 0){
            directionsToCheck = new int[][]{this.DOWN, this.RIGHT, this.LEFT}; //We don't have to check the row above because there is none.
        }
        else if(currentColumn == 0){ // We don't have to check to the left.
            directionsToCheck = new int[][]{this.DOWN,  this.RIGHT, this.UP};
        }
        else if (currentColumn == map[0].length - 1){ //If we're all the way to the right there is no tile to the right.
            directionsToCheck = new int[][] {this.DOWN, this.LEFT ,this.UP};
        }
        else{ //Check all
            directionsToCheck = new int[][] {this.DOWN, this.RIGHT, this.LEFT, this.UP};
        }
        for (int i = 0; i < directionsToCheck.length; i++){            
            //if the correct letter is there and we haven't already checked that tile.
            
            boolean alreadyChecked = false;
            int neighborRowIndex = currentRow + directionsToCheck[i][0];
            int neighborColIndex = currentColumn + directionsToCheck[i][1];
            

            if(alreadyCheckedTiles[neighborRowIndex][neighborColIndex]){
                alreadyChecked = true;
            }

            //System.out.println(map[currentRow + indexMods[i][0]][currentColumn + indexMods[i][1]]);
            if (map[currentRow + directionsToCheck[i][0]][currentColumn + directionsToCheck[i][1]] == map[currentRow][currentColumn] && !alreadyChecked){
                tilesToCheck.push(new int[]{neighborRowIndex, neighborColIndex});
            }
        }
    }

    private boolean checkTile(boolean[][] alreadyCheckedTiles, int currentRow, int currentColumn, char letter, char[][] map, Deque<int[]> tilesToCheck){ 
        //we're going to check the tile so we don't want to be able to check it again later.

        if(foundBottom(currentRow, map.length - 1)){
            return true;
        }

        getNextTiles(currentRow, currentColumn, alreadyCheckedTiles, map, tilesToCheck); //Gets the neighboring tiles that have the same letter.

        return false; //If we get all the way to here without returning true, there isn't a path.
    }
}
