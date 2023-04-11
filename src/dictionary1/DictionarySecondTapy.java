package dictionary1;

import java.io.IOException;

public class DictionarySecondTapy extends DictionaryMain{
    static String regect1 ="\\d{5}";
    static String regect2 ="[a-zA-Z]{4}";
    public DictionarySecondTapy(String filename) throws IOException {
        validate();
        DictionaryMain.complete(filename);
    }

    public DictionarySecondTapy() {
        validate();
    }

    @Override
    protected void validate() {
        DictionaryMain.regeKey = regect1;
        DictionaryMain.regeValue = regect2;
    }
}
