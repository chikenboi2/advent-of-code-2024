import java.io.*;
import java.util.*;

public class day18 {
    public static int n;
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 18/day18.in"));

        n = 2994; //2990, 2995
        int np = 71;
        char[][] grid = new char[np][np];
        for(int i = 0; i < np; i++) for(int j = 0; j < np; j++) grid[i][j] = '.';
        for(int i = 0; i < n; i++){
            String line = in.nextLine();
            grid[Integer.parseInt(line.split(",")[0])][Integer.parseInt(line.split(",")[1])] = '#';
        }
       
        for(int i = 0; i < np; i++) System.out.println(grid[i]);

        boolean[][] visited = new boolean[np][np];
        
        System.out.println(bfs(grid, visited, 0, 0));
        in.close();
    }


    public static int dx[] = {-1, 0, 1, 0};
    public static int dy[] = {0, 1, 0, -1};

    public static int bfs(char[][] grid, boolean[][] visited, int r, int c){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;
        int out = 0;
        int x = 0, y = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int k = 0; k < size; k++){
                int[] now = q.poll();
                x = now[0];
                y = now[1];

                //System.out.println(grid[x][y] + " ");
                
                if(x == grid.length - 1 && y == grid[0].length -1) return out;

                for(int i = 0; i < 4; i++){
                    int newx = x + dx[i];
                    int newy = y + dy[i];
                    if(newx >= 0 && newx < grid.length && newy >= 0 && newy < grid[0].length && !visited[newx][newy] && grid[newx][newy] == '.'){
                        q.offer(new int[]{newx, newy});
                        visited[newx][newy] = true;
                        
                    }
                }
            }
            out++;
        }
        
        return -1;
    }
}
