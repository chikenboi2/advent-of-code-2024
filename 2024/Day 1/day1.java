import java.util.*;
import java.io.*;

public class day1 {
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 1/day1.txt"));
        // Part 1
        /*int s = 1000; 
        ArrayList<Integer> rlist = new ArrayList<Integer>();
        ArrayList<Integer> llist = new ArrayList<Integer>();
        for(int i = 0; i < s; i++){
            rlist.add(in.nextInt());
            llist.add(in.nextInt());
        }

        Collections.sort(rlist);
        Collections.sort(llist);

        int ret = 0;
        for(int i = 0; i < s; i++){
            ret += Math.abs(rlist.get(i) - llist.get(i));
        }
        System.out.println(ret);*/

        //Part 2
        int s = 1000;
        ArrayList<Integer> rlist = new ArrayList<Integer>();
        ArrayList<Integer> llist = new ArrayList<Integer>();
        for(int i = 0; i < s; i++){
            rlist.add(in.nextInt());
            llist.add(in.nextInt());
        }

        Map<Integer, Integer> oc = new HashMap<>();
        for (int number : llist) {
            oc.put(number, oc.getOrDefault(number, 0) + 1);
        }

        int ret = 0;
        int num = 0;
        for(int i = 0; i < s; i++){
            num = rlist.get(i);
            ret += num * oc.getOrDefault(num,0);
        }

        System.out.println(ret);


        in.close();
    }
}
