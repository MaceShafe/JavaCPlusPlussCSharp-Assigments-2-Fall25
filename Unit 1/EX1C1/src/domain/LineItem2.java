package domain;


import java.util.Objects;

public class LineItem2 {
    private int lineItemId;
    private double amount;
    private String description;

    /**
     *
     * @param amount
     * @param description
     */
    public LineItem2(double amount, String description) {
        this.lineItemId = DbContext2.getNextLineItemId();
        this.amount = amount;
        this.description = description;
    }

    /**
     *
     * @param lineItem
     */
    public LineItem2(LineItem2 lineItem) {
        this.lineItemId = lineItem.getLineItemId();
        this.amount = lineItem.amount;
        this.description = lineItem.description;

    }

    public LineItem2 copy() {
        return new LineItem2(this);
    }

    public int getLineItemId() {
        return lineItemId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "LineItem{" +
                "lineItemId=" + lineItemId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
//        if (this == o) return true;
        if (!(o instanceof LineItem2 lineItem)) return false;
        return getLineItemId() == lineItem.getLineItemId() &&
                Double.compare(getAmount(), lineItem.getAmount()) == 0 &&
                Objects.equals(getDescription(), lineItem.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLineItemId(), getAmount(), getDescription());
    }
}
