package dictionary1;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public abstract class DictionaryMain
{
    protected static  String regeKey;
    protected static  String regeValue;

    protected static  int vrem = 0;

    protected static HashMap<String,String> mapOsn = new LinkedHashMap();
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
            System.out.println("8 - Выбор файла");
            System.out.println("9 - Выход");
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
                    System.out.print("Введите путь к файлу: ");
                    Scanner filen = new Scanner(System.in);
                    filename = filen.nextLine();
                    workMain(filename);
                    break;
                case 9:
                    System. exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    private static HashMap<String,String> retateZnach(HashMap<String,String> map,int i,String filename) throws IOException {
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
    private static void readFile (String filename){// Чтение из файла
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
    private static void writeFile (String filename, HashMap<String,String> hashMap,String k,String v){// Запись в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for (HashMap.Entry<String,String> entry : hashMap.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                if (!fileContains(writer, key, value,filename)) {
                    writer.write(key + " " + value);
                    writer.newLine();
                }
            }
            if (fileContains(writer, k, v,filename)) {
                writer.close();
                deleteInFile(k, v, filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void searchingKey (String filename, HashMap map,String regex){// Поиск по ключу
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

    private static void removeKey (String filename, HashMap map){// Удалить по ключу
        System.out.print("Введите значение ключа для удаления: ");
        Scanner scanner = new Scanner(System.in);
        String key= scanner.nextLine();
        key =key.trim();
        System.out.println("Введён ключ: "+ key);
        if (map.containsKey(key))
        {
            String valueKey = (String) map.get(key);
            map.remove(key);
            writeFile(filename, map,key,valueKey);
            System.out.println("Значение удалено успешно");
        }
        else System.out.println("Такого значения в словаре нет");
    }
    private static void addForHashMap(String filename, HashMap hashMap){// Добавление в Map
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
                writeFile(filename, hashMap,"","");
                System.out.println("Значение добавлено");
            }
            else System.out.println("Введенный ключ уже существует");
        }
        else System.out.println("Введены некорректные значения");

    }
    private static void readHashMap(HashMap hashMap){
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
    private static HashMap createMap(String filename) throws IOException
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
    private static HashMap chooseStr(String line, HashMap map)
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
    private static boolean fileContains(BufferedWriter writer, String key, String value,String filename) throws IOException {
        writer.flush();
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.trim().equals(key + " " + value)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    private static void deleteInFile(String key, String value,String filename) throws IOException {
        File inputFile = new File(filename);
        File tempFile = new File(inputFile.getParent() + "\\temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            String lineToRemove = key + " " + value;
            String currentLine;
            boolean found = false;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(lineToRemove) && !found) {
                    found = true;
                    continue;
                }
                writer.write(currentLine + System.lineSeparator());
            }
            if (!found) {
                System.out.println("Не нашли нужной строчки");
                return;
            }
            writer.close();
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        if (inputFile.delete())
        {
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Не удалось переименовать временный файл");
            }
        } else
        {
            System.out.println("Не удалось удалить исходный файл");
        }
    }

}
