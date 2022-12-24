import TableMap.SortedTableMap;
import TableMap.UnsortedTableMap;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
      menu();
    }
    public static void menu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        ReadFile readFile = new ReadFile();

        while (true) {
            System.out.println("inter time:");
            System.out.println("format(yyyy/mm/dd)");
            String time = input.next();
            boolean identify = readFile.readOneDay(time);
            if (!identify) {
                System.out.println("**We can not identify this day!");
                continue;
            }

            break;
        }

        System.out.println("inter Encrypted word:");
        String encrypted = input.next();
        System.out.print("decode of word is: ");
        System.out.print(readFile.decode(encrypted));
    }
}