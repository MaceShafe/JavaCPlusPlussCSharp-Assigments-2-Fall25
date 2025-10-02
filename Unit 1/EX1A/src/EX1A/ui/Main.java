package EX1A.ui;

import EX1A.domain.GDate;

public class Main {
    public static void main(String[] args) {
        // constructors
        GDate date1 = new GDate();
        System.out.println("GDate():\t\t\t"+date1);
        GDate date2 = new GDate(2000,1,1);
        System.out.println("GDate(2000, 1, 1):\t"+date2);
        GDate date3 = new GDate(date1);
        System.out.println("GDate(Date1):\t\t"+date3);
        GDate date4 = new GDate(2451545);
        System.out.println("GDate(2451545):\t\t"+date4);
        GDate date5 = date1.copy();
        System.out.println("GDate(Copy):\t\t"+date5);


        GDate date6 = new GDate(2001,1,1);

        //comparisons
        System.out.print(date1);
        System.out.print(date1.equals(date3) ? " = " : " != ");
        System.out.println(date3);

        System.out.print(date1);
        System.out.print(date1.equals(date6) ? " = " : " != ");
        System.out.println(date6);

        System.out.print(date1);
        System.out.print(date1.greaterThan(date6) ? " > " : " < ");
        System.out.println(date6);

        System.out.print(date6);
        System.out.print(date6.greaterThan(date1) ? " > " : " < ");
        System.out.println(date1);


        System.out.print(date6);
        System.out.print(" - ");
        System.out.print(date1);
        System.out.print(" = ");
        System.out.println(date6.diff(date1) + " days");

        System.out.print(date1);
        System.out.print(" - ");
        System.out.print(date6);
        System.out.print(" = ");
        System.out.println(date1.diff(date6) + " days");


        System.out.print(date1);
        System.out.print(" + 60 = ");
        System.out.println(date1.add(60) + " days");

        System.out.print(date6);
        System.out.print(" + 60 = ");
        System.out.println(date6.add(60) + " days");

        System.out.print("Year of 2000.01.01 = ");
        System.out.println(date1.year() );

        System.out.print("Month of 2000.01.01 = ");
        System.out.println(date1.month() );

        System.out.print("Day of \t2000.01.01 = ");
        System.out.println(date1.day() );

        System.out.print("JDN of \t2000.01.01 = ");
        System.out.println(date1.julianDay() );

    }
}
