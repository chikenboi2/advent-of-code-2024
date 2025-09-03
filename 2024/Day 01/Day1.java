import java.util.*;
import java.io.*;

public class Day1 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> rlist = new ArrayList<Integer>();
        ArrayList<Integer> llist = new ArrayList<Integer>();
        inputLists(rlist, llist);
        part1(rlist, llist);
        part2(rlist, llist);
    }

    public static void part1(ArrayList<Integer> rlist, ArrayList<Integer> llist) {
        Collections.sort(rlist);
        Collections.sort(llist);

        int ret = 0;
        for(int i = 0; i < rlist.size(); i++){
            ret += Math.abs(rlist.get(i) - llist.get(i));
        }
        System.out.println(ret);
    }

    public static void part2(ArrayList<Integer> rlist, ArrayList<Integer> llist) {
        Map<Integer, Integer> oc = new HashMap<>();
        for (int number : llist) {
            oc.put(number, oc.getOrDefault(number, 0) + 1);
        }

        int ret = 0;
        int num = 0;
        for(int i = 0; i < rlist.size(); i++){
            num = rlist.get(i);
            ret += num * oc.getOrDefault(num,0);
        }

        System.out.println(ret);
    }

    public static void inputLists(ArrayList<Integer> rlist, ArrayList<Integer> llist) throws Exception {
        Scanner in = new Scanner(new FileReader("./day1.txt"));
        int s = 1000;
        for(int i = 0; i < s; i++){
            rlist.add(in.nextInt());
            llist.add(in.nextInt());
        }
        in.close();
    }
}
