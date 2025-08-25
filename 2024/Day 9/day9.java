import java.util.*;
import java.io.*;

public class day9 {
    public static ArrayList<String> bet;
    public static void main(String[] args) throws Exception{
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 9/day9.in"));
        char[] line = in.nextLine().toCharArray();
        bet = new ArrayList<>();

        int id = 0;
        boolean space = false;
        long shouldbethislong = 0;
        for(int i = 0; i < line.length; i++){
            int len = line[i] - '0';
            if(!space){
                for(int j = 0; j < len; j++){
                    bet.add(Integer.toString(id));
                    shouldbethislong++;
                }
                id++;
            }
            else{
                for(int j = 0; j < len; j++){
                    bet.add(".");
                }
            }
            space = !space;
        }
        System.out.println(bet.toString());
        System.out.println(shouldbethislong);
        /*while(bet.indexOf(".") != shouldbethislong){ // PART 1
            //System.out.println(bet.toString());
            Collections.swap(bet, bet.indexOf("."), LASTLETTER(bet));
        }
        
        long ret = 0;
        for(int i = 0; i < bet.size(); i++){
            if(bet.get(i).equals(".")) break;
            else{
                //System.out.println(i + " * " + bet.get(i) + " " + ((bet.get(i) - '0') * i));
                ret += Integer.parseInt((bet.get(i))) * i;
            }
        }
        System.out.println(bet.toString());
        
        System.out.println(ret);*/

        for(int i = Integer.parseInt(bet.get(bet.size() - 1)); i >= 0; i--){
            SWAP(bet, i);
        }
        
        long ret = 0;
        for(int i = 0; i < bet.size(); i++){
            if(!bet.get(i).equals(".")) ret += Integer.parseInt((bet.get(i))) * i;;
            //System.out.println(i + " * " + bet.get(i) + " " + ((bet.get(i) - '0') * i));
        }
        System.out.println(bet.toString());
        System.out.println(ret);
        in.close();
    }

    public static int LASTLETTER(ArrayList<String> line){
        for(int i = line.size() - 1; i >= 0; i--){
            if(!line.get(i).equals(".")) return i;
        }
        System.out.println("LASTLETTER COOKED");
        return -1;
    }
    
    public static int[] INDEXOFBIGENOUGHDOTGROUP(ArrayList<String> line, int enough){
        int temp = 0, tempi = 0;
        boolean inside = false;
        for(int i = 0; i < line.size(); i++){
            if(line.get(i) == "."){
                if(!inside) {
                    tempi = i;
                    inside = true;
                }
                temp++;
            }
            else{
                if(inside){
                    inside = false;
                }
                if(temp >= enough){
                    break;
                }
                temp = 0;
            }
        }
        int[] arr = {temp, tempi};
        return arr;
    }

    public static int LETTERGROUPSIZE(ArrayList<String> line, int id){
        int ret = 0;
        for(int i = line.size() - 1; i >= 0; i--){
            if(line.get(i).equals(id + "")) ret++;
        }
        return ret;
    }

    public static void SWAP(ArrayList<String> line, int id){
        
        int siz = LETTERGROUPSIZE(line, id);
        int maxi = INDEXOFBIGENOUGHDOTGROUP(line, siz)[1];
        int max = INDEXOFBIGENOUGHDOTGROUP(line, siz)[0];
        //System.out.println(" maxi " + maxi + "  siz " + siz + "  id " + id);
        if(max != 0 && line.indexOf(id + "") > maxi){
            for(int i = maxi; i < maxi + siz; i++){
                Collections.swap(bet, i, line.lastIndexOf(id + ""));
            }
        }
        //System.out.println(bet.toString());
    }
}

