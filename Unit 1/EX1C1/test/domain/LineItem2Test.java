package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineItem2Test {

        LineItem2 lineItem1;

        @BeforeEach
        void setUp() {
            this.lineItem1 = new LineItem2(1.0, "description1");
        }


        @Test
        void copyConstructor() {
            LineItem2 lineItem2 = new LineItem2(lineItem1);
            assertEquals(this.lineItem1.getLineItemId(), lineItem2.getLineItemId());
            assertEquals(this.lineItem1.getAmount(), lineItem2.getAmount());
            assertEquals(this.lineItem1.getDescription(), lineItem2.getDescription());
//            assertNotEquals(this.lineItem1, lineItem2);
            assertFalse(lineItem1==lineItem2);
        }


        @Test
        void copy() {
            LineItem2 lineItem2 = lineItem1.copy();
            assertEquals(this.lineItem1.getLineItemId(), lineItem2.getLineItemId());
            assertEquals(this.lineItem1.getAmount(), lineItem2.getAmount());
            assertEquals(this.lineItem1.getDescription(), lineItem2.getDescription());
//            assertNotEquals(this.lineItem1, lineItem2);
            assertFalse(lineItem1==lineItem2);

        }

    @Test
    void equals() {
        LineItem2 lineItem2 = this.lineItem1.copy();
        assertTrue(lineItem1.equals(lineItem2));
    }



}
