package dictionary1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class DictionaryMain
{
    public static  Integer keyLen;
    public static  String rege;
    static void complete(String filename) throws IOException {// Запуск программы
        HashMap map;
        map =createMap(filename);
        mainMenu(filename,map);
    }
    protected abstract void validate ();


    public static void mainMenu (String filename,HashMap map)
    {
        while (true) {
            System.out.println("Выберите операцию, которую вы хотите выполнить:");
            System.out.println("1 - Прочитать весь словарь");
            System.out.println("2 - Удалить запись по ключу");
            System.out.println("3 - Поиск записи по ключу");
            System.out.println("4 - Добавить запись");
            System.out.println("5 - Выход");
            Scanner in = new Scanner(System.in);
            int number = in.nextInt();
            switch (number) {
                case 1:
                    readFile(filename);
                    break;
                case 2:
                    removeKey(filename, map);
                    break;
                case 3:
                    searchingKey(filename, map,rege,keyLen);
                    break;
                case 4:
                    addForHashMap(filename, map);
                    break;
                case 5:
                    System. exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    static void readFile (String filename){// Чтение из файла
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
    static void writeFile (String filename, HashMap hashMap){// Запись в файл
        try(FileWriter writer = new FileWriter(filename,false)) {
            Set allstr = hashMap.entrySet(); // коллекция элементов, не допускающих дублирования
            Iterator iter = allstr.iterator();
            while (iter.hasNext())
            {
                Map.Entry me = (Map.Entry) iter.next();
                writer.write(me.getKey() + " " + me.getValue());
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    static void searchingKey (String filename, HashMap map,String regex,Integer len){// Поиск по ключу
        System.out.print("Введите значение ключа для удаления: ");
        Scanner scanner = new Scanner(System.in);
        String key= scanner.nextLine();
        key =key.trim();
        if ((key.length() == len && key.matches(regex)))
        {
            System.out.println("Вы ввели ключ: "+ key);
            if (map.containsKey(key)){
                System.out.println("Найденное значение: "+map.get(key));
            }
            else System.out.println("Такого значения в словаре нет");
        }
        else System.out.println("Введены некорректные значения");
        scanner.close();
    }

    static void removeKey (String filename, HashMap map){// Удалить по ключу
        System.out.print("Введите значение ключа для удаления: ");
        Scanner scanner = new Scanner(System.in);
        String key= scanner.nextLine();
        key =key.trim();
        System.out.println("Введён ключ: "+ key);
        if (map.containsKey(key))
        {
            map.remove(key);
            writeFile(filename, map);
            System.out.println("Значение удалено успешно");
        }
        else System.out.println("Такого значения в словаре нет");
        scanner.close();
    }
    static void addForHashMap(String filename, HashMap hashMap){// Добавление в Map
        System.out.print("Введите ключ: ");
        Scanner scanner1 = new Scanner(System.in);
        String key = scanner1.nextLine();
        key = key.trim();
        System.out.print("Введите значение: ");
        Scanner scanner2 = new Scanner(System.in);
        String value = scanner2.nextLine();
        value = value.trim();
        if ((key.length() == 4 && key.matches("^[a-zA-Z0-9]+$")) && (value.length() == 5 && value.matches("\\d+")))
        {
            if (!hashMap.containsKey(key)) {
                hashMap.put(key, value);
                writeFile(filename, hashMap);
                System.out.println("Значение добавлено");
            }
            else System.out.println("Введенный ключ уже существует");
        }
        else System.out.println("Введены некорректные значения");
        scanner1.close();
        scanner2.close();

    }
    public static HashMap createMap(String filename) throws IOException
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
        if((value.length()==4 && value.matches("^[a-zA-Z0-9]+ $")) && (Integer.toString(key).length()==5 && Integer.toString(key).matches("\\d+")))
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
        scanner.close();
        return  map;
    }

}
