import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Countries2 {
    public static HashMap<String, ArrayList<Country>> alphaGroup = new HashMap();

    public static void main(String[] args) {
        splitColumns();
        returnCountries();
        saveEntry();
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

    static void splitColumns() {
        String cContent = readFile("countries.txt");
        String[] lines = cContent.split("\n");
        for (String line : lines) {
            String[] columns = line.split("\\|");
            String abbr = columns[0];
            String name = columns[1];
            Country country = new Country(abbr, name);
            String firstLetter = String.valueOf(name.charAt(0));
            ArrayList<Country> list = alphaGroup.get(firstLetter);
            if (list == null) {
                list = new ArrayList();
                list.add(country);
                alphaGroup.put(firstLetter, list);
            } else {
                list.add(country);
            }
        }
    }//static method splitColumns

    static void returnCountries() {
        System.out.println("Type any letter to see all of the countries beginning with that letter.");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toLowerCase();
        if (input.length() != 1  || !input.matches("[A-Za-z]")) {
            System.out.println("Not a valid entry");
            returnCountries();
        } else {
            ArrayList<Country> output = alphaGroup.get(input);
            String outputStr = "";
            for (Country c : output) {
                outputStr += String.format("%s %s | ", c.abbr, c.name);
            }
            System.out.println(outputStr);
        }
    }//static method returnCountries

    static void saveEntry() {
        File f = new File("save.json");
        JsonSerializer serializer = new JsonSerializer();
        String contentToSave = serializer.include("*").serialize(alphaGroup);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(contentToSave);
            fw.close();
        } catch (Exception e) {
            System.out.println("Save Failed!");
        }
    }//static method saveEntry

}//class Countries