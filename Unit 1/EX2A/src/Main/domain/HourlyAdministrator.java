package Main.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class HourlyAdministrator extends Administrator{
private double hourlyRate;
private ArrayList<TimeCard> timeCards = new ArrayList<>();

    public HourlyAdministrator(int personId, String firstName, String lastName, String userName, LocalDateTime birthDate, String ssn, String phone, LocalDateTime employmentStartDate, double hourlyRate) {
        super(personId, firstName, lastName, userName, birthDate, ssn, phone, employmentStartDate);
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(LocalDateTime startDate, LocalDateTime endDate) {
        new TimeCard(startDate,endDate);
        timeCards.add(new TimeCard(startDate,endDate));
        }

    public void removeTimeCard(int index) {
        TimeCard timeCard = null;
        if(index >= 0) {
            timeCard = timeCards.get(index);
            timeCards.remove(index);
        }

    }

    public TimeCard getTimeCard(int index) {

        TimeCard timeCard = null;
        if(index >= 0) {
            timeCard = timeCards.get(index);
        }

        return  timeCard;
    }

    public ArrayList<TimeCard> getTimeCards() {
        return timeCards;
    }

    public double calcTotalHours() {
        double totalHours =0.0;

        for (TimeCard timeCard :timeCards) {
            totalHours += timeCard.calcHours();
        }

        return totalHours;
    }

    @Override
    public double calcGrossPay() {
        return calcTotalHours() * this.hourlyRate;
    }

    @Override
    public String toString() {
        return super.toString() +
                " hourlyRate:  " + hourlyRate +
                ", total hours:  " + calcTotalHours()+
                ", gross pay: " + calcGrossPay();
    }
}

