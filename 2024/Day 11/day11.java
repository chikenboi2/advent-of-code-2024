import java.util.*;
import java.io.*;

public class day11 {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 11/day11.in"));
        Map<Long, Long> map = new HashMap<>();

        while (in.hasNextInt()) {
            long temp = in.nextInt();
            map.put(temp, map.getOrDefault(temp, 0L) + 1);
        }

        for (int i = 0; i < 75; i++) { // PART 1 was 25, PART 2 is 75
            Map<Long, Long> next = new HashMap<>();

            for (Map.Entry<Long, Long> entry : map.entrySet()) {
                long stone = entry.getKey();
                long count = entry.getValue();

                if (stone == 0) {
                    next.put(1L, next.getOrDefault(1, 0L) + count);
                } 
                else if (String.valueOf(stone).length() % 2 == 0) {
                    int mid = String.valueOf(stone).length() / 2;
                    long l = Long.parseLong(String.valueOf(stone).substring(0, mid));
                    long r = Long.parseLong(String.valueOf(stone).substring(mid));

                    next.put(l, next.getOrDefault(l, 0L) + count);
                    next.put(r, next.getOrDefault(r, 0L) + count);
                } 
                else {
                    next.put(stone * 2024, next.getOrDefault(stone * 2024, 0L) + count);
                }
            }
            map = next;
        }

        long ret = 0;
        for (long i : map.values()) ret += i;
        System.out.println(ret);

        in.close();
    }
}
