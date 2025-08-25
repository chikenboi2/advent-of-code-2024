import java.util.*;
import java.io.*;

public class day2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 2/day2.txt"));
        // Part 1
        /*int ret = 0;
        int s = 1000;
        boolean works = true;
        for(int i = 0; i < s; i++){
            String line = br.readLine();

            String[] parts = line.trim().split(" ");
            List<Integer> integers = new ArrayList<>();
            for (String part : parts) integers.add(Integer.parseInt(part));
            //System.out.println(integers.toString());
            int test = 0;
            if(!isStrictlyDecreasing(integers) && !isStrictlyIncreasing(integers)){
                works = false;
            }
            else{
                for(int j = 1; j < integers.size(); j++){
                    test = integers.get(j) - integers.get(j-1);
                    if(Math.abs(test) > 3 || test == 0){
                        works = false;
                        break;
                    }
                }
            }
            

            if(works) ret++;
            works = true;

        }
        System.out.println(ret);*/

        // Part 2
        int ret = 0;
        int s = 1000;
        for(int i = 0; i < s; i++){
            String line = br.readLine();
            String[] parts = line.trim().split(" ");
            List<Integer> ints = new ArrayList<>();
            for (String part : parts) ints.add(Integer.parseInt(part));

            if(workings(ints)) ret++;
            else{
                boolean tempworks = false;
                for(int j = 0; j < ints.size(); j++){
                    List<Integer> tempints = new ArrayList<>(ints);
                    tempints.remove(j);
                    if(workings(tempints)){
                        tempworks = true;
                        break;
                    }
                }
                if(tempworks) ret++;               
            }
        }
        System.out.println(ret);
        br.close();
    }



    public static boolean workings(List<Integer> list){
        boolean works = true;
        int test = 0;
        if(!isStrictlyDecreasing(list) && !isStrictlyIncreasing(list)){
            works = false;
        }
        else{
            for(int j = 1; j < list.size(); j++){
                test = list.get(j) - list.get(j-1);
                if(Math.abs(test) > 3 || test == 0){
                    works = false;
                    break;
                }
            }
        }
        return works;
    }
    public static boolean isStrictlyIncreasing(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isStrictlyDecreasing(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
