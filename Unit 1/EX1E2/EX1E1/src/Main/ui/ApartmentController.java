package Main.ui;

import Main.domain.Apartment;
import Main.domain.DbContext;
import Main.domain.Invoice;
import Main.domain.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApartmentController {
//    private ArrayList<Apartment> apartments = new ArrayList<Apartment>();
//    private ArrayList<Person> people = new ArrayList<Person>();

    public Button saveApartmentButton;
    public Button viewInvoicesButton;
    public ComboBox<Apartment> ApartmentComboBox;
//    public ComboBox administratorComboBox;
//    public ComboBox tenantComboBox;
    public ComboBox<Person> administratorComboBox;          //ex1e
    public ComboBox<Person> tenantComboBox;                 //ex1e
    public TextField apartmentNumTextField;
    public TextField squareFeetTextField;
    public TextField bathroomsTextField;
    public TextField priceTextField;
    public TextField updatedTextField;

public ApartmentController() {
//    this.apartments = DbContext.getApartments();
//    this.people = DbContext.getPeople();
}

    @FXML
    protected void initialize() {

ArrayList<Apartment> apartments = DbContext.getApartments();

        for (Apartment apartment : apartments) {
            ApartmentComboBox.getItems().add(apartment);
        }
        ApartmentComboBox.getSelectionModel().selectFirst();
        Apartment apartment = ApartmentComboBox.getSelectionModel().getSelectedItem();

        ArrayList<Person>  people = DbContext.getPeople();


        for (Person person : people) {
//          administratorComboBox.getItems().add(person.toShortString());
            administratorComboBox.getItems().add(person);
        }
//        administratorComboBox.getSelectionModel().selectFirst();

        for (Person person : people) {
//          tenantComboBox.getItems().add(person.toShortString());
            tenantComboBox.getItems().add(person);
        }
//        tenantComboBox.getSelectionModel().selectFirst();
        displayApartment(apartment);


    }

    private void displayApartment(Apartment apartment) {
        this.apartmentNumTextField.setText(apartment.getApartmentNum());
        this.squareFeetTextField.setText(String.valueOf(apartment.getSquareFeet()));
        this.bathroomsTextField.setText(String.valueOf(apartment.getBathrooms()));
        this.priceTextField.setText(String.valueOf(apartment.getPrice()));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        this.updatedTextField.setText(apartment.getUpdated().format(formatter));

        int selectedIndex = -1;
//        for (int i = 0; i<this.people.size(); i++) {          //ex1e
//            Person person = this.people.get(i);

        for (int i = 0; i<this.administratorComboBox.getItems().size(); i++) {
            Person person = this.administratorComboBox.getItems().get(i);

            if (person.equals(apartment.getAdministrator())) {
                selectedIndex = i;
                break;
            }
        }

        this.administratorComboBox.getSelectionModel().select(selectedIndex);

        selectedIndex = -1;
//        for (int i = 0; i<this.people.size(); i++) {
//            Person person = this.people.get(i);
            for (int i = 0; i<this.tenantComboBox.getItems().size(); i++) {
                Person person = this.tenantComboBox.getItems().get(i);
            if (person.equals(apartment.getTenant())) {
                selectedIndex = i;
                break;
            }
        }
        this.tenantComboBox.getSelectionModel().select(selectedIndex);
    }



    public void saveApartmentButtonClicked(ActionEvent actionEvent) {
   int comboIndex = ApartmentComboBox.getSelectionModel().getSelectedIndex();
   Apartment apartment = ApartmentComboBox.getItems().get(comboIndex);

apartment.setApartmentNum(apartmentNumTextField.getText());
apartment.setSquareFeet(Integer.parseInt(squareFeetTextField.getText()));
apartment.setBathrooms(Integer.parseInt(bathroomsTextField.getText()));
apartment.setPrice(Double.parseDouble(priceTextField.getText()));
apartment.setUpdated(LocalDateTime.now());

int personIndex = administratorComboBox.getSelectionModel().getSelectedIndex();
Person person = this.administratorComboBox.getItems().get(personIndex);
apartment.setAdministrator(person);



personIndex = tenantComboBox.getSelectionModel().getSelectedIndex();
Person person2 = this.tenantComboBox.getItems().get(personIndex);
apartment.setTenant(person2);

ApartmentComboBox.getItems().set(comboIndex, apartment);
ApartmentComboBox.getSelectionModel().select(comboIndex);

    }

    public void viewInvoicesButtonClicked(ActionEvent actionEvent) throws IOException {

        int comboIndex = ApartmentComboBox.getSelectionModel().getSelectedIndex();
        if (comboIndex >= 0) {
            Apartment apartment = ApartmentComboBox.getItems().get(comboIndex);
            ArrayList<Invoice> invoices = apartment.getInvoices();
            System.out.println("Apartment " + apartment.getApartmentNum() + " has " + apartment.getInvoices().size() + " invoices.");
            try {


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InvoiceView.fxml"));
                InvoiceController invoiceController = new InvoiceController();
                fxmlLoader.setController(invoiceController);


                Pane pane = (Pane) fxmlLoader.load();
                invoiceController.initData(invoices);
                invoiceController.initialize();


                Stage stage = new Stage();
                stage.setTitle("Invoice Manager");
                stage.setScene(new Scene(pane, 480,500));
                stage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void ApartmentComboBox_ItemSelected(ActionEvent actionEvent) {
//        int comboIndex = ApartmentComboBox.getSelectionModel().getSelectedIndex();
//        if (comboIndex < 0) return;
//        Apartment apartment = this.apartments.get(comboIndex);
//        displayApartment(apartment);
        int comboIndex = ApartmentComboBox.getSelectionModel().getSelectedIndex();
        if (comboIndex >= 0) {
            displayApartment(ApartmentComboBox.getItems().get(comboIndex));
        }
    }

    public void administratorComboBox_ItemSelected(ActionEvent actionEvent) {


    }

    public void tenantComboBox_ItemSelected(ActionEvent actionEvent) {
    }
}