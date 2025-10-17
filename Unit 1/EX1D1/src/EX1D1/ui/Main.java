package EX1D1.ui;


import EX1D1.domain.DbContext;
import EX1D1.domain.Invoice;
import EX1D1.domain.LineItem;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
            ArrayList<Invoice> invoices = DbContext.getInvoices();
            for (Invoice invoice : invoices) {
                System.out.println(invoice.toShortString());
            }
        }
}
