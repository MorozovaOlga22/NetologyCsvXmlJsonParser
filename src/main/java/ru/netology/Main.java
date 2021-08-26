package ru.netology;


import org.xml.sax.SAXException;
import ru.netology.services.CsvService;
import ru.netology.services.JsonService;
import ru.netology.services.XmlService;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        final String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        final List<Employee> employeeList = CsvService.parseCSV(columnMapping, "data.csv");
        JsonService.writeEmployeeToJson(employeeList, "data.json");

        final List<Employee> employeeList2 = XmlService.parseXML("data.xml");
        JsonService.writeEmployeeToJson(employeeList2, "data2.json");

        final List<Employee> employeeList3 = JsonService.jsonToList("new_data.json");
        employeeList3.forEach(System.out::println);
    }
}
