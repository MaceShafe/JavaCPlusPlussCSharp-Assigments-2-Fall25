package domain;

import java.util.ArrayList;
import java.util.Objects;

public class Invoice
{
    private int invoiceId;
    private int status;
    private GDate invoiceDate;
    private GDate dueDate;
    private ArrayList<LineItem> lineItems;

    /**
     *
     * @param status
     * @param invoiceDate
     * @param dueDate
     */


    public Invoice(int status, GDate invoiceDate, GDate dueDate) {
    this.invoiceId= DbContext.getNextInvoiceId();
    this.status= status;
    this.invoiceDate = invoiceDate;
    this.dueDate = dueDate;
    }

    /**
     *
     * @param invoice
     */
    public Invoice(Invoice invoice) {
this.invoiceId = invoice.invoiceId;
this.status = invoice.status;
this.invoiceDate = invoice.invoiceDate;
this.dueDate = invoice.dueDate;
    }

    public Invoice copy() {
        Invoice invoice = new Invoice(this.status, this.invoiceDate, this.dueDate);
        invoice.invoiceId= this.invoiceId;
        return invoice;
    }

    /**
     *
     * @param lineItem
     */
    public void addLineItem(LineItem lineItem) {
        // TODO - implement Invoice.addLineItem
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param lineItemId
     */
    public LineItem removeLineItem(int lineItemId) {
        // TODO - implement Invoice.removeLineItem
        throw new UnsupportedOperationException();
    }

    public double total() {
        // TODO - implement Invoice.total
        throw new UnsupportedOperationException();
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getStatus() {
        return status;
    }

    public GDate getInvoiceDate() {
        return invoiceDate;
    }

    public GDate getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate +
                ", dueDate=" + dueDate +
                '}';
    }


    @Override
    public boolean equals(Object o) {
//        if  (this == o) return true;
        if (!(o instanceof Invoice invoice)) return false;
        return getInvoiceId() == invoice.getInvoiceId() && getStatus() == invoice.getStatus() && Objects.equals(getInvoiceDate(), invoice.getInvoiceDate()) && Objects.equals(getDueDate(), invoice.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getStatus(), getInvoiceDate(), getDueDate());
    }
}
