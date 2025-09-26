package domain;

import java.util.ArrayList;
import java.util.Objects;

public class Invoice2
{
    private int invoiceId;
    private int status;
    private GDate2 invoiceDate;
    private GDate2 dueDate;
    private ArrayList<LineItem2> lineItems = new ArrayList<LineItem2>();

    /**
     *
     * @param status
     * @param invoiceDate
     * @param dueDate
     */


    public Invoice2(int status, GDate2 invoiceDate, GDate2 dueDate) {
    this.invoiceId= DbContext2.getNextInvoiceId();
    this.status= status;
    this.invoiceDate = new GDate2(invoiceDate);
    this.dueDate = new GDate2(dueDate);
    }

    /**
     *
     * @param invoice
     */
    public Invoice2(Invoice2 invoice) {
this.invoiceId = invoice.invoiceId;
this.status = invoice.status;
this.invoiceDate = new GDate2(invoice.invoiceDate);
this.dueDate = new GDate2(invoice.dueDate);
    }

    public Invoice2 copy() {
//        Invoice2 invoice = new Invoice2(this.status, this.invoiceDate, this.dueDate);
//        invoice.invoiceId= this.invoiceId;
//        return invoice;

        return new Invoice2(this);
    }

    /**
     *
     * @param lineItem
     */
    public void addLineItem(LineItem2 lineItem) {
        this.lineItems.add(lineItem);
    }

    /**
     *
     * @param lineItemId
     */
    public LineItem2 removeLineItem(int lineItemId) {
        LineItem2 lineItem = null;
        if (lineItemId >= 0 && lineItemId < this.lineItems.size())
        {
            lineItem = new LineItem2(this.lineItems.get(lineItemId));
            this.lineItems.remove(lineItemId);

        }
    return lineItem;
    }

    public LineItem2 removeLineItem(LineItem2 lineItem) {
        LineItem2 removedLineItem = null;
        int index = lineItems.indexOf(lineItem);
        if (index >= 0 && index < this.lineItems.size()) {
            removedLineItem = new LineItem2(this.lineItems.get(index));
            this.lineItems.remove(index);
        }
        return removedLineItem;

    }


    public double total() {

        double total = 0;
        for (int i=0; i<lineItems.size(); i++) {
        LineItem2 lineItem = this.lineItems.get(i);
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

    public GDate2 getInvoiceDate() {
        return invoiceDate.copy();
    }

    public GDate2 getDueDate() {
        return dueDate.copy();
    }

    public LineItem2 getLineItem(int lineItemId) {}

    public ArrayList<LineItem2> getLineItems() {
        return lineItems;
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
        if (!(o instanceof Invoice2 invoice)) return false;
        return getInvoiceId() == invoice.getInvoiceId() && getStatus() == invoice.getStatus() && Objects.equals(getInvoiceDate(), invoice.getInvoiceDate()) && Objects.equals(getDueDate(), invoice.getDueDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getStatus(), getInvoiceDate(), getDueDate());
    }
}
