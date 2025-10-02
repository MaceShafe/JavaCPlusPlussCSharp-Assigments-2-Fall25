package EX1B.ui;

import EX1B.domain.GDate;
import EX1B.domain.Invoice;

public class Main {
    public static void main(String[] args) {
    int invoiceId = 1001;
    int status = 1;
    GDate invoiceDate = new GDate();

    Invoice invoice1 =new Invoice(invoiceId, status, invoiceDate);

        System.out.println(invoice1);

        invoiceId=10002;
        status=2;
        invoiceDate.add(1);
        System.out.println(invoice1);


        Invoice invoice2 =new Invoice(invoiceId, status, 2000,1,1);





    }
}
