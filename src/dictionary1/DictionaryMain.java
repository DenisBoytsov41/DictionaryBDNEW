package dictionary1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class DictionaryMain {
    static void complete(String filename) {// Запуск программы

    }

    static void readFile(String filename) {// Чтение из файла
        try (FileReader reader = new FileReader(filename)) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            System.out.print(e.getMessage());
        } catch (IOException e) {
            System.out.print(e.getMessage());
        }
    }

    static void writeFile(String filename, HashMap hashMap) {// Запись в файл
        try (FileWriter writer = new FileWriter(filename, false)) {
            Set allstr = hashMap.entrySet(); // коллекция элементов, не допускающих дублирования
            Iterator iter = allstr.iterator();
            while (iter.hasNext()) {
                Map.Entry me = (Map.Entry) iter.next();
                writer.write(me.getKey() + " " + me.getValue());
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void searchingKey(String filename, HashMap map) {// Поиск по ключу

    }

    static void removeKey(String filename, HashMap map) {// Удалить по ключу

    }

    static void addForHashMap(String filename, HashMap hashMap) {// Добавление в Map
    }
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
}
