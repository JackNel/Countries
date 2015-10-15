import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Countries {
    public static void main(String[] args) {
        HashMap<String, ArrayList<Country>> alphaGroup = new HashMap();
        String cContent = readFile("countries.txt");
        String[] lines = cContent.split("\n");

        for (String line : lines) {
            String[] columns = line.split("\\|");
            String abbr = columns[0];
            String name = columns[1];
            ArrayList<Country> group = new ArrayList();
            Country country = new Country(abbr, name);
            group.add(country);
            String firstLetter = String.valueOf(name.charAt(0));
            alphaGroup.put(firstLetter, group);
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
}//class Countries
