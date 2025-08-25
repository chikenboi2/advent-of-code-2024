import java.util.*;
import java.io.*;

public class day20 {
    public static int startR = 0, startC = 0, endR, endC, permstR, permstC, permenR, permenC;
    public static char[][] perm;
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 20/day20.in"));
        int n = 15; //141
        char[][] grid = new char[n][n];
        perm = new char[n][n];
        // 285
        for(int i = 0; i < n; i++){
            String temp = in.nextLine();
            for(int j = 0; j < n; j++){
                grid[i][j] = temp.charAt(j);
                if(temp.charAt(j) == 'S'){
                    startR = i;
                    permstR = i;
                    startC = j;
                    permstC = j;
                }
                else if(temp.charAt(j) == 'E'){
                    endR = i;
                    permenR = i;
                    endC = j;
                    permenC = j;
                }
            }
            System.out.println(temp);
        }
        for(int q = 0; q < n; q++) for(int p = 0; p < n; p++) perm[q][p] = grid[q][p];

        int max = bfs(grid);
        int ret = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 1; i < n-1; i++){
            for(int j = 1; j < n-1; j++){
                for(int x = i; x < n-1; x++){
                    for(int y = j; y < n-1; y++){
                        if(grid[i][j] == '.' && grid[x][y] == '.' && y-j + x-i <= 20){
                            startR = permstR;
                            startC = permstC;
                            endR = i;
                            endC = j;
                            int begtopath = bfs(grid);

                            int path = -1;
                            for(int r = Math.min(i, x); r < Math.max(i, x); r++) grid[r][Math.min(j, y)] = '.'; path++;
                            for(int c = Math.min(j, y); c < Math.max(j, y); c++) grid[Math.max(i, x)][c] = '.'; path++;
                            
                            startR = x;
                            startC = y;
                            endR = permenR;
                            endC = permenC;
                            int pathtoend = bfs(grid);
                            
                            System.out.println();
                            System.out.println("i,j: " + i + "," + j + "  x,y: " + x + "," + y);
                            for(char[] t : grid) System.out.println(t);
                            
                            int temp = max - (begtopath + path + pathtoend);
                            System.out.println(temp);
                            map.put(temp, map.getOrDefault(temp, 0) + 1);
                            if(temp >= 50) ret++;
                            for(int q = 0; q < n; q++) for(int p = 0; p < n; p++) grid[q][p] = perm[q][p];
                        }
                    }
                }
            }
        }
        Map<Integer, Integer> sortedMap = new TreeMap<>(map);

        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println(ret);

        in.close();
    }

    public static int[] dr = {0, 0, 1, -1};
    public static int[] dc = {1, -1, 0, 0};
    public static int bfs(char[][] grid){
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.add(new int[]{startR, startC, 0});

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        visited[startR][startC] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], d = cur[2];

            if(r == endR && c == endC) return d;
            
            for(int i = 0; i < 4; i++){
                int newr = r + dr[i];
                int newc = c + dc[i];

                if(newr >= 0 && newr < grid.length && newc >= 0 && newc < grid[0].length && grid[newr][newc] != '#' && !visited[newr][newc]){
                    queue.add(new int[]{newr, newc, d + 1});
                    visited[newr][newc] = true;
                }
            }
        }

        return -1;
    }
    
}