package main.domain;

import Main.domain.HourlyAdministrator;
import Main.domain.TimeCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HourlyAdministratorTest {
    HourlyAdministrator hourlyAdmin;
    @BeforeEach
    void setUp() {
        this.hourlyAdmin = new HourlyAdministrator(
                105, "Eeee", "Eeeee", "eeee.eeeee",
                LocalDateTime.of(2000, 1, 1, 0, 0, 0),
                "555-55-5555", "555-555-5555",
                LocalDateTime.of(2000, 1, 1, 0, 0, 0),
                50.00);
    }


    @Test
    void addTimeCard() {
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));

        TimeCard timeCard = hourlyAdmin.getTimeCard(0);
        int i = timeCard.toString().indexOf("startDateTime");
        String strTimeCard = timeCard.toString();
        strTimeCard=strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 10:00AM, hours=2.00";
        assertEquals(result,strTimeCard);
//        String strTimeCard=hourlyAdmin.timeCard.toString();
//        int i = strTimeCard.indexOf("startDateTime");
//        strTimeCard=strTimeCard.substring(i);
//        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 10:00AM, hours=2.00";
//        assertEquals(result,strTimeCard);

    }

    @Test
    void getTimeCard() {


        TimeCard timeCard = this.hourlyAdmin.getTimeCard(0);
        assertNull(timeCard);

        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));
        timeCard = this.hourlyAdmin.getTimeCard(0);
        int i = timeCard.toString().indexOf("startDateTime");

        String strTimeCard = timeCard.toString();
        strTimeCard=strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 10:00AM, hours=2.00";
        assertEquals(result,strTimeCard);

//        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
//                LocalDateTime.of(2018,10,22,10,0));
//
//        assertTrue(this.hourlyAdmin.getTimeCards() != null);

//        104 EEEEEE, EEEEEE Administrator, birthDate: 2025/10/31, ssn: 111-11-1111, phone: 111-111-1111, employmentStartDate: 2025/10/31 hourlyRate:  50.0, total hours:  8.0, gross pay: 400.0
//	ID=10002, startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 10:00AM, hours=2.00
    }

    @Test
    void removeTimeCard() {
        TimeCard timeCard = this.hourlyAdmin.removeTimeCard(0);
        assertNull(timeCard);

        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));
        timeCard = this.hourlyAdmin.removeTimeCard(0);
        int i = timeCard.toString().indexOf("startDateTime");
        String strTimeCard = timeCard.toString();
        strTimeCard=strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 10:00AM, hours=2.00";
        assertEquals(result,strTimeCard);
        ArrayList<TimeCard> timeCards = hourlyAdmin.getTimeCards();
        assertEquals(timeCards.size(),0);

//        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
//                LocalDateTime.of(2018,10,22,10,0));
    }



    @Test
    void getTimeCards() {

        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));

        ArrayList<TimeCard> timeCards = this.hourlyAdmin.getTimeCards();
        for (int i = 0; i<timeCards.size();i++) {
            assertEquals(this.hourlyAdmin.getTimeCard(i), timeCards.get(i));
            assertTrue(this.hourlyAdmin.getTimeCard(i) == timeCards.get(i));
        }

//        assertEquals(.size(),2);

    }

    @Test
    void calcTotalHours() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));

        assertEquals(this.hourlyAdmin.calcTotalHours(),2);
    }

    @Test
    void calcGrossPay() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));

        double grossPay = this.hourlyAdmin.calcGrossPay();
        assertEquals(grossPay, 100);
    }

    @Test
    void testToString() {
        assertEquals(hourlyAdmin.toString(),"104 EEEEEE, EEEEEE Administrator, birthDate: 2025/11/01, ssn: 111-11-1111, phone: 111-111-1111, employmentStartDate: 2025/11/01 hourlyRate:  50.0, total hours:  0.0, gross pay: 0.0");

    }

    @Test
    void jsonStringifyTest() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 22, 8, 0),
                LocalDateTime.of(2018, 10, 22, 18, 0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018, 10, 23, 8, 0),
                LocalDateTime.of(2018, 10, 23, 18, 0));

        assertEquals("{\n" +
                "\"subclass\":\"hourlyAdministrator\",\n" +
                "\"personId\":105,\n" +
                "\"lastName\":\"Eeeee\",\n" +
                "\"firstName\":\"Eeee\",\n" +
                "\"userName\":\"eeee.eeeee\",\n" +
                "\"birthDate\":\"2000/01/01\",\n" +
                "\"ssn\":\"555-55-5555\",\n" +
                "\"phone\":\"555-555-5555\",\n" +
                "\"employmentStartDate\":\"2000/01/01\",\n" +
                "\"hourlyRate\":50.0,\n" +
                "\"timeCards\":[\n" +
                "{\"id\":10001,\"startDateTime\":\"2018/10/22 08:00AM\",\"endDateTime\":\"2018/10/22 06:00PM\"},\n" +
                "{\"id\":10002,\"startDateTime\":\"2018/10/23 08:00AM\",\"endDateTime\":\"2018/10/23 06:00PM\"}\n" +
                "]\n" +
                "}", this.hourlyAdmin.jsonStringify());
    }
}

//            HourlyAdministrator hourlyAdministrator = new HourlyAdministrator(104, "EEEEEE", "EEEEEE", "EEEEEE.EEEEEE",
//                    LocalDateTime.now(),"111-11-1111","111-111-1111", LocalDateTime.now(),50.00);