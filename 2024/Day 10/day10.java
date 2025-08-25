import java.util.*;
import java.io.*;

public class day10 {
    public static int ret;
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 10/day10.in"));

        int n = 45; 

        int[][] grid = new int[n][n];
        for(int i = 0; i < n; i++){
            char[] temp = in.nextLine().toCharArray();
            for(int j = 0; j < n; j++){
                grid[i][j] = temp[j] - '0';
            }
            //System.out.println(Arrays.toString(grid[i]));
        }

        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int ret = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    int temp = floodFill(grid, visited, i, j, -1);
                    //System.out.print(temp + ", ");
                    ret += temp;
                    visited = new boolean[rows][cols];      
                }
            }
        }
        System.out.println("\n" + ret);
        in.close();
    }

    public static int floodFill(int[][] grid, boolean[][] visited, int r, int c, int prev) {
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return 0;
        if(visited[r][c]) return 0;
        if(grid[r][c] != prev + 1) return 0;
         
    
        if(grid[r][c] == prev + 1) {
            //visited[r][c] = true; // part 1 keep, part 2 remove this
            prev++;
            if(grid[r][c] == 9) return 1;
        }
        
        return floodFill(grid, visited, r+1, c, prev) + floodFill(grid, visited, r-1, c, prev) + floodFill(grid, visited, r, c+1, prev) + floodFill(grid, visited, r, c-1, prev);
    }
}
