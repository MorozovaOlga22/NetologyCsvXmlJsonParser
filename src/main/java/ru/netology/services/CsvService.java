package ru.netology.services;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import ru.netology.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class CsvService {
    public static List<Employee> parseCSV(String[] columnMapping, String fileName) throws IOException {
        try (final CSVReader csvReader = new CSVReader(new FileReader(fileName))) {
            final ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(Employee.class);
            strategy.setColumnMapping(columnMapping);
            final CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee>(csvReader).withMappingStrategy(strategy).build();
            return csv.parse();
        }
    }
}
