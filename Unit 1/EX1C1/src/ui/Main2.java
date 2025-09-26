package ui;


import domain.GDate2;
import domain.Invoice2;
import domain.LineItem2;

public class Main2 {
        public static void main(String[] args) {

            GDate2 date1 = new GDate2(2019,9,1);
            GDate2 date2 = new GDate2(2019,9,11);

            LineItem2 lineItem1 = new LineItem2(1000, "2019 September rent");
            System.out.println("lineItem1=" + lineItem1);
            LineItem2 lineItem2 = new LineItem2(lineItem1);
            System.out.println("lineItem2=" + lineItem2);
            LineItem2 lineItem3 = lineItem1.copy();
            System.out.println("lineItem3=" + lineItem3);
            LineItem2 lineItem4 = new LineItem2(1000, "2019 September rent");
            System.out.println("lineItem4=" + lineItem4);

            Invoice2 invoice1 = new Invoice2(1, date1, date2);
            System.out.println("invoice1=" + invoice1);
            Invoice2 invoice2 = new Invoice2(invoice1);
            System.out.println("invoice2=" + invoice2);
            Invoice2 invoice3 = invoice1.copy();
            System.out.println("invoice3=" + invoice3);
            Invoice2 invoice4 = new Invoice2(1,date1,date2);
            System.out.println("invoice4=" + invoice4);

            Invoice2 invoice5 = new Invoice2(2, date1,date2);
            LineItem2 lineItem5 = new LineItem2(1000, "2019 September rent");

            LineItem2 lineItem6 = new LineItem2(lineItem5);
            LineItem2 lineItem7 = new LineItem2(2000, "2019 September rent");
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
