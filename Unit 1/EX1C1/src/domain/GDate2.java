package domain;

import java.util.Objects;

public class GDate2 {
    private int julianDay = 0;

    public GDate2() { this.julianDay=2451545;     /* Default date =2000.01.01*/ }

    public GDate2(int year, int month, int day)
    {
        this.julianDay=( 1461 * ( year + 4800 + ( month - 14 ) / 12 ) ) / 4 +
                ( 367 * ( month - 2 - 12 * ( ( month - 14 ) / 12 ) ) ) / 12 -
                ( 3 * ( ( year + 4900 + ( month - 14 ) / 12 ) / 100 ) ) / 4 +
                day - 32075;
    }

    /**
     *
     * @param date
     */
    public GDate2(GDate2 date) {
        this.julianDay = date.julianDay;
    }

    /**
     *
     * @param julianDay
     */
    public GDate2(int julianDay) {
        this.julianDay = julianDay;
    }

    public GDate2 copy() {
        return new GDate2(this.julianDay);
    }


//    public boolean equals(GDate date) {
//        return this.julianDay == date.julianDay;
//    }
    public boolean greaterThan(GDate2 date) {
        return this.julianDay > date.julianDay;
    }

    /**
     *
     * @param date
     */
    public int diff(GDate2 date) {
        if(this.greaterThan(date))
        {return this.julianDay - date.julianDay;}
        else {return date.julianDay-this.julianDay;}
    }

    public int julianDay() {
        return this.julianDay;
    }

    public int year() {
        int l = this.julianDay + 68569;
        int n = ( 4 * l ) / 146097;
        l = l - ( 146097 * n + 3 ) / 4;
        int i = ( 4000 * ( l + 1 ) ) / 1461001;
        l = l - ( 1461 * i ) / 4 + 31;
        int j = ( 80 * l ) / 2447;
        int d = l - ( 2447 * j ) / 80;
        l = j / 11;
        int m = j + 2 - ( 12 * l );
        int y = 100 * ( n - 49 ) + i + l;
        return y;
    }

    public int month() {
        int l = this.julianDay + 68569;
        int n = ( 4 * l ) / 146097;
        l = l - ( 146097 * n + 3 ) / 4;
        int i = ( 4000 * ( l + 1 ) ) / 1461001;
        l = l - ( 1461 * i ) / 4 + 31;
        int j = ( 80 * l ) / 2447;
        int d = l - ( 2447 * j ) / 80;
        l = j / 11;
        int m = j + 2 - ( 12 * l );
        return m;
    }

    public int day() {
        int l = this.julianDay + 68569;
        int n = ( 4 * l ) / 146097;
        l = l - ( 146097 * n + 3 ) / 4;
        int i = ( 4000 * ( l + 1 ) ) / 1461001;
        l = l - ( 1461 * i ) / 4 + 31;
        int j = ( 80 * l ) / 2447;
        int d = l - ( 2447 * j ) / 80;
        l = j / 11;
        return d;
    }

    /**
     *
     * @param days
     */
    public GDate2 add(int days) {
        return new GDate2(this.julianDay + days);
    }


    @Override
    public String toString() {
        int Year = this.year();
        String Month = String.format("%0" + 2 + "d", this.month());
        String Day = String.format("%0" + 2 + "d", this.day());
        return Year + "." + Month + "." + Day;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GDate2 gDate = (GDate2) o;
        return julianDay == gDate.julianDay;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(julianDay);
    }
}
