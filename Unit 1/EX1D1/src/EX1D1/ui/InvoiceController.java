package EX1D1.ui;

import EX1D1.domain.DbContext;
import EX1D1.domain.Invoice;
import EX1D1.domain.LineItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class InvoiceController {



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
    @FXML
    public TextField totalTextField;
    @FXML
    public Button saveInvoiceButton;
    @FXML
    public Button saveLineItemButton;
    @FXML
    public Button addLineItemButton;
    @FXML
    public Button deleteLineItemButton;


//    public InvoiceController() {
//
//this.invoices= DbContext.getInvoices();
//
//}

    public InvoiceController() {



    }

    public void initData(ArrayList<Invoice> invoices) {

        this.invoices = invoices;

        invoicesComboBox.getItems().clear();
        for (Invoice invoice : invoices) {
            invoicesComboBox.getItems().add(invoice.toShortString());
        }

        if (!this.invoices.isEmpty()) {
            invoicesComboBox.getSelectionModel().selectFirst();
            Invoice invoice = this.invoices.get(0);
            this.displayInvoice(invoice);
            this.displayLineItems(invoice);
            this.displayInvoiceTotal(invoice);
        }


    }

@FXML
  void displayInvoice(Invoice invoice) {


    this.invoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
     this.statusTextField.setText(Integer.toString(invoice.getStatus()));

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    this.invoiceDateTextField.setText(invoice.getInvoiceDate().format(formatter));

    this.dueDateTextField.setText(invoice.getDueDate().format(formatter));


}


    @FXML
    protected void initialize() {


if (!this.invoices.isEmpty()) {
        for (Invoice invoice : this.invoices) {
            invoicesComboBox.getItems().add(invoice.toShortString());
        }
        invoicesComboBox.getSelectionModel().selectFirst();
        Invoice invoice = this.invoices.get(0);
        this.displayInvoice(invoice);
        this.displayLineItems(invoice);
        this.displayInvoiceTotal(invoice);

}
    }
private void displayLineItem(LineItem lineItem) {
        this.descriptionTextField.setText(lineItem.getDescription());
        this.amountTextField.setText(Double.toString(lineItem.getAmount()));

}

    public void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
        int comboIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        if (comboIndex < 0) return;
        Invoice invoice = this.invoices.get(comboIndex);
        this.displayInvoice(invoice);
        this.displayLineItems(invoice);
        this.displayInvoiceTotal(invoice);
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

    private void displayInvoiceTotal(Invoice invoice) {
    double total = invoice.total();
    this.totalTextField.setText(Double.toString(total));
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


//    invoice.setStatus(Integer.parseInt(statusTextField.getText()));
//    GDate invoiceDate = new GDate(
//            Integer.parseInt(this.invoiceDateTextField.getText().substring(0,4)),
//            Integer.parseInt(this.invoiceDateTextField.getText().substring(5,7)),
//            Integer.parseInt(this.invoiceDateTextField.getText().substring(8,10))
//    );
//    GDate dueDate = new GDate(
//            Integer.parseInt(this.dueDateTextField.getText().substring(0,4)),
//            Integer.parseInt(this.dueDateTextField.getText().substring(5,7)),
//            Integer.parseInt(this.dueDateTextField.getText().substring(8,10))
//    );
//
//    invoice.setInvoiceDate(invoiceDate);
//    invoice.setDueDate(dueDate);

        int comboIndex = invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = invoices.get(comboIndex);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        invoice.setInvoiceDate(LocalDateTime.parse(this.invoiceDateTextField.getText(), formatter));
        LocalDate date;
        date = LocalDate.parse(this.invoiceDateTextField.getText(), formatter);
        invoice.setInvoiceDate(date.atStartOfDay());
//        invoice.setDueDate(LocalDateTime.parse(this.dueDateTextField.getText(), formatter));
        date =  LocalDate.parse(this.dueDateTextField.getText(), formatter);
        invoice.setDueDate(date.atStartOfDay());



        invoicesComboBox.getItems().set(comboIndex, invoice.toShortString());
        invoicesComboBox.getSelectionModel().select(comboIndex);

//        this.invoicesComboBox.getItems().remove(comboIndex);
//        this.invoicesComboBox.getItems().add(invoice);
//        this.invoicesComboBox.getSelectionModel().select(invoice);

    }



    public void saveLineItemButtonClicked(ActionEvent actionEvent) {
        int comboIndex =  invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = invoices.get(comboIndex);


        int listIndex = lineItemsListView.getSelectionModel().getSelectedIndex();
        invoice.removeLineItem(listIndex);

        String description = descriptionTextField.getText();
        Double amount = Double.parseDouble(amountTextField.getText());

        LineItem lineItem = new LineItem(amount, description);
        invoice.addLineItem(listIndex, lineItem);
        displayInvoiceTotal(invoice);
        lineItemsListView.getItems().remove(listIndex);
        lineItemsListView.getItems().add(listIndex, lineItem.toString());
        lineItemsListView.getSelectionModel().select(listIndex);


    }

    public void addLineItemButtonClicked(ActionEvent actionEvent) {
        int comboIndex =  invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = invoices.get(comboIndex);

        LineItem lineItem = new LineItem(0.0, "");
        invoice.addLineItem(lineItem);

        this.lineItemsListView.getItems().add(lineItem.toString());
        this.lineItemsListView.getSelectionModel().selectLast();

        this.lineItemsListView.scrollTo(lineItemsListView.getSelectionModel().getSelectedIndex());
        displayLineItem(lineItem);
        displayInvoiceTotal(invoice);
        this.descriptionTextField.requestFocus();

    }

    public void deleteLineItemButtonClicked(ActionEvent actionEvent) {
        this.amountTextField.clear();
        this.descriptionTextField.clear();

        int comboIndex =  invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = invoices.get(comboIndex);

        if(this.lineItemsListView.getSelectionModel().getSelectedIndex() >= 0) {
            invoice.removeLineItem(lineItemsListView.getSelectionModel().getSelectedIndex());
            lineItemsListView.getItems().remove(lineItemsListView.getSelectionModel().getSelectedIndex());
            lineItemsListView.getSelectionModel().selectLast();

            if(lineItemsListView.getSelectionModel().getSelectedIndex() >= 0) {
            this.lineItemsListView.scrollTo(lineItemsListView.getSelectionModel().getSelectedIndex());
            displayLineItem(getSelectedLineItem());
            }
            displayInvoiceTotal(invoice);

        }


    }
}
