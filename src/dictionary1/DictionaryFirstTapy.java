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

    int lengs = 4;
    String regect ="^[a-zA-Z0-9]+$";
    public DictionaryFirstTapy(String filename) throws IOException {
        DictionaryMain.complete(filename);
    }
    @Override
    protected void validate() {
        DictionaryMain.rege = regect;
        DictionaryMain.keyLen = lengs;
    }
}
