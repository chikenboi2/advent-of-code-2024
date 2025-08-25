import java.util.*;
import java.io.*;

public class day12 {
    public static void main(String[] args) throws IOException{ 
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 12/day12.in"));

        int n = 140;
        char[][] grid = new char[n][n];
        boolean[][] visited = new boolean[n][n];
        for(int i = 0; i < n; i++){
            String temp = in.nextLine();
            for(int j = 0; j < n; j++){
                grid[i][j] = temp.charAt(j);
            }
            //System.out.println(temp);
        }
        

        boolean[][] gridv = new boolean[n][n];
        int ret = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!gridv[i][j]){
                    int temp1 = area(grid, visited, i,j, grid[i][j]);
                    //System.out.println(temp1);
                    //for(int x = 0; x < n; x++) System.out.println(Arrays.toString(visited[x]));
                    visited = new boolean[n][n];
                    int temp2 = perim(grid, visited, i, j, grid[i][j]); // PART 1
                    //System.out.println(temp2);
                    for(int x = 0; x < n; x++){
                            for(int y = 0; y < n; y++){
                            if(visited[x][y]) gridv[x][y] = true;
                        }
                    }
                    temp2 = sides(visited); // PART 2
                    //for(int x = 0; x < n; x++) System.out.println(Arrays.toString(visited[x]));

                    visited = new boolean[n][n];
                    ret += temp1 * temp2;
                }
            }
        }//F is 11 should be 12, M is 5 should be 6
        System.out.println(ret);

        in.close();
    }


    public static int area(char[][] grid, boolean[][] visited, int r, int c, char let) {
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return 0;
        if(visited[r][c]) return 0;
        visited[r][c] = true;
        if(grid[r][c] != let) return 0;
         
        
        return 1 + area(grid, visited, r+1, c, let) + area(grid, visited, r-1, c, let) + area(grid, visited, r, c+1, let) + area(grid, visited, r, c-1, let);
    }

    public static int perim(char[][] grid, boolean[][] visited, int r, int c, char let) {
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return 1;
        if(grid[r][c] != let) return 1;
        if(visited[r][c]) return 0;
        visited[r][c] = true;
         
        //for(int i = 0; i < visited.length; i++) System.out.println(Arrays.toString(visited[i]));
        //System.out.println();
        return perim(grid, visited, r+1, c, let) + perim(grid, visited, r-1, c, let) + perim(grid, visited, r, c+1, let) + perim(grid, visited, r, c-1, let);
    }

    public static int sides(boolean[][] grid){
        // 
        // slice horizontally, if nothing 1 above//next then put X into string.
        // count number of groups with split string
        // do same in other 3 directions
        String line;
        boolean abo;
        int ret = 0;
        for(int r = 0; r < grid.length; r++){
            line = "";
            for(int c = 0; c < grid[0].length; c++){
                abo = false;
                if(r > 0){
                    abo = grid[r-1][c];
                }
                line += !abo && grid[r][c] ? "X" : "O";
            }
            //System.out.println(line);
            for (String group : line.split("O+")) {
                if (group.contains("X")) {
                    ret++;
                }
            }
        }
       // System.out.println();
        for(int r = grid.length-1; r >= 0; r--){
            line = "";
            for(int c = 0; c < grid[0].length; c++){
                abo = false;
                if(r < grid.length-1){
                    abo = grid[r+1][c];
                }
                line += !abo && grid[r][c] ? "X" : "O";
            }
            //System.out.println(line);
            for (String group : line.split("O+")) {
                if (group.contains("X")) {
                    ret++;
                }
            }
        }
        //System.out.println();

        for(int c = 0; c < grid[0].length; c++){
            line = "";
            for(int r = 0; r < grid.length; r++){
                abo = false;
                if(c > 0){
                    abo = grid[r][c - 1];
                }
                line += !abo && grid[r][c] ? "X" : "O";
            }
            //System.out.println(line);
            for (String group : line.split("O+")) {
                if (group.contains("X")) {
                    ret++;
                }
            }
        }
        //System.out.println();
        for(int c = grid.length-1; c >= 0; c--){
            line = "";
            for(int r = 0; r < grid[0].length; r++){
                abo = false;
                if(c < grid.length-1){
                    abo = grid[r][c+1];
                }
                line += !abo && grid[r][c] ? "X" : "O";
            }
            //System.out.println(line);
            for (String group : line.split("O+")) {
                if (group.contains("X")) {
                    ret++;
                }
            }
        }
        return ret;
    }
}
