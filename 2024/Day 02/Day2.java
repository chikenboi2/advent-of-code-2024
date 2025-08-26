import java.util.*;
import java.io.*;

public class Day2 {
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Integer>> lines = new ArrayList();
        getInput(lines);
        part1(lines);
        part2(lines);
    }

    public static void part1(ArrayList<ArrayList<Integer>> lines) {
        int ret = 0;
        for(int i = 0; i < lines.size(); i++){
            ArrayList<Integer> integers = lines.get(i);
            // System.out.println(integers.toString());
            if(isStrictlyDecreasingWithinLimits(integers) || isStrictlyIncreasingWithinLimits(integers)) {
                ret++;
            }
        }
        System.out.println(ret);
    }

    public static void part2(ArrayList<ArrayList<Integer>> lines) {
        int ret = 0;
        for(int i = 0; i < lines.size(); i++){
            List<Integer> ints = lines.get(i);
            
            if (isStrictlyDecreasingWithinLimits(ints) || isStrictlyIncreasingWithinLimits(ints)) ret++;
            else{
                for (int j = 0; j < ints.size(); j++){
                    List<Integer> tempints = new ArrayList<>(ints);
                    tempints.remove(j);
                    if(isStrictlyDecreasingWithinLimits(tempints) || isStrictlyIncreasingWithinLimits(tempints)) {
                        ret++;
                        break;
                    }
                }
            }
        }
        System.out.println(ret);
    }

    public static void getInput(ArrayList<ArrayList<Integer>> arr) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("./day2.txt"));
        int s = 1000;
        for(int i = 0; i < s; i++) {
            String line = br.readLine();
            String[] parts = line.trim().split(" ");
            ArrayList<Integer> ints = new ArrayList<>();
            for (String part : parts) ints.add(Integer.parseInt(part));
            arr.add(ints);
        }
        br.close();
    }

    public static boolean isStrictlyIncreasingWithinLimits(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) <= list.get(i - 1)) {
                return false;
            }
            int test = list.get(i) - list.get(i-1);
            if (Math.abs(test) > 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean isStrictlyDecreasingWithinLimits(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) >= list.get(i - 1)) {
                return false;
            }
            int test = list.get(i) - list.get(i-1);
            if (Math.abs(test) > 3) {
                return false;
            }
        }
        return true;
    }
}
