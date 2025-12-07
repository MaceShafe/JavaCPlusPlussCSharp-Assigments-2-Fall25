package Main.domain;

import Main.Exceptions.TimeCardIllegalArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HourlyAdministrator extends Administrator implements Printable, JSONStringifiable{
private double hourlyRate;
private ArrayList<TimeCard> timeCards = new ArrayList<>();


        public void print() {
            System.out.println("");
        }

    public HourlyAdministrator(int personId, String firstName, String lastName, String userName, LocalDateTime birthDate, String ssn, String phone, LocalDateTime employmentStartDate, double hourlyRate) {
        super(personId, firstName, lastName, userName, birthDate, ssn, phone, employmentStartDate);
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(LocalDateTime startDate, LocalDateTime endDate) {
        try {

            timeCards.add(new TimeCard(startDate, endDate));
        }
        catch(Exception e){
            throw new TimeCardIllegalArgumentException("Invalid timecard owned by " + this.getPersonId() + ". " + userName + ": " + e.getMessage());
        }
    }

    public void addTimeCard(int id, LocalDateTime startDate, LocalDateTime endDate) {
        try{
        this.timeCards.add( new TimeCard(id, startDate,endDate));
        }
        catch(Exception e){
            throw new TimeCardIllegalArgumentException("Invalid timecard owned by " + this.getPersonId() + ". " + userName + ": " + e.getMessage());
        }
    }

    public TimeCard removeTimeCard(int index) {
        TimeCard timeCard = null;
        try {
            if (index >= 0) {
                timeCard = timeCards.get(index);
                timeCards.remove(index);
            }
        } catch (IndexOutOfBoundsException e) {}
        return timeCard;
    }

    public TimeCard getTimeCard(int index) {

        TimeCard timeCard = null;
        try {
            if (index >= 0) {
                timeCard = timeCards.get(index);
            }
        } catch (IndexOutOfBoundsException e) {}
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

    public String jsonStringify() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        StringBuilder sb = new StringBuilder(200);

        sb.append("{\n");
        sb.append("\"subclass\":\"hourlyAdministrator");
        sb.append("\",\n\"personId\":");
        sb.append(this.personId);
        sb.append(",\n\"lastName\":\"");
        sb.append(this.lastName);
        sb.append("\",\n\"firstName\":\"");
        sb.append(this.firstName );
        sb.append("\",\n\"userName\":\"");
        sb.append(this.userName);
        sb.append("\",\n\"birthDate\":\"");
        sb.append(formatter.format(this.getBirthDate()));
        sb.append("\",\n\"ssn\":\"");
        sb.append(this.getSsn());
        sb.append("\",\n\"phone\":\"");
        sb.append(this.getPhone());
        sb.append("\",\n\"employmentStartDate\":\"");
        sb.append(formatter.format(getEmploymentStartDate()));
        sb.append("\",\n\"hourlyRate\":");
        sb.append(this.hourlyRate);
        sb.append(",\n\"timeCards\":[");

        for (TimeCard timeCard : timeCards) {
//                    System.out.println("\t" + timeCard);
            sb.append("\n");
            sb.append(timeCard.jsonStringify());
            sb.append(",");


        }
        int commaIndex = sb.lastIndexOf(",");
        sb.deleteCharAt(commaIndex);
        sb.append("\n]\n");

        sb.append("}");
        return sb.toString();

    }
}

