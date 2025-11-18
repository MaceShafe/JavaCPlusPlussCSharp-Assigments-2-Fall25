package Main.ui;

import Main.domain.Person;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    static String json = "{\n" +
            "    \"person\": {\n" +
            "      \"personId\": 101,\n" +
            "      \"lastName\": \"Smith\",\n" +
            "      \"firstName\": \"John\",\n" +
            "      \"userName\": \"john.smith\"\n" +
            "\n" +
            "    }\n" +
            "}";

    public static void main (String[] args) {
        JSONObject obj = new JSONObject(json);
        int personId = obj.getJSONObject("person").getInt("personId");
        String firstName = obj.getJSONObject("person").getString("firstName");
        String lastName = obj.getJSONObject("person").getString("lastName");
        String userName = obj.getJSONObject("person").getString("userName");
        Person person = new Person(personId, firstName, lastName, userName);

//        System.out.println("Person: " +personId + " " + firstName + " " +   lastName + " " + userName);
        System.out.println("Person: " + person);

    }
}
