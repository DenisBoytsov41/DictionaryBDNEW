package dictionary1;

import java.util.HashMap;

public interface DictionaryMain
{
    static void complete(String filename){} // Запуск программы
    static void readFile (String filename){} // Чтение из файла
    static void writeFile (String filename,HashMap map){} // Запись в файл
    static void searchingKey (String filename, HashMap map){} // Поиск по ключу
    static void removeKey (String filename, HashMap map){} // Удалить по ключу
    static void addForHashMap(String filename, HashMap hashMap){}; // Добавление в Map
    //static HashMap chooseStr(String line, HashMap hmap){} // Формат поиска

}
