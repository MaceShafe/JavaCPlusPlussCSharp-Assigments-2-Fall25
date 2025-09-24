package ui;


import domain.GDate;
import domain.Invoice;
import domain.LineItem;

public class Main {
        public static void main(String[] args) {

            GDate date1 = new GDate(2019,9,1);
            GDate date2 = new GDate(2019,9,11);

            LineItem lineItem1 = new LineItem(1000, "2019 September rent");
            System.out.println("lineItem1=" + lineItem1);
            LineItem lineItem2 = new LineItem(lineItem1);
            System.out.println("lineItem2=" + lineItem2);
            LineItem lineItem3 = lineItem1.copy();
            System.out.println("lineItem3=" + lineItem3);
            LineItem lineItem4 = new LineItem(1000, "2019 September rent");
            System.out.println("lineItem4=" + lineItem4);

            Invoice invoice1 = new Invoice(1, date1, date2);
            System.out.println("invoice1=" + invoice1);
            Invoice invoice2 = new Invoice(invoice1);
            System.out.println("invoice2=" + invoice2);
            Invoice invoice3 = invoice1.copy();
            System.out.println("invoice3=" + invoice3);
            Invoice invoice4 = new Invoice (1,date1,date2);
            System.out.println("invoice4=" + invoice4);

            Invoice invoice5 = new Invoice(2, date1,date2);
            LineItem lineItem5 = new LineItem(1000, "2019 September rent");

            LineItem lineItem6 = new LineItem(lineItem5);
            LineItem lineItem7 = new LineItem(2000, "2019 September rent");
            invoice5.addLineItem(lineItem5);
            invoice5.addLineItem(lineItem6);
            invoice5.addLineItem(lineItem7);
            System.out.println("lineItem5=" + lineItem5);
            System.out.println("lineItem6=" + lineItem6);
            System.out.println("lineItem7=" + lineItem7);
            System.out.println("Line Items 5-7 TOTAL:" + invoice5.total());

            invoice5.removeLineItem(lineItem5);
            System.out.println("Removed line Item 5, now it should be " + invoice5.total());
        }
}
