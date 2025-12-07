package Main.domain;


import Main.Exceptions.TimeCardIllegalArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeCard implements JSONStringifiable{
    private int timeCardId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeCard(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mma");
        if (startDateTime.isEqual(endDateTime) || startDateTime.isAfter(endDateTime)) {
            throw new TimeCardIllegalArgumentException(
                    "Start: " + formatter.format(startDateTime)
                            + " End: " + formatter.format(endDateTime));

        }
        this.timeCardId = DbContext.getNextTimeCardId();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        }



    public TimeCard(int timeCardId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mma");
        if (startDateTime.isEqual(endDateTime) || startDateTime.isAfter(endDateTime)) {
            throw new TimeCardIllegalArgumentException(
                    "Start: " +  formatter.format(startDateTime)
                            + " End: " +  formatter.format(endDateTime));
            }
            this.timeCardId = timeCardId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        }



    public TimeCard(TimeCard timeCard) {
        this.timeCardId = timeCard.timeCardId;
        this.startDateTime = timeCard.startDateTime;
        this.endDateTime = timeCard.endDateTime;
    }

    public TimeCard copy() {
        return new TimeCard(this.timeCardId, this.startDateTime, this.endDateTime);
    }

    public double calcHours() {
        return this.startDateTime.until(this.endDateTime, ChronoUnit.MINUTES)/60.0;

    }

    @Override
    public String toString() {
        DateTimeFormatter ymdFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");

        return
                "ID=" + timeCardId +
                ", startDateTime=" + startDateTime.format(ymdFormat) +
                ", endDateTime=" + endDateTime.format(ymdFormat) +
                ", hours=" + String.format( "%.2f",  calcHours());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TimeCard timeCard = (TimeCard) o;
        return timeCardId == timeCard.timeCardId && Objects.equals(startDateTime, timeCard.startDateTime) && Objects.equals(endDateTime, timeCard.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeCardId, startDateTime, endDateTime);
    }

    public String jsonStringify() {
        DateTimeFormatter ymdFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");

        StringBuilder sb = new StringBuilder(200);

        sb.append("{\"id\":");
        sb.append(this.timeCardId);
        sb.append(",\"startDateTime\":\"");
        sb.append(this.startDateTime.format(ymdFormat));
        sb.append("\",\"endDateTime\":\"");
        sb.append(this.endDateTime.format(ymdFormat));
        sb.append("\"}");


        return sb.toString();
    }

}
