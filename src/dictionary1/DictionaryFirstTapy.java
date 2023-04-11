package dictionary1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.nio.file.Path;
import java.util.Scanner;

public class DictionaryFirstTapy extends DictionaryMain{

    static String regect1 ="[a-zA-Z]{4}";
    static String regect2 ="\\d{5}";
    public DictionaryFirstTapy(String filename) throws IOException {
        validate();
        DictionaryMain.mainMenu(filename,DictionaryMain.mapOsn);
    }

    public DictionaryFirstTapy() {
        validate();
    }

    @Override
    protected void validate() {
        DictionaryMain.regeKey = regect1;
        DictionaryMain.regeValue = regect2;
    }
}
