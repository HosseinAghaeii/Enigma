import TableMap.SortedTableMap;
import TableMap.UnsortedTableMap;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader fileReader = new FileReader("C:\\Users\\bpc\\Desktop\\EnigmaFile.txt");
        Scanner scr = new Scanner(fileReader);
        System.out.println( scr.next());
        System.out.println( scr.next());

        System.out.println( scr.next());
        String s = scr.nextLine();
        System.out.println(s);
         String[] str = s.split(", ");

        System.out.println(Arrays.toString(str));

//------------------------------------------------------------------------------------------------------------------------
        Scanner input = new Scanner(System.in);
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(a,b);
            }
        };
        SortedTableMap<Integer,String> reflector = new SortedTableMap<>(comp);

        List<UnsortedTableMap<Integer,String>> plugBoard = new ArrayList<>();
        plugBoard.add(new UnsortedTableMap<>());
        plugBoard.add(new UnsortedTableMap<>());

        List<UnsortedTableMap<Integer,String>> rotors = new ArrayList<>();
        rotors.add(new UnsortedTableMap<>());
        rotors.add(new UnsortedTableMap<>());
        rotors.add(new UnsortedTableMap<>());

        System.out.println("--------------------------------------------------------");
        System.out.println("inter time:");
        System.out.println("format(yyyy/mm/dd)");
        String time = input.next();
        System.out.println("inter Encrypted word:");


    }
}