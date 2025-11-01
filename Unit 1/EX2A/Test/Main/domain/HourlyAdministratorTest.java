package Main.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class HourlyAdministratorTest {
HourlyAdministrator hourlyAdmin;
    @BeforeEach
    void setUp() {
        hourlyAdmin = new HourlyAdministrator(104, "EEEEEE", "EEEEEE", "EEEEEE.EEEEEE",
                LocalDateTime.now(),"111-11-1111","111-111-1111", LocalDateTime.now(),50.00);

    }


    @Test
    void addTimeCard() {
        hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));

        assertEquals(hourlyAdmin.getTimeCards().size(),1);
    }

    @Test
    void removeTimeCard() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));
        this.hourlyAdmin.removeTimeCard(0);
        assertEquals(this.hourlyAdmin.getTimeCards().size(),0);

    }

    @Test
    void getTimeCard() {
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));

        assertTrue(this.hourlyAdmin.getTimeCards() != null);

//        104 EEEEEE, EEEEEE Administrator, birthDate: 2025/10/31, ssn: 111-11-1111, phone: 111-111-1111, employmentStartDate: 2025/10/31 hourlyRate:  50.0, total hours:  8.0, gross pay: 400.0
//	ID=10002, startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 10:00AM, hours=2.00
    }

    @Test
    void getTimeCards() {

        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));
        this.hourlyAdmin.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));

        assertEquals(this.hourlyAdmin.getTimeCards().size(),2);

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
        assertEquals(hourlyAdmin.toString(),"104 EEEEEE, EEEEEE Administrator, birthDate: 2025/10/31, ssn: 111-11-1111, phone: 111-111-1111, employmentStartDate: 2025/10/31 hourlyRate:  50.0, total hours:  0.0, gross pay: 0.0");

    }
}

//            HourlyAdministrator hourlyAdministrator = new HourlyAdministrator(104, "EEEEEE", "EEEEEE", "EEEEEE.EEEEEE",
//                    LocalDateTime.now(),"111-11-1111","111-111-1111", LocalDateTime.now(),50.00);