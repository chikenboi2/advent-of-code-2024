import java.util.*;
import java.io.*;

public class day7 {

    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 7/day7.in"));

        int n = 850;
        Long ret = 0L;
        for(int i = 0; i < n; i++){
            String line = in.nextLine();
            Long pot = Long.parseLong(line.substring(0, line.indexOf(":")));
            int[] nums = Arrays.stream(line.substring(line.indexOf(":") + 2).split(" ")).mapToInt(Integer::parseInt).toArray();
            //System.out.println(pot);
            //System.out.println(Arrays.toString(nums));
            //System.out.println(nums[nums.length]);
            if(works(nums, pot, 1, nums[0])){
                //System.out.println("YAY");
                ret += pot;
            }
            //System.out.println();
        }
        System.out.println(ret);
        in.close();
    }

    public static boolean works(int[] arr, Long pot, int ind, long cur){
        if(cur == pot && ind == arr.length) return true;
        if(ind == arr.length) return false;
        int next = arr[ind];
        long concat = Long.parseLong(cur + "" + next);

        return works(arr, pot, ind + 1, cur + next) || works(arr, pot, ind + 1, cur * next) || works(arr, pot, ind + 1, concat);
    }
}

