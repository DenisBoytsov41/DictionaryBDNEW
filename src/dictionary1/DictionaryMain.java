package dictionary1;

import com.sun.tools.javac.Main;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class DictionaryMain
{
    public static  String regeKey;
    public static  String regeValue;

    public static  int vrem = 0;

   public static HashMap<String,String> mapOsn = new LinkedHashMap();
    static void complete(String filename) throws IOException {// Запуск программы
        mapOsn =createMap(filename);
        mainMenu(filename,mapOsn);
    }
    protected abstract void validate ();


    public static void mainMenu (String filename,HashMap map) throws IOException {
        while (true) {
            System.out.println("Выберите операцию, которую вы хотите выполнить:");
            System.out.println("1 - Записать значения в словарь");
            System.out.println("2 - Прочитать весь словарь");
            System.out.println("3 - Удалить запись по ключу");
            System.out.println("4 - Поиск записи по ключу");
            System.out.println("5 - Добавить запись");
            System.out.println("6 - Прочитать файл");
            System.out.println("7 - Выбор словаря");
            System.out.println("8 - Выход");
            Scanner in = new Scanner(System.in);
            int number = in.nextInt();
            switch (number) {
                case 1:
                    complete(filename);
                    break;
                case 2:
                    readHashMap(map);
                    break;
                case 3:
                    removeKey(filename, map);
                    break;
                case 4:
                    searchingKey(filename, map,regeKey);
                    break;
                case 5:
                    addForHashMap(filename, map);
                    break;
                case 6:
                    readFile(filename);
                    break;
                case 7:
                    workMain(filename);
                    break;
                case 8:
                    System. exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    public static HashMap<String,String> retateZnach(HashMap<String,String> map,int i,String filename) throws IOException {
        HashMap swapped = new LinkedHashMap();
        for (HashMap.Entry<String,String> enty: map.entrySet())
        {
            swapped.put(enty.getValue(),enty.getKey());
        }
        map.clear();
        map.putAll(swapped);
        if (i==1) {
            DictionaryFirstTapy dft = new DictionaryFirstTapy();
        } else {
            DictionarySecondTapy dst = new DictionarySecondTapy();
        }
        return map;
    }
    public static int workMain(String filename)
    {
        try {
            System.out.println("Выберите словарь:");
            System.out.println("1 - Английский гипотетический-Цифры");
            System.out.println("2 - Цифры-Английский гипотетический");
            Scanner choose = new Scanner(System.in);
            int number = choose.nextInt();
            switch (number) {
                case 1:
                    if (vrem != number && vrem!=0) {
                        vrem = number;
                        mapOsn = retateZnach(mapOsn,number,filename);
                    }
                    else {
                        vrem = number;
                        DictionaryFirstTapy dft = new DictionaryFirstTapy(filename);
                    }
                    return number;
                case 2:
                    if (vrem != number && vrem!=0) {
                        vrem = number;
                        mapOsn = retateZnach(mapOsn,number,filename);
                    }
                    else {
                        vrem = number;
                        DictionarySecondTapy dst = new DictionarySecondTapy(filename);
                    }
                    return number;
                default:
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.print("Вы ввели некорректный путь к файлу!");
        }

        return 0;
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
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            Map<String,String> map = new LinkedHashMap<>();
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                map.put(line,"");
            }
            scanner.close();
            map.putAll(hashMap);
            FileWriter writer = new FileWriter(filename,true);
            Set allstr = hashMap.entrySet(); // коллекция элементов, не допускающих дублирования
            Iterator iter = allstr.iterator();
            while (iter.hasNext())
            {
                Map.Entry me = (Map.Entry) iter.next();
                writer.write(me.getKey() + " " + me.getValue());
                writer.append('\n');
            }

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        /*try(FileWriter writer = new FileWriter(filename,false)) {

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
        }*/
    }
    static void searchingKey (String filename, HashMap map,String regex){// Поиск по ключу
        System.out.print("Введите значение ключа для удаления: ");
        Scanner scanner = new Scanner(System.in);
        String key= scanner.nextLine();
        key =key.trim();
        if (key.matches(regex))
        {
            System.out.println("Вы ввели ключ: "+ key);
            if (map.containsKey(key)){
                System.out.println("Найденное значение: "+map.get(key));
            }
            else System.out.println("Такого значения в словаре нет");
        }
        else System.out.println("Введены некорректные значения");
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
        if ( key.matches(regeKey) && value.matches(regeValue))
        {
            if (!hashMap.containsKey(key)) {
                hashMap.put(key, value);
                writeFile(filename, hashMap);
                System.out.println("Значение добавлено");
            }
            else System.out.println("Введенный ключ уже существует");
        }
        else System.out.println("Введены некорректные значения");

    }
    public static void readHashMap(HashMap hashMap){
        Set set = hashMap.entrySet();
        Iterator iter = set.iterator();
        // Отображаем элементы
        while(iter.hasNext()) {
            Map.Entry me = (Map.Entry)iter.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
        System.out.println();
    }
    public static HashMap createMap(String filename) throws IOException
    {
        Path path = Paths.get(filename);
        Scanner scanner = new Scanner(path);
        HashMap map = mapOsn;
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
        String  key = "";
        String value = "";
        key = scanner.next();
        value = scanner.next();
        if((key.matches(regeKey) && (value.matches(regeValue))))
        {
            if (map.containsKey(key))
            {
                System.out.println("Такое ключ и значение уже есть в словаре: ключ - "+key+" значение - "+value);
            }
            else
            {
                map.put(key,value);
                System.out.println("Добавлено значение: ключ - "+key+" значение - "+value);
            }
        }
        return  map;
    }

}
