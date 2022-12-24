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

    private int rotorIndex1 = 0;
    private int rotorIndex2 = 0;
    private int rotorIndex3 = 0;


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
                plugBoard.get(0).put(i,s[1]);
                plugBoard.get(1).put(i,s[2]);
            }else {
                plugBoard.get(0).put(i,s[0]);
                plugBoard.get(1).put(i,s[1]);
            }

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
            //System.out.println("--------");
        }
        return rotors;
    }

    public String decode(String encrypted){
        String[] str = encrypted.split("");
        StringBuilder sb  =new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            char chr = str[i].charAt(0);
            chr = plugBoardDecode(chr);
            chr = rotor3Decode(chr);
            chr = rotor2Decode(chr);
            chr = rotor1Decode(chr);
            chr = reflectorDecode(chr);
            chr = rotor1Decode(chr);
            chr = rotor2Decode(chr);
            chr = rotor3Decode(chr);
            chr = plugBoardDecode(chr);
            sb.append(chr);
            rotateRotor();
        }
        return sb.toString();
    }

    private char plugBoardDecode (char chr){
        //boolean exist = false;
        char answer = chr;
        for (int i = 0; i < plugBoard.get(0).size(); i++) {
            if (plugBoard.get(0).get(i).charAt(0)==chr){
                //exist =true;
                answer = plugBoard.get(1).get(i).charAt(0);
                return answer;
            }
        }
        for (int i = 0; i < plugBoard.get(1).size(); i++) {
            if (plugBoard.get(1).get(i).charAt(0)==chr){
                //exist =true;
                answer = plugBoard.get(0).get(i).charAt(0);
                return answer;
            }
        }
        return answer;
    }

    private char rotor3Decode(char chr){
        int j = chr-97;
        return rotors.get(2).getIthValue(j).charAt(0);
    }

    private char rotor2Decode(char chr){
        int j = chr-97;
        return rotors.get(1).getIthValue(j).charAt(0);

    }

    private char rotor1Decode(char chr){
        int j=chr-97;
        return rotors.get(0).getIthValue(j).charAt(0);

    }

    private char reflectorDecode(char chr){
        //int j = chr-97;
        int k = 122-chr;
        return reflector.get(k).charAt(0);
    }

    private void rotateRotor(){
        rotorIndex3++;
        if (rotorIndex3==26){
            rotorIndex2++;
            rotors.get(1).rotate();
            rotorIndex3=0;
        }
        rotors.get(2).rotate();

        if (rotorIndex2==26){
            rotorIndex1++;
            rotors.get(0).rotate();
            rotorIndex2=0;
        }
    }



}
