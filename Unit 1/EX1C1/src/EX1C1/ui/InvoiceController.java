package EX1C1.ui;

import EX1C1.domain.GDate;
import EX1C1.domain.Invoice;
import EX1C1.domain.LineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class InvoiceController {

    public Button saveInvoiceButton;
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

    @FXML
    private TextField invoiceIdTextField;
    @FXML
    private TextField statusTextField;
    @FXML
    private TextField invoiceDateTextField;
    @FXML
    private TextField dueDateTextField;
    @FXML
    public TextField descriptionTextField;
    @FXML
    public TextField amountTextField;
    @FXML
    public ListView lineItemsListView;
    @FXML
    private ComboBox invoicesComboBox;


    public InvoiceController() {


    GDate date1 = new GDate(2019,9,1);
    GDate date2 = new GDate(2019,9,11);

    Invoice invoice1 = new Invoice(1, date1, date2);

    invoice1.addLineItem(new LineItem(1.0, "description1"));
    invoice1.addLineItem(new LineItem(2.0, "description2"));
    invoice1.addLineItem(new LineItem(3.0, "description3"));
    invoice1.addLineItem(new LineItem(4.0, "description4"));
    this.invoices.add(invoice1);

    Invoice invoice2 = new Invoice(2,
            new GDate(2025,10,15),
            new GDate(2025,11,1));
        invoice2.addLineItem(new LineItem(5.0, "description1"));
        invoice2.addLineItem(new LineItem(6.0, "description2"));
        invoice2.addLineItem(new LineItem(7.0, "description3"));
        invoice2.addLineItem(new LineItem(8.0, "description4"));
    this.invoices.add(invoice2);
}

@FXML
  void displayInvoice(Invoice invoice) {
     this.invoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
     this.statusTextField.setText(Integer.toString(invoice.getStatus()));
     this.invoiceDateTextField.setText(invoice.getInvoiceDate().toString());
     this.dueDateTextField.setText(invoice.getDueDate().toString());
 }


    @FXML
    protected void initialize() {

        for (Invoice invoice : this.invoices) {
            invoicesComboBox.getItems().add(invoice.toShortString());
        }
        invoicesComboBox.getSelectionModel().selectFirst();
        Invoice invoice = this.invoices.get(0);
        this.displayInvoice(invoice);
        this.displayLineItems(invoice);

    }
private void displayLineItem(LineItem lineItem) {
        this.descriptionTextField.setText(lineItem.getDescription());
        this.amountTextField.setText(Double.toString(lineItem.getAmount()));

}

    public void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
        int comboIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();

        Invoice invoice = this.invoices.get(comboIndex);
        this.displayInvoice(invoice);
        this.displayLineItems(invoice);
    }

    public void lineItemsListViewClicked(MouseEvent mouseEvent) {
        LineItem lineItem = getSelectedLineItem();
        displayLineItem(lineItem);

    }


    private LineItem getSelectedLineItem() {
        int comboIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        int listIndex = lineItemsListView.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(comboIndex);
        LineItem lineItem = invoice.getLineItem(listIndex);
        return lineItem;
    }


    private void displayLineItems(Invoice invoice) {
        this.lineItemsListView.getItems().clear();
        ArrayList<LineItem> lineItems = invoice.getLineItems();
        this.descriptionTextField.clear();
        this.amountTextField.clear();
        for (LineItem lineItem : lineItems) {
            this.lineItemsListView.getItems().add(lineItem);
        }
        this.lineItemsListView.getSelectionModel().selectFirst();
        if (lineItems.size() > 0) {
            for (LineItem lineItem : lineItems) {
                displayLineItem(lineItem);
            }
        }

    }

    public void saveInvoiceButtonClicked(ActionEvent actionEvent) {
        int comboIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();


    Invoice invoice = invoices.get(comboIndex);

    invoice.setStatus(Integer.parseInt(statusTextField.getText()));
    GDate invoiceDate = new GDate(
            Integer.parseInt(this.invoiceDateTextField.getText().substring(0,4)),
            Integer.parseInt(this.invoiceDateTextField.getText().substring(5,7)),
            Integer.parseInt(this.invoiceDateTextField.getText().substring(8,10))
    );
    GDate dueDate = new GDate(
            Integer.parseInt(this.dueDateTextField.getText().substring(0,4)),
            Integer.parseInt(this.dueDateTextField.getText().substring(5,7)),
            Integer.parseInt(this.dueDateTextField.getText().substring(8,10))
    );

    invoice.setInvoiceDate(invoiceDate);
    invoice.setDueDate(dueDate);



        this.invoicesComboBox.getItems().remove(comboIndex);
        this.invoicesComboBox.getItems().add(invoice.toShortString());
        this.invoicesComboBox.getSelectionModel().select(invoice);

    }
}
