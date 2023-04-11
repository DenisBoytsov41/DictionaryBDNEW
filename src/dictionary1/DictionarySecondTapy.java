package dictionary1;

import java.io.IOException;

public class DictionarySecondTapy extends DictionaryMain{
    int lengs = 5;
    String regect ="\\d+";
    public DictionarySecondTapy(String filename) throws IOException {
        DictionaryMain.complete(filename);
    }
    @Override
    protected void validate() {
        DictionaryMain.rege = regect;
        DictionaryMain.keyLen = lengs;
    }
}
