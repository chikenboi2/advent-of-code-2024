import java.util.*;
import java.io.*;

public class day5 {
    static public int ret = 0;
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 5/day5.in"));

        int n = 1176, m = 204; // length of inputs
        //int n = 21, m = 6;
        int[][] map = new int[n][2];
        for(int i = 0; i < n; i++){
            String line = in.readLine();
            map[i][0] = Integer.parseInt(line.substring(0, 2));
            map[i][1] = Integer.parseInt(line.substring(3));
        }
        in.readLine();
        /*for(int i = 0; i < m; i++){ // run through each line PART 1
            String[] temp = in.nextLine().split(",");
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < temp.length; j++) arr.add(Integer.parseInt(temp[j]));
            System.out.println(arr.toString());

            boolean works = true;
            for(int j = 0; j < n; j++){ // loop through map
                System.out.println(map[j][0]);
                System.out.println(map[j][1]);
                if(!isFirstAhead(arr, map[j][0], map[j][1])){
                    works = false;
                    break;
                }
            }
            if(works){
                ret += arr.get(arr.size() / 2);
            }
        }*/

        for(int i = 0; i < m; i++){ // run through each line PART 2
            String[] temp = in.readLine().split(",");
            ArrayList<Integer> arr = new ArrayList<>();
            for (int j = 0; j < temp.length; j++) arr.add(Integer.parseInt(temp[j]));
            //System.out.println(arr.toString());

            boolean works = true;
            for(int j = 0; j < n; j++){ // loop through map
                //System.out.println(map[j][0]);
                //System.out.println(map[j][1]);
                if(!isFirstAhead(arr, map[j][0], map[j][1])){
                    works = false;
                    break;
                }
            }

            if(!works){
                System.out.println(arr.toString());
                //permute(arr, 0, map);
                //int t = permute(arr, 0, map, ret);
                //System.out.println(t);
                //System.out.println(permute(arr, 0, map));
                //System.out.println("trying");

                while(!works){
                    boolean works2 = true;
                    for(int j = 0; j < n; j++){ // loop through map
                        if(!isFirstAhead(arr, map[j][0], map[j][1])){
                            Collections.swap(arr, arr.indexOf(map[j][0]), arr.indexOf(map[j][1]));
                            works2 = false;
                        }
                    }
                    if(works2) break;
                }
                System.out.println(arr.toString());
                ret += arr.get(arr.size()/2);
            }
        }

        System.out.println(ret);
        in.close();
    }

    
    public static boolean isFirstAhead(ArrayList<Integer> arr, int first, int second) {
        if(!arr.contains(first) || !arr.contains(second)) return true; // GTFO
        for(int num : arr){
            if(num == first) return true;
            if(num == second) return false;
        }
        System.out.println("COOKED");
        return true;
    }

    public static void permute(ArrayList<Integer> arr, int k, int[][] map){
        for(int i = k; i < arr.size(); i++){
            Collections.swap(arr, i, k);
            permute(arr, k+1, map);
            Collections.swap(arr, k, i);
        }
        if (k == arr.size() -1){
            boolean works2 = true;
            for(int j = 0; j < map.length; j++){ // loop through map
                //System.out.println(map[j][0]);
                //System.out.println(map[j][1]);
                if(!isFirstAhead(arr, map[j][0], map[j][1])){
                    works2 = false;
                    break;
                }
            }
            if(works2){
                //System.out.println(arr);
                //System.out.println(arr.get(arr.size() / 2));
                ret += arr.get(arr.size() / 2);
                //System.out.println(ret);
            }
        }
    }
}
