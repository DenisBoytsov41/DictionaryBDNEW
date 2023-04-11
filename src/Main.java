import dictionary1.DictionaryMain;

import java.util.Scanner;

public class Main{
    public static void main(String[] args)
    {
        //C:\\Users\\Денис\\Desktop\\text_txt.txt
        try {
            System.out.print("Введите путь к файлу: ");
            Scanner filen = new Scanner(System.in);
            String filename = filen.nextLine();
            DictionaryMain.workMain(filename);
        }
        catch (Exception e)
        {
            System.out.print("Вы ввели некорректный путь к файлу!");
        }
    }

}