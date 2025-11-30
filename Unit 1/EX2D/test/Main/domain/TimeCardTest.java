package Main.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimeCardTest {
    TimeCard timeCard;

    @BeforeEach
    void setUp() {
        this.timeCard=new TimeCard(LocalDateTime.of(2018,10,22,8,0),
                LocalDateTime.of(2018,10,22,10,0));
    }

    @Test
    void copy() {
        TimeCard timeCard2 =  this.timeCard.copy();
        assertEquals(this.timeCard,timeCard2);
    }

    @Test
    void calcHours() {

        assertEquals(timeCard.calcHours(),2);
    }

    @Test
    void testToString() {
        String strTimeCard=this.timeCard.toString();
        int i = strTimeCard.indexOf("startDateTime");
        strTimeCard=strTimeCard.substring(i);
        String result = "startDateTime=2018/10/22 08:00AM, endDateTime=2018/10/22 10:00AM, hours=2.00";
        assertEquals(result,strTimeCard);
    }
}