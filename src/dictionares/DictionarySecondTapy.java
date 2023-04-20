package dictionares;

import java.io.IOException;

import static dictionares.Main.mainMenu;

public class DictionarySecondTapy extends DictionaryMain{
    static String regexFirst ="\\d{5}";
    static String regexSecond ="[a-zA-Z]{4}";
    public DictionarySecondTapy(String filename) throws IOException {
        validate();
        running(filename);
        mainMenu(filename, mapMain);
    }

    public DictionarySecondTapy() {
        validate();
    }

    @Override
    protected void validate() {
        DictionaryMain.regexKey = regexFirst;
        DictionaryMain.regexValue = regexSecond;
    }
}
