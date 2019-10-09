package com.company;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import java.util.Scanner;

public class EmployeeMain {
    final static private String DIRECTORY_PATH = "C:\\Users\\msinghi\\Documents\\day1\\GCE_db";
    final static private String XML_FILE = "Employee.xml";
    final static private String JSON_FILE = "Employee.txt";
    final static private String CSV_FILE = "Employee.csv";

    public static void main(String[] args) throws ClassNotFoundException {
        Dbconnect dbconnect = new Dbconnect();
        List<Employee>emps=dbconnect.getEmployees();
        Employees employees = new Employees();
        employees.setEmployees(emps);

        System.out.println("1 for XML");
        System.out.println("2 for JSON");
        System.out.println("3 for CSV");

        Scanner sc = new Scanner(System.in);
        int ch = sc.nextInt();

        switch (ch){
            case 1:
                WriteToXML(employees);
                System.out.println("file type 1");
                break;
            case 2:
                WriteToJSON(employees.getEmployees());
                System.out.println("file type 2");
                break;
            case 3:
                WriteToCSV(employees.getEmployees());
                System.out.println("file type 3");
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + ch);
        }
        sc.close();
    }

    private static void WriteToCSV(List<Employee> employees) {
        String csvDataToWrite="";
        for(Employee employee:employees){
            csvDataToWrite += employee.toString();
        }
        FileWriteHandler fileWriteHandler = new FileWriteHandler();
        fileWriteHandler.writeDataToFile(csvDataToWrite,DIRECTORY_PATH + "\\" + CSV_FILE);
    }

    private static void WriteToJSON(List<Employee> employees) {
        String jsonDataToWrite="";
        for(Employee employee:employees){
            ObjectToJsonConverter objectToJsonConverter = new ObjectToJsonConverter();
            jsonDataToWrite +=objectToJsonConverter.toJson(employee);
        }
        FileWriteHandler fileWriteHandler = new FileWriteHandler();
        fileWriteHandler.writeDataToFile(jsonDataToWrite,DIRECTORY_PATH + "\\" + JSON_FILE);
    }

    private static void WriteToXML(Employees employees) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Employees.class);
            Marshaller jabxMarshaller = jaxbContext.createMarshaller();
            jabxMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            //jabxMarshaller.marshal(employees, System.out);

            jabxMarshaller.marshal(employees,new File(DIRECTORY_PATH + "\\" + XML_FILE));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
