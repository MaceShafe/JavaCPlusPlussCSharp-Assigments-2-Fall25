package Main.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class TimeCard {
    private int timeCardId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeCard(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.timeCardId = DbContext.getNextTimeCardId();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public TimeCard(int timeCardId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
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
                ", hours=" +String.format( "%.2f",  calcHours());
    }
}
