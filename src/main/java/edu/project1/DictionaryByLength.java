package edu.project1;

import java.util.List;

public interface DictionaryByLength extends Dictionary {
    List<String> getByLength(int length);
}
