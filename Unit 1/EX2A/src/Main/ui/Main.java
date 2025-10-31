package Main.ui;


import Main.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
            Person person = new Person(101, "AAAAA", "AAAAA", "AAAA.AAAA");
            Tenant tenant = new Tenant(102, "BBBBB", "BBBBB", "BBBB.BBBB",
                    LocalDateTime.now(), "111-11-1111","111-111-1111","Minnesota State College Southeast",
                    "Instructor",10000.00,LocalDateTime.now());
//            System.out.println(person);

//            System.out.println(tenant);
            tenant.setUserName("BBBBBBBB.BBBBBBBBBB");
            tenant.setPhone("8675309");
//            System.out.println(tenant);

//            Administrator administrator = new Administrator(103, "CCCCCC", "CCCCCC", "CCCCCC.CCCCCC",
//                    LocalDateTime.now(),"111-11-1111","111-111-1111", LocalDateTime.now());
//            System.out.println(administrator);

            ContractAdministrator contractAdministrator = new ContractAdministrator(103, "DDDDDD", "DDDDDD", "DDDDDD.DDDDDD",
                    LocalDateTime.now(),"111-11-1111","111-111-1111", LocalDateTime.now(),200.00);
//            System.out.println(contractAdministrator);

            HourlyAdministrator hourlyAdministrator = new HourlyAdministrator(104, "EEEEEE", "EEEEEE", "EEEEEE.EEEEEE",
                    LocalDateTime.now(),"111-11-1111","111-111-1111", LocalDateTime.now(),50.00);

//            ArrayList<TimeCard> timeCards = new ArrayList<>();
            hourlyAdministrator.addTimeCard(LocalDateTime.of(2018,10,22,8,0),
                    LocalDateTime.of(2018,10,22,10,0));
            hourlyAdministrator.addTimeCard (LocalDateTime.of(2018,10,23,8,0),
                    LocalDateTime.of(2018,10,23,10,0));
            hourlyAdministrator.addTimeCard (LocalDateTime.of(2018,10,24,8,0),
                    LocalDateTime.of(2018,10,24,10,0));
            hourlyAdministrator.addTimeCard (LocalDateTime.of(2018,10,25,8,0),
                    LocalDateTime.of(2018,10,25,10,0));
//            System.out.println(hourlyAdministrator);
//


            ArrayList<Person> people = new ArrayList<Person>();
            people.add(person);
            people.add(tenant);
            people.add(contractAdministrator);
            people.add(hourlyAdministrator);

            double totalGrossPay = 0.0;

            for (Person p : people) {
                System.out.println(p.toString());
                if (p instanceof Administrator)
                    totalGrossPay = ((Administrator) p).calcGrossPay();
            }

            ArrayList<TimeCard> timeCards = hourlyAdministrator.getTimeCards();
            for (TimeCard timeCard : timeCards) {
                System.out.println("\t" + timeCard);
            }

            ArrayList<Administrator> administrators = new ArrayList<Administrator>();
            administrators.add(contractAdministrator);
            administrators.add(hourlyAdministrator);

//            for (Administrator a : administrators){
//                totalGrossPay += a.calcGrossPay();
//            }

            System.out.println("\tTotal Payroll: "+String.format("%.2f",  totalGrossPay));

        }

}
