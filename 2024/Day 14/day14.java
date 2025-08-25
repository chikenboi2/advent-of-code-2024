import java.util.*;
import java.io.*;

public class day14 {
    public static boolean stop = false;
    public static void main(String[] args) throws IOException {
        int len = 7753;

        while(len < 8000){
            //System.out.println(len); // 28, 77, 131, 178, 234, 279, 337, 380, 440, 481, 543
            //System.out.println();
            System.out.println(len);
            DO(len);
            len++;
        }
    }

    public static void DO(int len) throws IOException{ 
        
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 14/day14.in"));
        
        int l = 101, w = 103; // 101, 103 // 11, 7

        //int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        int x, y, vx, vy;
        String line;
        //int len = 0;
        int[][] arr = new int[w][l];
        while(in.hasNextLine()){
            line = in.nextLine();

            x = Integer.parseInt(line.split(" ")[0].substring(2).split(",")[0]);
            y = Integer.parseInt(line.split(" ")[0].substring(2).split(",")[1]);
            vx = Integer.parseInt(line.split(" ")[1].substring(2).split(",")[0]);
            vy = Integer.parseInt(line.split(" ")[1].substring(2).split(",")[1]);
            
            x = (x + (vx * len)) % l;
            y = (y + (vy * len)) % w;
            if(x < 0) x += l;
            if(y < 0) y += w;

            /*if(x > l / 2 && y < w/2) q1++;
            else if(x < l / 2 && y < w/2) q2++;
            else if(x < l / 2 && y > w/2) q3++;
            else if(x > l / 2 && y > w/2) q4++;*/
            arr[y][x]++;
        }

        //System.out.println(q1 * q2 * q3 * q4);
        //if(arr[50][50] != 0 && arr[51][50] != 0 && arr[51][51] != 0 && arr[50][51] != 0){
            for(int i = 0; i < arr.length; i++){
                line = "";
                for(int j = 0; j < arr[0].length; j++){
                    if(arr[i][j] == 0) line += " ";
                    else line += "█";

                }
                System.out.println(line);
            }
            stop = true;
            Scanner myObj = new Scanner(System.in);
            myObj.nextLine();
            
            myObj.close();
        //}
        in.close(); 
    }
}
