package Main.dataAccess;

import Main.domain.Person;
import Main.domain.Tenant;
import Main.domain.HourlyAdministrator;
import Main.domain.ContractAdministrator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class PeopleJSONParser {
    private static String json = "{}";
    private static ArrayList<Exception>  exceptions = new ArrayList<Exception>();

    public static void readFile(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                ///note to self look into file readers and string builders more
                new FileReader(path)))

        {
            String line = "";
            StringBuilder sbJSON = new StringBuilder(400);
            while ((line = reader.readLine()) != null) {
                sbJSON.append(line + "\n");
            }
            json = sbJSON.toString();

        }
    }

    public static ArrayList<Person> getPeople() {
        ArrayList<Person> people = new ArrayList<Person>();

        JSONObject obj = new JSONObject(json);
        JSONArray jsonPeople = obj.getJSONArray("people");
        if (jsonPeople != null) {
            for (int i = 0; i < jsonPeople.length(); i++) {
                try{
                JSONObject jsonPerson = jsonPeople.getJSONObject(i);
                String subclass = jsonPerson.getString("subclass");
                switch (subclass) {
                    case "person":
                        Person person = getPerson(jsonPerson);
                        if (person!=null) people.add(person);
                        break;

                    case "tenant":
                        Tenant tenant = getTenant(jsonPerson);
                        if (tenant!=null) people.add(tenant);
                        break;

                    case "contractAdministrator":
                        ContractAdministrator contractAdministrator = getContractAdministrator(jsonPerson);
                        if (contractAdministrator!=null) people.add(contractAdministrator);
                        break;

                    case "hourlyAdministrator":
                        HourlyAdministrator hourlyAdministrator = getHourlyAdministrator(jsonPerson);
                        if (hourlyAdministrator!=null) people.add(hourlyAdministrator);
                        break;
                }
                } catch (JSONException e) {
//                    String msg = "Except #" + i + ":" + e.toString();
//                    if(e.getCause() != null) msg += "\n\t"+e.getCause();
//                    System.out.println(msg);
                    exceptions.add(e);
                } catch(DateTimeParseException e){
//                    String msg = "Except #" + i + ":" + e.toString();
//                    if(e.getCause() != null) msg += "\n\t"+e.getCause();
//                    System.out.println(msg);
                    exceptions.add(e);
                } catch(IllegalArgumentException e){
//                    String msg = "Except #" + i + ":" + e.toString();
//                    if(e.getCause() != null) msg += "\n\t"+e.getCause();
//                    System.out.println(msg);
                    exceptions.add(e);
                }

            }
        }


        return people;
    }

    public static ArrayList<Exception> getExceptions() {
        return exceptions;
    }
        public static Person getPerson(JSONObject jsonPerson) {
            //declaring as null so that if json data is read incorrectly it just defaults as null
        Person person = null;
    try{
//        JSONObject obj = new JSONObject(json);
        int personId = jsonPerson.getInt("personId");
        String firstName = jsonPerson.getString("firstName");
        String lastName = jsonPerson.getString("lastName");
        String userName = jsonPerson.getString("userName");

        person = new Person(personId, firstName, lastName, userName);

    } catch (JSONException e) {
        String msg = e.toString();
        msg += "\n\t"+e.getCause();
        System.out.println(msg);
    } catch(DateTimeParseException e){
        String msg = e.toString();
        if(e.getCause() != null) msg += "\n\t"+e.getCause();
        System.out.println(msg);
    }
        return person;
    }

    public static Tenant getTenant(JSONObject jsonTenant) {
            //declaring as null so that if json data is read incorrectly it just defaults as null
        Tenant tenant = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

//        JSONObject obj = new JSONObject(json);
            int personId = jsonTenant.getInt("personId");
            String firstName = jsonTenant.getString("firstName");
            String lastName = jsonTenant.getString("lastName");
            String userName = jsonTenant.getString("userName");
            String strBirthDate = jsonTenant.getString("birthDate");
            LocalDate birthDate = LocalDate.parse(strBirthDate, formatter);
            LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));
            String ssn = jsonTenant.getString("ssn");
            String phone = jsonTenant.getString("phone");
            String employer = jsonTenant.getString("employer");
            String occupation = jsonTenant.getString("occupation");
            Double grossPay = jsonTenant.getDouble("grossPay");
            String strEmploymentStartDate = jsonTenant.getString("employmentStartDate");
            LocalDate employmentStartDate = LocalDate.parse(strEmploymentStartDate, formatter);
            LocalDateTime employmentStartDateTime = LocalDateTime.of(employmentStartDate, LocalTime.of(0, 0));

            tenant = new Tenant(personId, firstName, lastName, userName, birthDateTime, ssn, phone, employer, occupation, grossPay, employmentStartDateTime);



        return tenant;
    }

    public static ContractAdministrator getContractAdministrator(JSONObject jsonContractAdmin) {
        //declaring as null so that if json data is read incorrectly it just defaults as null
        ContractAdministrator contractAdministrator = null;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");


//        JSONObject obj = new JSONObject(json);
        int personId = jsonContractAdmin.getInt("personId");
        String firstName = jsonContractAdmin.getString("firstName");
        String lastName = jsonContractAdmin.getString("lastName");
        String userName = jsonContractAdmin.getString("userName");
        String strBirthDate = jsonContractAdmin.getString("birthDate");
        LocalDate birthDate = LocalDate.parse(strBirthDate, formatter);
        LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0,0));
        String  ssn = jsonContractAdmin.getString("ssn");
        String phone = jsonContractAdmin.getString("phone");
        String strEmploymentStartDate = jsonContractAdmin.getString("employmentStartDate");
        LocalDate employmentStartDate = LocalDate.parse(strEmploymentStartDate, formatter);
        LocalDateTime employmentStartDateTime = LocalDateTime.of(employmentStartDate, LocalTime.of(0,0));
        double monthlyRate = jsonContractAdmin.getDouble("monthlyRate");

        contractAdministrator = new ContractAdministrator(personId,firstName,lastName,userName,birthDateTime,ssn,phone,employmentStartDateTime,monthlyRate);

        return contractAdministrator;
    }

    public static HourlyAdministrator getHourlyAdministrator(JSONObject jsonHourlyAdmin) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");


