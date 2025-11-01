package Main.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TenantTest {
Tenant tenant = null;

    @BeforeEach
    void setUp() {
        this.tenant = new Tenant(102, "BBBBB", "BBBBB", "BBBB.BBBB",
                LocalDateTime.of(2025,10,30,0,0,0), "111-11-1111","111-111-1111","Minnesota State College Southeast",
                "Instructor",10000.00,LocalDateTime.of(2025,10,31,0,0,0));
    }

    @Test
    void testToString() {
        String result = "102 BBBBB, BBBBB birthDate: 2025/10/30, ssn: 111-11-1111, phone: 111-111-1111, employer: Minnesota State College Southeast, occupation: Instructor, grossPay: 10000.0, employmentStartDate: 2025/10/31";
        assertEquals(result, this.tenant.toString());
    }

}