import java.util.*;
import java.io.*;

public class Day4 {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader(new File("./day4.txt")));

        int s = 140;
        char[][] grid = new char[s][s];
        for(int i = 0; i < s; i++){
            char[] temp = in.nextLine().toCharArray();
            for(int j = 0; j < s; j++){
                grid[i][j] = temp[j];
            } 
        }
        
        System.out.println(countXmasOccurrences(grid));
        System.out.println(countMasXOccurrences(grid));
        in.close();
    }

    public static int countXmasOccurrences(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        
        // Directions for 8 possible movements (right, left, down, up, and 4 diagonals)
        int[][] directions = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}, // Horizontal and vertical
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1} // Diagonal
        };

        // Iterate through every cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // If the starting letter is 'X', begin search
                if (grid[r][c] == 'X') {
                    for (int[] dir : directions) {
                        if (checkWord(grid, r, c, dir, "XMAS")) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }

    private static boolean checkWord(char[][] grid, int r, int c, int[] dir, String word) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        for (int i = 0; i < word.length(); i++) {
            int newRow = r + i * dir[0];
            int newCol = c + i * dir[1];

            // Check boundaries
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                return false;
            }

            // Check if the character matches
            if (grid[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }
    
    public static int countMasXOccurrences(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        // Iterate through every cell in the grid
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                // Check all possible MAS-X patterns
                if ((grid[r][c] == 'A' &&
                     grid[r - 1][c - 1] == 'M' && grid[r - 1][c + 1] == 'S' &&
                     grid[r + 1][c - 1] == 'M' && grid[r + 1][c + 1] == 'S') ||
                    (grid[r][c] == 'A' &&
                     grid[r - 1][c - 1] == 'S' && grid[r - 1][c + 1] == 'S' &&
                     grid[r + 1][c - 1] == 'M' && grid[r + 1][c + 1] == 'M') ||
                    (grid[r][c] == 'A' &&
                     grid[r - 1][c - 1] == 'M' && grid[r - 1][c + 1] == 'M' &&
                     grid[r + 1][c - 1] == 'S' && grid[r + 1][c + 1] == 'S') ||
                    (grid[r][c] == 'A' &&
                     grid[r - 1][c - 1] == 'S' && grid[r - 1][c + 1] == 'M' &&
                     grid[r + 1][c - 1] == 'S' && grid[r + 1][c + 1] == 'M')) {
                    count++;
                }
            }
        }

        return count;
    }

}
