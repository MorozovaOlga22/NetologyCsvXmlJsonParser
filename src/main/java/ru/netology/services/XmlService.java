package ru.netology.services;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.netology.Employee;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlService {
    public static List<Employee> parseXML(String fileName) throws IOException, SAXException, ParserConfigurationException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document doc = builder.parse(new File(fileName));

        final List<Employee> resultList = new ArrayList<>();
        final NodeList employees = doc.getElementsByTagName("employee");
        for (int i = 0; i < employees.getLength(); i++) {
            final Element employee = (Element) employees.item(i);
            resultList.add(parseEmployee(employee));
        }
        return resultList;
    }

    private static Employee parseEmployee(Element employee) {
        final long id = Long.parseLong(getElementText(employee, "id"));
        final String firstName = getElementText(employee, "firstName");
        final String lastName = getElementText(employee, "lastName");
        final String country = getElementText(employee, "country");
        final int age = Integer.parseInt(getElementText(employee, "age"));

        return new Employee(id, firstName, lastName, country, age);
    }

    private static String getElementText(Element employee, String elementName) {
        return employee.getElementsByTagName(elementName).item(0).getTextContent();
    }
}
