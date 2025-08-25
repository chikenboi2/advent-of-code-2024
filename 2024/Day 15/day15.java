import java.util.*;
import java.io.*;

public class day15 {
    public static char[][] grid;
    public static boolean[][] visited;
    public static char UP = '^', DOWN = 'v', LEFT = '<', RIGHT = '>';
    public static char WALL = '#', BOX = 'O', EMPTY = '.', ROBOT = '@';
    public static int r, c;
    public static int n;
    public static char BOXL = '[', BOXR = ']';
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 15/day15.in"));
        n = 7;
        grid = new char[n][n*2];
        for(int i = 0; i < n; i++){
            String line = in.nextLine();
            for(int j = 0; j < n * 2; j += 2){
                if(line.charAt(j/2) == WALL){
                    grid[i][j] = WALL;
                    grid[i][j + 1] = WALL;
                }
                else if (line.charAt(j/2) == EMPTY){
                    grid[i][j] = EMPTY;
                    grid[i][j + 1] = EMPTY;
                }
                else if (line.charAt(j/2) == ROBOT){
                    r = i;
                    c = j;
                    grid[i][j] = ROBOT;
                    grid[i][j + 1] = EMPTY;
                }
                else if (line.charAt(j/2) == BOX){
                    grid[i][j] = BOXL;
                    grid[i][j + 1] = BOXR;
                }
            }
        }
        for(int i = 0; i < n; i++) System.out.println(Arrays.toString(grid[i]));
        in.nextLine();
        String temp = "";
        while(in.hasNextLine()) temp += in.nextLine();
        char[] moves = temp.toCharArray();
        System.out.println(Arrays.toString(moves));

        for(char move : moves){
            MOVE(move);
            System.out.println("Move " + move);
            for(int i = 0; i < n; i++) System.out.println(grid[i]);
            System.out.println();
            
        }

        int ret = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == BOXL) ret += 100 * i + j;
            }
        }

        System.out.println(ret);
        in.close();
    }

    public static void MOVE(char dir){
        StringBuilder line = new StringBuilder();
        int ind;

        if(dir == UP){
            if(grid[r-1][c] == WALL) return;
            else if(grid[r-1][c] == EMPTY) {
                grid[r-1][c] = ROBOT;
                grid[r][c] = EMPTY;
            }
            /*else if(grid[r-1][c] == BOX){
                for(int i = r; i >= 0; i--){
                    if(grid[i][c] == WALL) break;
                    line.append(grid[i][c]);
                }
                if(!line.toString().contains("" + EMPTY)) return;
                ind = line.toString().indexOf("" + EMPTY);
                line.setCharAt(ind, BOX);
                line.deleteCharAt(1);
                for(int i = 1; i < line.length() + 1; i++) grid[r-i][c] = line.charAt(i - 1);
                grid[r][c] = EMPTY;
            }*/
            else if(grid[r-1][c] == BOXR || grid[r-1][c] == BOXL){
                pushUp(visited, r, c, grid[r][c]);
            }
            r--;
        }
        else if(dir == DOWN){
            if(grid[r+1][c] == WALL) return;
            else if(grid[r+1][c] == EMPTY) {
                grid[r+1][c] = ROBOT;
                grid[r][c] = EMPTY;
            }
            else if(grid[r+1][c] == BOX){
                for(int i = 0; i < n - r; i++){
                    if(grid[r+i][c] == WALL) break;
                    line.append(grid[r+i][c]);
                }
                if(!line.toString().contains("" + EMPTY)) return;
                ind = line.toString().indexOf("" + EMPTY);
                line.setCharAt(ind, BOX);
                line.deleteCharAt(1);
                for(int i = 1; i < line.length() + 1; i++) grid[r+i][c] = line.charAt(i - 1);
                grid[r][c] = EMPTY;
            }
            r++;
        }
        else if(dir == LEFT){
            if(grid[r][c-1] == WALL) return;
            else if(grid[r][c-1] == EMPTY) {
                grid[r][c-1] = ROBOT;
                grid[r][c] = EMPTY;
            }
            else if(grid[r][c-1] == BOXR){
                for(int i = c; i >= 0; i--){
                    if(grid[r][i] == WALL) break;
                    line.append(grid[r][i]);
                }
                if(!line.toString().contains("" + EMPTY)) return;
                ind = line.toString().indexOf("" + EMPTY);
                line.setCharAt(ind, BOXR);
                line.deleteCharAt(1);
                for(int i = 1; i < line.length() + 1; i++) {
                    grid[r][c-i] = line.charAt(i - 1);
                    if(line.charAt(i - 1) == BOXR) grid[r][c-i] = BOXL;
                    if(line.charAt(i - 1) == BOXL) grid[r][c-i] = BOXR;
                }
                grid[r][c] = EMPTY;
            }
            c--;
        }
        else if(dir == RIGHT){
            if(grid[r][c+1] == WALL) return;
            else if(grid[r][c+1] == EMPTY) {
                grid[r][c+1] = ROBOT;
                grid[r][c] = EMPTY;
            }
            else if(grid[r][c+1] == BOXL){
                for(int i = 0; i < n*2 - c; i++){
                    if(grid[r][c+i] == WALL) break;
                    line.append(grid[r][c + i]);
                }
                if(!line.toString().contains("" + EMPTY)) return;
                ind = line.toString().indexOf("" + EMPTY);
                line.setCharAt(ind, BOXL);
                line.deleteCharAt(1);
                for(int i = 1; i < line.length() + 1; i++) {
                    grid[r][c + i] = line.charAt(i - 1);
                    if(line.charAt(i - 1) == BOXR) grid[r][c+i] = BOXL;
                    if(line.charAt(i - 1) == BOXL) grid[r][c+i] = BOXR;
                }
                grid[r][c] = EMPTY;
            }
            c++;
        }
        else{
            System.out.println("holy cooked");
            System.exit(0);
        }
    }

    public static void pushUp(boolean[][] visited, int r, int c, char prev){
        if(r < 0 || c < 0 || r >= grid.length || c >= grid.length) return;
        if(visited[r][c]) return;
        visited[r][c] = true;
        char now = grid[r][c];
        if(prev == now) return;

        /*
        for(int i = 0; i < n - r; i++){
            if(grid[r+i][c] == WALL) break;
            line.append(grid[r+i][c]);
        }
        if(!line.toString().contains("" + EMPTY)) return;
        ind = line.toString().indexOf("" + EMPTY);
        line.setCharAt(ind, BOX);
        line.deleteCharAt(1);
        for(int i = 1; i < line.length() + 1; i++) grid[r+i][c] = line.charAt(i - 1);
        grid[r][c] = EMPTY;
        */
    }
}
