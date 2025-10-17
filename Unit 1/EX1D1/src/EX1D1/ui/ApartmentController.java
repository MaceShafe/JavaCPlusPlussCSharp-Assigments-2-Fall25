package EX1D1.ui;

import EX1D1.domain.Apartment;
import EX1D1.domain.DbContext;
import EX1D1.domain.Invoice;
import EX1D1.domain.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ApartmentController {
    private ArrayList<Apartment> apartments = new ArrayList<Apartment>();
    private ArrayList<Person> people = new ArrayList<Person>();

    public Button saveApartmentButton;
    public Button viewInvoicesButton;
    public ComboBox ApartmentComboBox;
    public ComboBox administratorComboBox;
    public ComboBox tenantComboBox;
    public TextField apartmentNumTextField;
    public TextField squareFeetTextField;
    public TextField bathroomsTextField;
    public TextField priceTextField;
    public TextField updatedTextField;

public ApartmentController() {
    this.apartments = DbContext.getApartments();
    this.people = DbContext.getPeople();
}

    @FXML
    protected void initialize() {

        for (Apartment apartment : this.apartments) {
            ApartmentComboBox.getItems().add(apartment.toShortString());
        }
        ApartmentComboBox.getSelectionModel().selectFirst();
        Apartment apartment = this.apartments.get(0);

        for (Person person : this.people) {
            administratorComboBox.getItems().add(person.toShortString());
        }
//        administratorComboBox.getSelectionModel().selectFirst();

        for (Person person : this.people) {
            tenantComboBox.getItems().add(person.toShortString());
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
        for (int i = 0; i<this.people.size(); i++) {
            Person person = this.people.get(i);
            if (person.equals(apartment.getAdministrator())) {
                selectedIndex = i;
                break;
            }
        }

        this.administratorComboBox.getSelectionModel().select(selectedIndex);

        selectedIndex = -1;
        for (int i = 0; i<this.people.size(); i++) {
            Person person = this.people.get(i);
            if (person.equals(apartment.getTenant())) {
                selectedIndex = i;
                break;
            }
        }
        this.tenantComboBox.getSelectionModel().select(selectedIndex);
    }



    public void saveApartmentButtonClicked(ActionEvent actionEvent) {
   int comboIndex = ApartmentComboBox.getSelectionModel().getSelectedIndex();
   Apartment apartment = this.apartments.get(comboIndex);

apartment.setApartmentNum(apartmentNumTextField.getText());
apartment.setSquareFeet(Integer.parseInt(squareFeetTextField.getText()));
apartment.setBathrooms(Integer.parseInt(bathroomsTextField.getText()));
apartment.setPrice(Double.parseDouble(priceTextField.getText()));
apartment.setUpdated(LocalDateTime.now());

comboIndex = administratorComboBox.getSelectionModel().getSelectedIndex();
Person person = this.people.get(comboIndex);
apartment.setAdministrator(person);

comboIndex = tenantComboBox.getSelectionModel().getSelectedIndex();
Person person2 = this.people.get(comboIndex);
apartment.setTenant(person2);

ApartmentComboBox.getItems().set(comboIndex, apartment.toShortString());
ApartmentComboBox.getSelectionModel().select(comboIndex);

    }

    public void viewInvoicesButtonClicked(ActionEvent actionEvent) throws IOException {

        int comboIndex = ApartmentComboBox.getSelectionModel().getSelectedIndex();
        if (comboIndex >= 0) {
            Apartment apartment = apartments.get(comboIndex);
            ArrayList<Invoice> invoices = apartment.getInvoices();
            System.out.println("Apartment " + apartment.getApartmentNum() + " has " + apartment.getInvoices().size() + " invoices.");
            try {


                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("invoiceView.fxml"));
                InvoiceController invoiceController = new InvoiceController();
                fxmlLoader.setController(invoiceController);


                Pane pane = (Pane) fxmlLoader.load();

                invoiceController.initData(invoices);

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
            displayApartment(apartments.get(comboIndex));
        }
    }

    public void administratorComboBox_ItemSelected(ActionEvent actionEvent) {


    }

    public void tenantComboBox_ItemSelected(ActionEvent actionEvent) {
    }
}