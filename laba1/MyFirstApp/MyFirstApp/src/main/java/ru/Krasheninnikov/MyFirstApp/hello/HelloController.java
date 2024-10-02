package ru.Krasheninnikov.MyFirstApp.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class HelloController {
    private ArrayList<String> arrayList = new ArrayList<>();
    private HashMap<Integer, String> hashMap = new HashMap<>();
    private int mapCounter = 0; // Счетчик для ключей HashMap

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World ") String name ) {
        return String.format("Hello %s!", name);
    }
    // Метод для обновления ArrayList
    @GetMapping("/update-array")
    public String updateArrayList() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Test");
        list.add("Test value list");
        return list.toString();
    }
    // Метод для отображения всех элементов ArrayList
    @GetMapping("/show-array")
    public String showArrayList() {
        return updateArrayList();
    }
    // Метод для обновления HashMap
    public String updateHashMap(@RequestParam String s) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        int mapCounter = 0;
        hashMap.put(mapCounter++, s); // Добавляем значение в HashMap с уникальным ключом
        return "Added to HashMap: " + s + ". Current size: " + hashMap.size();
    }
    // Метод для отображения всех элементов HashMap
    @GetMapping("/show-map")
    public HashMap<Integer, String> showHashMap() {
        return hashMap;
    }

    // Метод для отображения количества элементов в ArrayList и HashMap
    @GetMapping("/show-all-length")
    public String showAllLength() {
        return "ArrayList size: " + arrayList.size() + ", HashMap size: " + hashMap.size();
    }
}

