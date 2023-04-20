package dictionares;

import java.io.IOException;

import static dictionares.Main.mainMenu;

public class DictionaryFirstTapy extends DictionaryMain{

    static String regexFirst ="[a-zA-Z]{4}";
    static String regexSecond ="\\d{5}";
    public DictionaryFirstTapy(String filename) throws IOException {
        validate();
        running(filename);
        mainMenu(filename, mapMain);
    }

    public DictionaryFirstTapy() {
        validate();
    }

    @Override
    protected void validate() {
        DictionaryMain.regexKey = regexFirst;
        DictionaryMain.regexValue = regexSecond;
    }
}
