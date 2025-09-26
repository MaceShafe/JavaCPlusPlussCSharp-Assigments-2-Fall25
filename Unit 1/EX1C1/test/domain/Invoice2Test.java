package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Invoice2Test {

    Invoice2 invoice1;

    @BeforeEach
    void setUp() {
        this.invoice1 = new Invoice2(1, new GDate2(), new GDate2());
    }

    @Test
    void copyConstructor() {
        //ex1c add lineitems
        this.invoice1.addLineItem(new LineItem2(1.0, "description1"));
        this.invoice1.addLineItem(new LineItem2(2.0, "description2"));
        this.invoice1.addLineItem(new LineItem2(3.0, "description3"));
        Invoice2 invoice2 = new Invoice2(this.invoice1);
        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
        assertEquals(this.invoice1.getDueDate().julianDay(), invoice2.getDueDate().julianDay());
//        assertNotEquals(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
//        assertNotEquals(this.invoice1.getDueDate(), invoice2.getDueDate());
        assertFalse(this.invoice1.getInvoiceDate() == invoice2.getInvoiceDate());
        assertFalse(this.invoice1.getDueDate() == invoice2.getDueDate());

        // ex1c: test lineItems
        ArrayList<LineItem2> lineItems1 = this.invoice1.getLineItems();
        ArrayList<LineItem2> lineItems2 = invoice2.getLineItems();
        assertEquals(lineItems1.size(), lineItems2.size());
        int i = 0;
        LineItem lineItem2 = null;
        for (LineItem2 lineItem1 : lineItems1) {
            lineItem2 = lineItems2.get(i);
            assertEquals(lineItem1.getLineItemId(), lineItem2.getLineItemId());
            assertEquals(lineItem1.getAmount(), lineItem2.getAmount());
            assertEquals(lineItem1.getDescription(), lineItem2.getDescription());
            assertFalse(lineItem1 == lineItem2);
            i++;
        }
    }

    @Test
    void copy() {
        this.invoice1.addLineItem(new LineItem2(1.0, "description1"));
        this.invoice1.addLineItem(new LineItem2(2.0, "description2"));
        this.invoice1.addLineItem(new LineItem2(3.0, "description3"));
        Invoice2 invoice2 = this.invoice1.copy();
        assertEquals(this.invoice1.getInvoiceId(), invoice2.getInvoiceId());
        assertEquals(this.invoice1.getStatus(), invoice2.getStatus());
        assertEquals(this.invoice1.getInvoiceDate().julianDay(), invoice2.getInvoiceDate().julianDay());
//        assertNotEquals(this.invoice1.getInvoiceDate(), invoice2.getInvoiceDate());
//        assertNotEquals(this.invoice1.getDueDate(), invoice2.getDueDate());
        assertFalse(this.invoice1.getInvoiceDate() == invoice2.getInvoiceDate());
        assertFalse(this.invoice1.getDueDate() == invoice2.getDueDate());

        // ex1c: test lineItems
        ArrayList<LineItem> lineItems1 = this.invoice1.getLineItems();
        ArrayList<LineItem> lineItems2 = invoice2.getLineItems();
        assertEquals(lineItems1.size(), lineItems2.size());
        int i = 0;
        LineItem lineItem2 = null;
        for (LineItem lineItem1 : lineItems1) {
            lineItem2 = lineItems2.get(i);
            assertEquals(lineItem1.getLineItemId(), lineItem2.getLineItemId());
            assertEquals(lineItem1.getAmount(), lineItem2.getAmount());
            assertEquals(lineItem1.getDescription(), lineItem2.getDescription());
            assertFalse(lineItem1 == lineItem2);
            i++;
        }
    }

//    @Test
//    void addLineItem() {
//    }

    @Test
    void removeLineItem() {
        LineItem2 lineItem1 = new LineItem2(1.0, "description1");

        // test Invoice.removeLineItem(int index)
        this.invoice1.addLineItem(lineItem1);
        LineItem2 removedLineItem = this.invoice1.removeLineItem(0);
        assertEquals(lineItem1, removedLineItem);
        assertFalse(lineItem1 == removedLineItem);
        // test removing from empty ArrayList
        removedLineItem = this.invoice1.removeLineItem(0);
        assertEquals(null, removedLineItem);

        // test Invoice.removeLineItem(LineItem lineItem)
        this.invoice1.addLineItem(lineItem1);
        removedLineItem = this.invoice1.removeLineItem(lineItem1);
        assertEquals(lineItem1, removedLineItem);
        assertFalse(lineItem1 == removedLineItem);
        // test removing from empty ArrayList
        removedLineItem = this.invoice1.removeLineItem(lineItem1);
        assertEquals(null, removedLineItem);
    }

    @Test
    void total() {
        LineItem2 lineItem1 = new LineItem2(5.0, "description1");
        LineItem2 lineItem2 = new LineItem2(2.0, "description1");
        this.invoice1.addLineItem(lineItem1);
        this.invoice1.addLineItem(lineItem2);
        double total = this.invoice1.total();
        assertEquals(7.0,total);

    }
}