package Main.domain;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;


public class DbContext {
    private static int previousInvoiceId = 1000;
    private static int previousLineItemId = 10000;
    private static int previousTimeCardId = 10000;

    public static int getNextInvoiceId() {
        return ++previousInvoiceId;
    }

    public static int getNextLineItemId() {
        return ++previousLineItemId;
    }

    public static int getNextTimeCardId() {return ++previousTimeCardId;}

    public static ArrayList<Invoice> getInvoices() {
    ArrayList<Invoice> invoices = new ArrayList<Invoice>();
        LocalDateTime date1 = LocalDateTime.of(2019, Month.SEPTEMBER,1,0,0);
        LocalDateTime date2 = LocalDateTime.of(2019,Month.SEPTEMBER,11,0,0);


        Invoice invoice1 = new Invoice(1, date1, date2, new Apartment());

        invoice1.addLineItem(new LineItem(1.0, "description1"));
        invoice1.addLineItem(new LineItem(2.0, "description2"));
        invoice1.addLineItem(new LineItem(3.0, "description3"));
        invoice1.addLineItem(new LineItem(4.0, "description4"));
        invoices.add(invoice1);

        Invoice invoice2 = new Invoice(2,
                LocalDateTime.of(2025,Month.AUGUST,15,0,0),
                LocalDateTime.of(2025,Month.SEPTEMBER,1,0,0),
                new Apartment());
        invoice2.addLineItem(new LineItem(5.0, "description1"));
        invoice2.addLineItem(new LineItem(6.0, "description2"));
        invoice2.addLineItem(new LineItem(7.0, "description3"));
        invoice2.addLineItem(new LineItem(8.0, "description4"));
        invoices.add(invoice2);

        Invoice invoice3 = new Invoice(3, date1, date2, new Apartment());
        invoice3.addLineItem(new LineItem(9.0, "description1"));
        invoice3.addLineItem(new LineItem(10.0, "description2"));
        invoice3.addLineItem(new LineItem(11.0, "description3"));
        invoice3.addLineItem(new LineItem(12.0, "description4"));
        invoices.add(invoice3);

        Invoice invoice4 = new Invoice(4, date1, date2, new Apartment());
        invoice4.addLineItem(new LineItem(13.0, "description1"));
        invoice4.addLineItem(new LineItem(14.0, "description2"));
        invoice4.addLineItem(new LineItem(15.0, "description3"));
        invoice4.addLineItem(new LineItem(16.0, "description4"));
        invoices.add(invoice4);
        return invoices;
    }

    public static ArrayList<Apartment> getApartments() {
        ArrayList<Person> people = DbContext.getPeople();
        ArrayList<Apartment> apartments = new ArrayList<Apartment>();
        ArrayList<Invoice> invoices = getInvoices();


        Apartment apartment = new Apartment();
        apartment.setApartmentId(101);
        apartment.setApartmentNum("101");
        apartment.setSquareFeet(1001);
        apartment.setBathrooms(1);
        apartment.setPrice(1000.01);


        apartment.addInvoice(invoices.get(0));
        apartment.addInvoice(invoices.get(1));

        apartment.setAdministrator(people.get(0));
        apartment.setTenant(people.get(1));
        apartments.add(apartment);

        apartment = new Apartment();
        apartment.setApartmentId(102);
        apartment.setApartmentNum("102");
        apartment.setSquareFeet(1002);
        apartment.setBathrooms(2);
        apartment.setPrice(1000.02);
        apartment.addInvoice(invoices.get(2));
        apartment.addInvoice(invoices.get(3));
        apartments.add(apartment);
        apartment.setAdministrator(people.get(0));
        apartment.setTenant(people.get(2));
        return apartments;
    }

    public static ArrayList<Person> getPeople() {
        ArrayList<Person> people = new ArrayList<>();
        Person person = new Person();
        person.setPersonId(101);
        person.setFirstName("John");
        person.setLastName("Administrator");
        person.setUserName("admin");
        people.add(person);

        person = new Person();
        person.setPersonId(102);
        person.setFirstName("Joe");
        person.setLastName("Shmoe");
        person.setUserName("Joe.Shmoe");
        people.add(person);

        person = new Person();
        person.setPersonId(103);
        person.setFirstName("Jane");
        person.setLastName("Shmoe");
        person.setUserName("Jane.Shmoe");
        people.add(person);

        return people;
    }

}
