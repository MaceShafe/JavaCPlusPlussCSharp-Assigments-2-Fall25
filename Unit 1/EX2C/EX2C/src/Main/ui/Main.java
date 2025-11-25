package Main.ui;

import Main.dataAccess.PeopleJSONParser;
import Main.domain.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
//    private static String json = "{\n" +
//            "    \"person\": {\n" +
//            "      \"personId\": 101,\n" +
//            "      \"lastName\": \"Smith\",\n" +
//            "      \"firstName\": \"John\",\n" +
//            "      \"userName\": \"john.smith\"\n" +
//            "\n" +
//            "    }\n" +
//            "}";


    public static void main(String[] args) {

        ArrayList<Person> people = new ArrayList<>();
        Person person = null;
//        Tenant tenant = null;
//        ContractAdministrator contractAdministrator = null;
//        HourlyAdministrator hourlyAdministrator = null;



//        System.out.println("Person: " +personId + " " + firstName + " " +   lastName + " " + userName);
        try {
            PeopleJSONParser.readFile("Unit 1/EX2C/EX2C/resources/people.json");

//            person = PeopleJSONParser.getPerson();
//            if (person != null) people.add(person);
//            tenant = PeopleJSONParser.getTenant();
//            if (tenant != null) people.add(tenant);
//            contractAdministrator = PeopleJSONParser.getContractAdministrator();
//            if (contractAdministrator != null) people.add(contractAdministrator);
//            hourlyAdministrator = PeopleJSONParser.getHourlyAdministrator();
//            if (hourlyAdministrator != null) people.add(hourlyAdministrator);
            people = PeopleJSONParser.getPeople();

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");

        }
        catch (IOException e) {
            System.out.println("I/O Error Occurred");
        }




        for (Person p : people) {
            System.out.println(p);
            if (p.getClass() == HourlyAdministrator.class) {
                HourlyAdministrator hourlyAdministrator = (HourlyAdministrator) p;
                ArrayList<TimeCard> timeCards = hourlyAdministrator.getTimeCards();
                for (TimeCard timeCard : timeCards) {
                    System.out.println("\t" + timeCard);
                }
            }
        }
    }



}
