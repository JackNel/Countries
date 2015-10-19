import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Jack on 10/19/15.  This is Zach's version from in class on 10/19/2015
 */
public class Countries3 {
    public static void main(String[] args) {
        String fileContent = readFile("countries.txt");
        String[] lines = fileContent.split("\n");
        HashMap<String, ArrayList<Country>> countries = new HashMap();

        for (String line : lines) {
            String[] columns = line.split("\\|");
            String abbr = columns[0];
            String name = columns[1];
            Country c = new Country(abbr, name);

            String firstLetter = name.substring(0, 1);
            ArrayList<Country> list = countries.get(firstLetter);
            if (list == null) {
                list = new ArrayList();
                list.add(c);
                countries.put(firstLetter, list);
            } else {
                list.add(c);
            }
        }//for loop

        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine();
        ArrayList<Country> list = countries.get(letter);
        if (list == null) {
            System.out.println(String.format("Can't find any country starting with '%s'", letter));
        } else {
            String output = "";
            for (Country c : list) {
                output += String.format("%s %s\n", c.abbr, c.name);
            }
            writeFile(String.format("%s_countries.txt", letter), output);
        }
    }//main method



    static String readFile(String fileName) {
        File f = new File(fileName);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] fileContent = new char[fileSize];
            fr.read(fileContent);
            return new String(fileContent);
        } catch (Exception e) {
            return null;
        }
    }//static method readFile

    static void writeFile(String fileName, String fileContent) {
        File f = new File(fileName);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(fileContent);
            fw.close();
        } catch (Exception e){

        }
    }//static method writeFile

}//Countries3 class
