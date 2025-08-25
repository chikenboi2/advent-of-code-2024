import java.util.*;

public class day17 {
    public static long tempa = 20000000000000L; // 20016560000000L
    public static int[] prog = new int[]{2,4,1,7,7,5,0,3,1,7,4,1,5,5,3,0};
    public static ArrayList<Long> ret = new ArrayList<>();
    public static void main(String[] args) {
        while(tempa < Long.MAX_VALUE){
            boolean works = true;
            DO();
            if(ret.size() == prog.length){
                for(int i = 0; i < prog.length; i++){
                    if(prog[i] != ret.get(i)){
                        works = false;
                        break;
                    }

                }
            }
            else{
                works = false;
            }
            if(works){
                System.out.println(tempa);
                System.out.println(ret.toString());
                break;
            }
            tempa++;
            //System.out.println(tempa);
            if(tempa % 10000000 == 0) System.out.println(tempa);
        }
    }

    public static void DO(){
        long numa = tempa;
        long numb = 0;
        long numc = 0;
        ret = new ArrayList<>();
        long i = 0;
        while(i < prog.length - 1){
            long opcode = prog[(int)i];
            long literal = prog[(int) i+1];
            long combo = literal;

            if(literal == 4) combo = numa;
            else if(literal == 5) combo = numb;
            else if(literal == 6) combo = numc;

            if(opcode == 0){
                numa = (long) (numa / Math.pow(2,combo));
            }
            else if(opcode == 1){
                numb = numb ^ literal;
            }
            else if(opcode == 2){
                numb = combo % 8;
            }
            else if(opcode == 3){
                if(numa != 0){
                    i = literal;
                    continue;
                }
            }
            else if(opcode == 4){
                numb = numb ^ numc;
            }
            else if(opcode == 5){
                ret.add(combo % 8);
                if(ret.size() > prog.length) return;
                if(ret.size() > 0)if(ret.get(ret.size() - 1) != prog[ret.size() -1]) return;
            }
            else if(opcode == 6){
                numb = (long) (numa / Math.pow(2,combo));
            }
            else if(opcode == 7){
                numc = (long) (numa / Math.pow(2,combo));
            }
            i += 2;
        }
    }
}
