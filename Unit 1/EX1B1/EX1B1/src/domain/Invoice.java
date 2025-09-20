package domain;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Objects;

public class Invoice
{
    private int invoiceId;
    private int status;
    private GDate invoiceDate;
    private GDate dueDate;
    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();

    /**
     *
     * @param status
     * @param invoiceDate
     * @param dueDate
     */


    public Invoice(int status, GDate invoiceDate, GDate dueDate) {
    this.invoiceId= DbContext.getNextInvoiceId();
    this.status= status;
    this.invoiceDate = new GDate(invoiceDate);
    this.dueDate = new GDate(dueDate);
    }

    /**
     *
     * @param invoice
     */
    public Invoice(Invoice invoice) {
this.invoiceId = invoice.invoiceId;
this.status = invoice.status;
this.invoiceDate = new GDate(invoice.invoiceDate);
this.dueDate = new GDate (invoice.dueDate);
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
        this.lineItems.add(lineItem);
    }

    /**
     *
     * @param lineItemId
     */
    public LineItem removeLineItem(int lineItemId) {
        LineItem lineItem = null;
        if (lineItemId >= 0 && lineItemId < this.lineItems.size())
        {
            lineItem = new LineItem(this.lineItems.get(lineItemId));
            this.lineItems.remove(lineItemId);

        }
    return lineItem;
    }

    public LineItem removeLineItem(LineItem lineItem) {
        LineItem removedLineItem = null;
        int index = lineItems.indexOf(lineItem);
        if (index >= 0 && index < this.lineItems.size()) {
            removedLineItem = new LineItem(this.lineItems.get(index));
            this.lineItems.remove(index);
        }
        return removedLineItem;

    }


    public double total() {

        double total = 0;
        for (int i=0; i<lineItems.size(); i++) {
        LineItem lineItem = this.lineItems.get(i);
        double amount = lineItem.getAmount();
        total += amount ;
        }
        return total;
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