//        JSONObject obj = new JSONObject(json);
//        JSONObject jsonHourlyAdmin = obj.getJSONObject("hourlyAdministrator");

        HourlyAdministrator hourlyAdministrator = null;
            int personId = jsonHourlyAdmin.getInt("personId");
            String firstName = jsonHourlyAdmin.getString("firstName");
            String lastName = jsonHourlyAdmin.getString("lastName");
            String userName = jsonHourlyAdmin.getString("userName");
            String strBirthDate = jsonHourlyAdmin.getString("birthDate");
            LocalDate birthDate = LocalDate.parse(strBirthDate, formatter);
            LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));
            String ssn = jsonHourlyAdmin.getString("ssn");
            String phone = jsonHourlyAdmin.getString("phone");
            String strEmploymentStartDate = jsonHourlyAdmin.getString("employmentStartDate");
            LocalDate employmentStartDate = LocalDate.parse(strEmploymentStartDate, formatter);
            LocalDateTime employmentStartDateTime = LocalDateTime.of(employmentStartDate, LocalTime.of(0, 0));
            double hourlyRate = jsonHourlyAdmin.getDouble("hourlyRate");

            //declaring as null so that if json data is read incorrectly it just defaults as null
            hourlyAdministrator = null;

            hourlyAdministrator = new HourlyAdministrator(personId, firstName, lastName, userName,
                    birthDateTime, ssn, phone, employmentStartDateTime, hourlyRate);

            JSONArray jsonTimeCards = jsonHourlyAdmin.getJSONArray("timeCards");
            if (jsonTimeCards != null) {
                for (int i = 0; i < jsonTimeCards.length(); i++) {
                    JSONObject jsonTimeCard = jsonTimeCards.getJSONObject(i);
                    int id = jsonTimeCard.getInt("id");
                    LocalDateTime startDateTime =
                            LocalDateTime.parse(jsonTimeCard.getString("startDateTime"), dtFormatter);
                    LocalDateTime endDateTime =
                            LocalDateTime.parse(jsonTimeCard.getString("endDateTime"), dtFormatter);
                    hourlyAdministrator.addTimeCard(id, startDateTime, endDateTime);
                }
            }

        return hourlyAdministrator;
    }

}
