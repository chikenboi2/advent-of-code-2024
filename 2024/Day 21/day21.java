import java.util.*;
import java.io.*;

public class day21 {
    public static final char[][] numpad = {
        {'7', '8', '9'}, 
        {'4', '5', '6'},
        {'1', '2', '3'},
        {'N', '0', 'A'}
    };
    public static final char[][] dirpad = {
        {'N', '^', 'A'},
        {'<', 'v', '>'}
    };
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("/Users/samueld/Documents/Code/aoc/2024/Day 21/day21.in"));
        long ret = 0;
        while(in.hasNext()){
            String line = in.nextLine();
            System.out.println(line);
            String numpy = numpress(line);
            System.out.println(numpy);
            String keypy1 = keypress(numpy);
            System.out.println(keypy1);
            String keypy2 = keypress(keypy1);
            System.out.println(keypy2);
            System.out.println(keypy2.length());
            System.out.println(Integer.parseInt(line.substring(0, 3)));
            System.out.println();
            ret += keypy2.length() * Integer.parseInt(line.substring(0, 3));
        }
        System.out.println(ret);
        in.close();
    }

    public static String numpress(String line){
        int r = numpad.length - 1;
        int c = numpad[r].length - 1;
        StringBuilder stb = new StringBuilder();
        for(char q : line.toCharArray()){
            int[] loc = new int[2];
            for(int i = 0; i < numpad.length; i++) for(int j = 0; j < numpad[0].length; j++) if(numpad[i][j] == q) {loc = new int[]{i, j}; break;}
            int i = loc[0];
            int j = loc[1];

            //System.out.println(i + " " + j);
            if(r < i && c < j){
                if(r > i){
                    while(r != i){
                        r--;
                        stb.append('^');
                    }
                }
                else if(r < i){
                    while(r != i){
                        r++;
                        stb.append('v');
                    }
                }
                if(c > j){
                    while(c != j){
                        c--;
                        stb.append('<');
                    }
                }
                else if(c < j){
                    while(c != j){
                        c++;
                        stb.append('>');
                    }
                }
            }
            else if(r > i && c > i){
                if(c > j){
                    while(c != j){
                        c--;
                        stb.append('<');
                    }
                }
                else if(c < j){
                    while(c != j){
                        c++;
                        stb.append('>');
                    }
                }
                if(r > i){
                    while(r != i){
                        r--;
                        stb.append('^');
                    }
                }
                else if(r < i){
                    while(r != i){
                        r++;
                        stb.append('v');
                    }
                }
            }
            else{
                if(c > j){
                    while(c != j){
                        c--;
                        stb.append('<');
                    }
                }
                else if(c < j){
                    while(c != j){
                        c++;
                        stb.append('>');
                    }
                }
                if(r > i){
                    while(r != i){
                        r--;
                        stb.append('^');
                    }
                }
                else if(r < i){
                    while(r != i){
                        r++;
                        stb.append('v');
                    }
                }
            }
            if(r == i && c == j){
                stb.append('A');
            }
            else {
                System.out.println("numpress cooked");
                System.exit(0);
            }
        }

        return stb.toString();
    }

    public static String keypress(String line){
        int r = 0;
        int c = dirpad[0].length - 1;
        StringBuilder stb = new StringBuilder();
        for(char q : line.toCharArray()){
            int[] loc = new int[2];
            for(int i = 0; i < dirpad.length; i++) for(int j = 0; j < dirpad[0].length; j++) if(dirpad[i][j] == q) {loc = new int[]{i, j}; break;}
            int i = loc[0];
            int j = loc[1];
            if(r < i){
                if(r > i){
                    while(r != i){
                        r--;
                        stb.append('^');
                    }
                }
                else if(r < i){
                    while(r != i){
                        r++;
                        stb.append('v');
                    }
                }
                if(c > j){
                    while(c != j){
                        c--;
                        stb.append('<');
                    }
                }
                else if(c < j){
                    while(c != j){
                        c++;
                        stb.append('>');
                    }
                }
            }
            else if(r > i){
                if(c > j){
                    while(c != j){
                        c--;
                        stb.append('<');
                    }
                }
                else if(c < j){
                    while(c != j){
                        c++;
                        stb.append('>');
                    }
                }
                if(r > i){
                    while(r != i){
                        r--;
                        stb.append('^');
                    }
                }
                else if(r < i){
                    while(r != i){
                        r++;
                        stb.append('v');
                    }
                }
            }
            else{
                if(c > j){
                    while(c != j){
                        c--;
                        stb.append('<');
                    }
                }
                else if(c < j){
                    while(c != j){
                        c++;
                        stb.append('>');
                    }
                }
                if(r > i){
                    while(r != i){
                        r--;
                        stb.append('^');
                    }
                }
                else if(r < i){
                    while(r != i){
                        r++;
                        stb.append('v');
                    }
                }
            }
            if(r == i && c == j){
                stb.append('A');
            }
            else {
                System.out.println("numpress cooked");
                System.exit(0);
            }
        }

        return stb.toString();
    }
}
