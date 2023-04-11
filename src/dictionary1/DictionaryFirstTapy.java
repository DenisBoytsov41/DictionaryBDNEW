package dictionary1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.nio.file.Path;
import java.util.Scanner;

public class DictionaryFirstTapy implements DictionaryMain{

    public static HashMap createmap(String filename) throws IOException
    {
        Path path = Paths.get(filename);
        Scanner scanner = new Scanner(path);
        HashMap map = new HashMap();
        scanner.useDelimiter("\n");
        while (scanner.hasNext())
        {
            map =chooseStr(scanner.next(),map);
        }
        scanner.close();
        return map;
    }
    public static HashMap chooseStr(String line, HashMap map)
    {
        Scanner scanner = new Scanner(line);
        String value = scanner.next();
        int  key = scanner.nextInt();
        boolean latin = value.matches("^[a-zA-Z0-9]+ $");
        boolean numbers = value.matches("^[0-9]+ $");
        if((value.length()==4 && latin) && (Integer.toString(key).length()==5 && numbers))
        {
            if (map.containsKey(key))
            {
                System.out.printf("Такой ключ уже есть: ключ - %d , значение - %d",key,value);
            }
            else
            {
                map.put(value,key);
            }
        }
        return  map;
    }
    public static void readFile (String filename)
    {
        try (FileReader reader = new FileReader(filename))
        {
            int c;
            while ((c = reader.read())!=-1)
            {
                System.out.print((char)c);
            }
        } catch (FileNotFoundException e) {
            System.out.print(e.getMessage());
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }
    public static void writeFile (String filename,HashMap map)
    {
        try(FileWriter writer = new FileWriter(filename,false)) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
