import java.util.*;
import java.io.*;

public class day8 {
    public static char[][] track;
    public static int n;
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 8/day8.in"));
        n = 50; // 12, 50

        char[][] grid = new char[n][n];
        track = new char[n][n];
        for(int i = 0; i < n; i++){
            char[] temp = in.nextLine().toCharArray();
            for(int j = 0; j < n; j++){
                grid[i][j] = temp[j];
                track[i][j] = '.';
                if(grid[i][j] != '.') track[i][j] = grid[i][j];
            }
            System.out.println(Arrays.toString(temp));
        }
        
        char first = ' ';
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] != '.'){
                    first = grid[i][j];
                }
                for(int x = 0; x < n; x++){
                    for(int y = 0; y < n; y++){
                        if(grid[i][j] != '.' && grid[x][y] == first && i != x && j != y){
                            make(grid, i, j, x, y);
                        }
                    }
                }
            }
        }

        int ret = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(track[i][j] != '.') ret++;
            }
        }
        
        //for(int i = 0; i < n; i++) System.out.println(Arrays.toString(track[i]));
        System.out.println();
        for(int i = 0; i < n; i++) System.out.println(Arrays.toString(track[i]));
        System.out.println(ret);
        in.close();

    }

    public static void make(char[][] grid, int y1, int x1, int y2, int x2){
        int ydiff = y2 - y1; // 1
        int xdiff = x2 - x1; // -3
        //System.out.println(xdiff + " " + ydiff);// 2,5 -> 3,2
        int newy = y2 + ydiff;
        int newx = x2 + xdiff;

        while(newy >= 0 && newy < n && newx >= 0 && newx < n){
            track[newy][newx] = '@';
            newy += ydiff;
            newx += xdiff;
        }
        newy = y1 - ydiff;
        newx = x1 - xdiff;
        while(newy >= 0 && newy < n && newx >= 0 && newx < n){
            track[newy][newx] = '@';
            newy -= ydiff;
            newx -= xdiff;
        }
    }

}

