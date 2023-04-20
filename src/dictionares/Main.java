package dictionares;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static dictionares.DictionaryMain.*;

public class Main{
    public static void main(String[] args)
    {
        //C:\\Users\\Денис\\Desktop\\text_txt.txt
       // /home/student/Desktop/text
        try {
            System.out.print("Введите путь к файлу: ");
            Scanner filen = new Scanner(System.in);
            String filename = filen.nextLine();
            workMain(filename);
        }
        catch (Exception e)
        {
            System.out.print("Вы ввели некорректный путь к файлу!");
        }
    }
    public static void mainMenu(String filename, HashMap map) throws IOException {
        while (true) {
            System.out.println("Выберите операцию, которую вы хотите выполнить:");
            System.out.println("1 - Вывести весь словарь");
            System.out.println("2 - Удалить запись по ключу");
            System.out.println("3 - Поиск записи по ключу");
            System.out.println("4 - Добавить запись");
            System.out.println("5 - Прочитать файл");
            System.out.println("6 - Выбор словаря");
            System.out.println("7 - Выбор файла");
            System.out.println("8 - Выход");
            Scanner in = new Scanner(System.in);
            int number = in.nextInt();
            switch (number) {
                case 1:
                    readHashMap(map);
                    break;
                case 2:
                    removeKey(filename, map);
                    break;
                case 3:
                    searchingKey(filename, map, regexKey);
                    break;
                case 4:
                    addForHashMap(filename, map);
                    break;
                case 5:
                    readFile(filename);
                    break;
                case 6:
                    workMain(filename);
                    break;
                case 7:
                    main(new String[]{""});
                    break;
                case 8:
                    System. exit(0);
                    break;
                default:
                    break;
            }
        }
    }


}