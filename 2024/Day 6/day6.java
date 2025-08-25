import java.util.*;
import java.io.*;

public class day6 {
    //public static char[][] count;
    public static int n;
    public static int ret;
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 6/day6.in"));

        n = 130;
        char[][] grid = new char[n][n];
        char[][] fix = new char[n][n];
        for(int i = 0; i < n; i++){
            char[] line = in.nextLine().toCharArray();
            //System.out.println(Arrays.toString(line));
            for(int j = 0; j < n; j++){
                grid[i][j] = line[j];
                fix[i][j] = line[j];
            }
        }
        
        
        //System.out.println(Arrays.deepToString(grid));
        /*moveUntilCant(grid, 0); 

        int ret = 0;
        
        
        for(int i = 0; i < n; i++){
            //System.out.println(Arrays.toString(count[i]));
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(count[i][j] == 'X'){
                    ret++;
                }
            }
        }
        System.out.println(ret);*/ // PART 1
        int num = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                ret = 0;
                grid[i][j] = '#';
                for(int k = 0; k < n; k++){
                    //System.out.println(Arrays.toString(grid[k]));
                    
                }
                //System.out.println();
                moveUntilCant(grid, 0);
                for(int a = 0; a < n; a++){
                    for(int b = 0; b < n; b++){
                        grid[a][b] = fix[a][b];
                    }
                }
                for(int k = 0; k < n; k++){
                    //System.out.println(Arrays.toString(grid[k]));  
                }
                //System.out.println("OUTSIDE");
                if(ret > 1000000){
                    num++;
                }
                //System.out.println();
            }
        }
        System.out.println(num);
        
        in.close();
    }

    public static void moveUntilCant(char[][] grid, int dir){
        int r = 0;
        int c = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == '^'){
                    r = i;
                    c = j;
                    break;
                }
            }
        }
        char[][] count = grid.clone();
        for(int k = 0; k < n; k++){
            //System.out.println(Arrays.toString(grid[k]));
            
        }
        //System.out.println();
        while(r > 0 && r < n && c > 0 && c < n){
            
            count[r][c] = 'X';
            if(dir == 0){ // NORTH
                if(r - 1 < 0){
                    break;
                }
                else if(grid[r - 1][c] == '#'){
                    dir++;
                }
                else{
                    r--;
                }

            }
            else if(dir == 1){ // EAST
                if(c + 1 > n-1){
                    break;
                }
                else if(grid[r][c + 1] == '#'){
                    dir++;
                }
                else{
                    c++;
                }
            }
            else if(dir == 2){ // SOUTH
                if(r + 1 > n-1){
                    break;
                }
                else if(grid[r + 1][c] == '#'){
                    dir++;
                }
                else{
                    r++;
                }
            }
            else if(dir == 3){ // WEST
                if(c - 1 < 0){
                    break;
                }
                else if(grid[r][c - 1] == '#'){
                    dir++;
                }
                else{
                    c--;
                }
            }
            dir %= 4;
            ret++;
            if(ret > 1000000){
                //System.out.println("BROkE");
                break;
            }
            //System.out.println(r + " " + c);
            for(int i = 0; i < n; i++){
                //System.out.println(Arrays.toString(grid[i]));
                
            }
            //System.out.println("INSIDE");
            /*if(isStuck(count, r, c, dir)){
                System.out.println("YIPEE");
                System.out.println(r + " " + c + " " + dir);
                for(int i = 0; i < n; i++){
                    System.out.println(Arrays.toString(count[i]));
                }
                break;
            }*/
            
        }
        //System.out.println(r + " " + c);
    }
    public static boolean isStuck(char[][] grid, int r, int c, int dir){
        if(grid[r][c] == 'X'){
            if(dir == 0) if(grid[r-1][c] == 'X') return true;
            else if (dir == 1) if(grid[r][c+1] == 'X') return true;
            else if (dir == 2) if(grid[r+1][c] == 'X') return true;
            else if (dir == 3) if(grid[r][c-1] == 'X') return true;
        }
        return false;
    }
}
