package ru.netology.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ru.netology.Employee;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class JsonService {
    private static final Type employeeListType = new TypeToken<List<Employee>>() {
    }.getType();

    public static void writeEmployeeToJson(List<Employee> employeeList, String resultJsonFileName) throws IOException {
        final String resultJson = listToJson(employeeList);
        try (Writer file = new FileWriter(resultJsonFileName)) {
            file.write(resultJson);
            file.flush();
        }
    }

    private static String listToJson(List<Employee> employeeList) {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        return gson.toJson(employeeList, employeeListType);
    }

    public static List<Employee> jsonToList(String fileName) throws IOException {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        try (final FileReader reader = new FileReader(fileName);
             final BufferedReader buffer = new BufferedReader(reader)) {
            final String jsonString = buffer.lines().collect(Collectors.joining());
            return gson.fromJson(jsonString, employeeListType);
        }
    }
}
