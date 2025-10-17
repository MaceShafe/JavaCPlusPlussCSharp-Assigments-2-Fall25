package EX1D1.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class Invoice
{
    private int invoiceId;
    private int status;
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    private ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
    private Apartment apartment = null;


    /**
     *
     * @param status
     * @param invoiceDate
     * @param dueDate
     */


    public Invoice(int status, LocalDateTime invoiceDate, LocalDateTime dueDate, Apartment apartment) {
        this.invoiceId= DbContext.getNextInvoiceId();
        this.status= status;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.apartment = apartment;
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
        this.apartment = invoice.apartment;

        for (LineItem lineItem : invoice.lineItems) {
            this.lineItems.add(new LineItem(lineItem));
        }
    }

    public Invoice copy() {
        Invoice invoice = new Invoice(this.status, invoiceDate, this.dueDate, this.apartment);
        invoice.invoiceId= this.invoiceId;

        // Deep copy of line items
        invoice.lineItems = new ArrayList<>();
        for (LineItem lineItem : this.lineItems) {
            invoice.lineItems.add(new LineItem(lineItem));
        }
        return invoice;
    }

    /**
     *
     * @param lineItem
     */
    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    public void addLineItem(int index, LineItem lineItem) {
        this.lineItems.add(index, new LineItem(lineItem));
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

    public LineItem getLineItem (int index) {
        LineItem lineItem = null;
        if (index <this.lineItems.size()) {
            lineItem = lineItems.get(index).copy();
        }
        return lineItem;
    };

    public ArrayList<LineItem> getLineItems() {
        ArrayList<LineItem> lineItems = new ArrayList<>();
        for (int i=0; i<this.lineItems.size(); i++) {
            if (i > this.lineItems.size()) {
                return null;
            }
            LineItem lineItem = this.lineItems.get(i);
            lineItems.add(lineItem);
        }
    return lineItems;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getInvoiceDate() {
        return invoiceDate;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter ymdFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate.format(ymdFormat) +
                ", dueDate=" + dueDate.format(ymdFormat) +
                '}';
    }
    public String toShortString() {
        DateTimeFormatter ymdFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        return invoiceId +
                ", status: " + status +
                ", " + invoiceDate.format(ymdFormat);
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setInvoiceDate(LocalDateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Invoice invoice)) return false;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getStatus() == invoice.getStatus() &&
                this.invoiceDate.equals(invoice.getInvoiceDate()) &&
                this.dueDate.equals(invoice.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getStatus(), getInvoiceDate(), getDueDate());
    }
}
