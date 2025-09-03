import java.util.*;
import java.io.*;

public class Day22 {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(new FileReader("./day22.in"));
        ArrayList<Long> arr = new ArrayList<Long>();
        while(in.hasNextLine()) arr.add(Long.parseLong(in.nextLine()));

        part1(arr);
        part2(arr);

        in.close();
    }

    public static long MIX(long secret, long mixer){
        return secret ^ mixer;
    }
    public static long PRUNE(long secret){
        return secret % 16777216;
    }
    public static long EVOLVE(long secret){
        secret = PRUNE(MIX(secret, secret*64));
        secret = PRUNE(MIX(secret, Math.floorDiv(secret,32)));
        secret = PRUNE(MIX(secret, secret*2048));
        return secret;
    }
   
    public static void part1(ArrayList<Long> arr) throws Exception {
        long tot = 0;
        for(long secret: arr){
            for(int i = 0; i < 2000; i++){
                secret = EVOLVE(secret);
            }
            tot += secret;
        }
        System.out.println(tot);
    }

    public static void part2(ArrayList<Long> arr) throws Exception {
        long max = 0;
        for(int a = -5; a <= 5; a++){
            for(int b = -5; b <= 5; b++){
                for(int c = -5; c <= 5; c++){
                    for(int d = -5; d <= 5; d++){
                        Queue<Integer> true_window = new LinkedList<Integer>(Arrays.asList(a,b,c,d));
                        long temp = func(arr, true_window);
                        if(temp > max) max = temp;
                        //System.out.println("max: " + max);
                        //System.out.println("temp: " + temp);
                    }
                }
            }
        }
        System.out.println(max);
    }

    public static long func(ArrayList<Long> arr, Queue<Integer> true_window){
        Queue<Integer> cur_window = new LinkedList<Integer>();
        
        int tot = 0;
        for(long secret: arr){
            cur_window = new LinkedList<Integer>();
            for(int i = 0; i < 2000; i++){
                long prev = secret % 10;
                secret = EVOLVE(secret);
                long cur = secret % 10;
                
                cur_window.add((int) (cur-prev));
                if(cur_window.size() > 4) cur_window.remove();
                if(true_window.equals(cur_window)) {
                    tot += cur;
                    break;
                }
            }
        }
        return tot;
    }
}
