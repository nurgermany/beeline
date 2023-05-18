package com.example.beeline.entity;

import javax.persistence.Entity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject

import java.util.*;

@Entity
public class Main {
    public static void main(String[] args) throws JSONException expected  {

                String json = "[\n" +
                        "    {\n" +
                        "        \"country\": \"Kyrgyzstan\",\n" +
                        "        \"city\": \"Bishkek\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"country\": \"Kyrgyzstan\",\n" +
                        "        \"city\": \"Osh\"\n" +
                        "    },\n" +
                        "    {\n" +
                        "        \"country\": \"Italy\",\n" +
                        "        \"city\": \"Milan\"\n" +
                        "    }\n" +
                        "]";

                // Преобразование JSON-строки в массив объектов
                JSONArray jsonArray = new JSONArray(json);

                // Группировка городов по странам
                Map<String, List<String>> countryCityMap = new HashMap<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String country = jsonObject.getString("country");
                    String city = jsonObject.getString("city");
                    countryCityMap.computeIfAbsent(country, k -> new ArrayList<>()).add(city);
                }

                // Сортировка городов в списке по алфавиту
                for (List<String> cities : countryCityMap.values()) {
                    Collections.sort(cities);
                }

                // Подсчет количества городов в каждой стране
                Map<String, Integer> countryCountMap = new HashMap<>();
                for (String country : countryCityMap.keySet()) {
                    List<String> cities = countryCityMap.get(country);
                    countryCountMap.put(country, cities.size());
                }

                // Вывод результатов
                for (String country : countryCityMap.keySet()) {
                    System.out.println("Country: " + country);
                    System.out.println("Cities: " + countryCityMap.get(country));
                    System.out.println("Number of cities: " + countryCountMap.get(country));
                    System.out.println();
                }
            }
}
