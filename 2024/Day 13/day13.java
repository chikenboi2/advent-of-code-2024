import java.util.*;
import java.io.*;

public class day13 {
    public static void main(String[] args) throws IOException{ 
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 13/day13.in"));
        int n = 320; //320
        long a1, b1, c1;
        long a2, b2, c2;
        String line;
        long det;
        long x, y;
        long ret = 0;
        for(int i = 0; i < n; i++){
            line = in.nextLine();
            a1 = Integer.parseInt(line.substring(12, 14));
            a2 = Integer.parseInt(line.substring(18));
            //System.out.println(a1 + " " + a2);
            line = in.nextLine();
            b1 = Integer.parseInt(line.substring(12, 14));
            b2 = Integer.parseInt(line.substring(18));
            //System.out.println(b1 + " " + b2);
            line = in.nextLine();
            c1 = Integer.parseInt(line.split("\\D+")[1]) + 10000000000000L;
            c2 = Integer.parseInt(line.split("\\D+")[2]) + 10000000000000L;
            //System.out.println(c1 + " " + c2);

            //System.out.println();
            in.nextLine();
            det = a1 * b2 - a2 * b1;
            if(det == 0) continue;

            x = c1 * b2 - c2 * b1;
            y = a1 * c2 - a2 * c1;

            if (x % det != 0 || y % det != 0) {
                continue;
            }

            ret += (x / det) * 3 + (y / det) * 1;
            
            
        }
        System.out.println(ret);
        in.close();
    }
}
