package domain;

import java.util.Arrays;

public class Invoice {
    private int invoiceId;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private int status;
    private GDate invoiceDate;


    public Invoice(int invoiceId, int status, GDate invoiceDate) {
        this.invoiceId = invoiceId;
        this.status = status;
//        this.invoiceDate = invoiceDate;
//        this.invoiceDate = new GDate(invoiceDate);
        this.invoiceDate = invoiceDate.copy();
    }

    public Invoice(int invoiceId, int status, int year, int month, int day) {
        this.invoiceId = invoiceId;
        this.status = status;
//        this.invoiceDate = invoiceDate;
//        this.invoiceDate = new GDate(invoiceDate);
        this.invoiceDate = new GDate(year, month, day);
    }



    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate +
//                ", dueDate=" + dueDate +
//                ", lineItems=" + Arrays.toString(lineItems) +
                '}';
    }
}