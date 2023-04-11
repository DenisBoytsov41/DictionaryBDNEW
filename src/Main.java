import dictionary1.DictionaryFirstTapy;
import dictionary1.DictionarySecondTapy;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        try {
            System.out.print("Введите путь к файлу: ");
            Scanner filen = new Scanner(System.in);
            String filename = filen.nextLine();
            System.out.println("Выберите словарь:");
            System.out.println("1 - Английский гипотетический-Цифры");
            System.out.println("2 - Цифры-Английский гипотетический");
            HashMap map = new HashMap();
            Scanner choose = new Scanner(System.in);
            int number = choose.nextInt();
            switch (number) {
                case 1:
                    DictionaryFirstTapy dft = new DictionaryFirstTapy(filename);
                    break;
                case 2:
                    DictionarySecondTapy dst = new DictionarySecondTapy(filename);
                    break;
                default:
                    break;
            }
        }
        catch (Exception e)
        {
            System.out.print("Вы ввели некорректный путь к файлу!");
        }
    }
}