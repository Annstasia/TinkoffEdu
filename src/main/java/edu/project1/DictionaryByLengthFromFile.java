package edu.project1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Возможно тут смешение логики:
// 1. хранение и передача словаря
// 2. ввод из файла
// Но не обязательно имплементации DictionaryByLength хранят данные в таком виде
// Например, если бы файл был большой, то можно было бы не реализовывать
// в конструкторе его считывание,
// а в getByLength считывать для конкретной длины не более чем сколько-то слов
// Логики хранения всего словаря в общем случае может не быть
public class DictionaryByLengthFromFile implements DictionaryByLength {
    private final Map<Integer, ArrayList<String>> dictionary;
    private final static Logger LOGGER = LogManager.getLogger(DictionaryByLengthFromFile.class);

    public DictionaryByLengthFromFile() {
        this(Constants.RU);
    }

    public DictionaryByLengthFromFile(String filePath) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<Integer, ArrayList<String>>>() {
        }.getType();
        try {
            dictionary = gson.fromJson(new JsonReader(new FileReader(filePath)), type);
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override public List<String> getDictionary() {
        return dictionary.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }

    @Override public List<String> getByLength(int length) {
        return dictionary.get(length);
    }
}
