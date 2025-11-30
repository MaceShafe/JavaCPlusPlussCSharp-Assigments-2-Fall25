package Main.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContractAdministratorTest {
    ContractAdministrator contractAdministrator;


    @BeforeEach
    void setUp() {
        contractAdministrator = new ContractAdministrator(103, "DDDDDD", "DDDDDD", "DDDDDD.DDDDDD",
                LocalDateTime.of(2025,10,31,0,0,0),"111-11-1111","111-111-1111", LocalDateTime.of(2025,10,31,0,0,0),200.00);
//
    }

    @Test
    void testToString() {
        String result = "103 DDDDDD, DDDDDD Administrator, birthDate: 2025/10/31, ssn: 111-11-1111, phone: 111-111-1111, employmentStartDate: 2025/10/31, monthlyRate: 200.0, gross pay: 200.0";
        assertEquals(result, contractAdministrator.toString());
    }

    @Test
    void calcGrossPay() {
        assertEquals(200,this.contractAdministrator.calcGrossPay());
    }
}