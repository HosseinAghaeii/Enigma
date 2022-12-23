import TableMap.SortedTableMap;
import TableMap.UnsortedTableMap;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ReadFile {
    FileReader fileReader = new FileReader("C:\\Users\\bpc\\Desktop\\EnigmaFile.txt");
    Scanner scr = new Scanner(fileReader);
    Comparator<Integer> comp = new Comparator<Integer>() {
        @Override
        public int compare(Integer a, Integer b) {
            return Integer.compare(a,b);
        }
    };
    UnsortedTableMap<Integer,String> reflector = new UnsortedTableMap<>();
    List<UnsortedTableMap<Integer,String>> plugBoard = new ArrayList<>();
    List<UnsortedTableMap<Integer,String>> rotors = new ArrayList<>();


    public ReadFile() throws FileNotFoundException {
        plugBoard.add(new UnsortedTableMap<>());
        plugBoard.add(new UnsortedTableMap<>());
        rotors.add(new UnsortedTableMap<>());
        rotors.add(new UnsortedTableMap<>());
        rotors.add(new UnsortedTableMap<>());

    }

    public boolean readOneDay(String date){
        boolean identify= false;

        while (scr.hasNextLine()){
            scr.next();//read "Date:"
            String time = scr.next();
            if (time.equals(date)){
                identify = true;
                plugBoard = setPlugBoard();
                reflector = setReflector();
                rotors = setRotors();
                break;
            }
            scr.nextLine();
            scr.nextLine();
            scr.nextLine();
            scr.nextLine();
        }

        return identify;
    }

    private List<UnsortedTableMap<Integer,String>>  setPlugBoard(){
        scr.next();//read "PlugBoard:"
        String[] str = scr.nextLine().split(", ");
        //System.out.println(Arrays.toString(str));
        for (int i = 0; i < str.length; i++) {
            String[] s = str[i].split("");
            //System.out.println(Arrays.toString(s));
            char c0,c1;
            if (i==0){
                 c0 = s[1].charAt(0);
                 c1 = s[2].charAt(0);
            }else {
                c0 = s[0].charAt(0);
                c1 = s[1].charAt(0);
            }
            plugBoard.get(0).put(i,s[0]);
            plugBoard.get(1).put(i,s[1]);
           // System.out.println(c0+" "+c1);
        }
        return plugBoard;
    }

    private UnsortedTableMap<Integer,String> setReflector(){
        for (int i = 0; i < 26; i++) {
            reflector.put(i, String.valueOf((char)(i+97)));
           // System.out.println(reflector.get(i));
        }
        return reflector;
    }

    private List<UnsortedTableMap<Integer,String>>  setRotors(){
        for (int j = 0; j < 3; j++) {
            scr.next();//read "rotor j:"
            String[] str = scr.next().split("");
            //System.out.println(Arrays.toString(str));
            char[] chars = new char[str.length];
            for (int i = 0; i < str.length; i++) {
                chars[i] = str[i].charAt(0);
                 rotors.get(j).put(chars[i] - 97, str[i])  ;
                //System.out.print(rotors.get(j).get(chars[i] - 97));
            }
            System.out.println("--------");
        }
        return rotors;
    }

}
