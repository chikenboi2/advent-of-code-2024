import java.util.*;
import java.io.*;

public class day19 {
    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 19/day19.in"));
        Set<String> arr = new HashSet<String>(Arrays.asList(in.nextLine().split(", ")));
        in.nextLine();
        //System.out.println(arr.toString());
        
        Map<String, Long> memo = new HashMap<>();
        int ret = 0;
        long tot = 0;

        while (in.hasNextLine()){
            String line = in.nextLine();
            long ways = DO(line, arr, memo);
            if (ways > 0){
                System.out.println(line + "ways "  + ways);
                tot += ways;
                ret++;
            } else {
                System.out.println(line + " NO");
            }
        }

        System.out.println(ret);
        System.out.println(tot);
        in.close();
    }

    public static Long DO(String line, Set<String> arr, Map<String, Long> memo){
        if (memo.containsKey(line)) return memo.get(line);
        if (line.isEmpty()) return 1L;

        long tot = 0;
        for (String pat : arr){
            if (line.startsWith(pat)){
                String newLine = line.substring(pat.length());
                tot += DO(newLine, arr, memo);
            }
        }

        memo.put(line, tot);
        return tot;
    }
}