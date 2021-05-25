package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Viktoriia Sergeeva
 */
public class Registration {

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");

    String nameOfPerson;
    LocalDate reservationDate;
    Place place;
/**
 * 
 * @param nameOfPerson
 * @param reservationDate
 * @param place 
 */
    public Registration(String nameOfPerson, String reservationDate, Place place) {
        this.nameOfPerson = nameOfPerson;
        this.reservationDate = LocalDate.parse(reservationDate, dtf);
        this.place = place;
    }
/**
 * 
 * @param nameOfPerson
 * @param reservationDate
 * @param place 
 */
    public Registration(String nameOfPerson, LocalDate reservationDate, Place place) {
        this.nameOfPerson = nameOfPerson;
        this.reservationDate = reservationDate;
        this.place = place;
    }

    public Place getPlace() {
        return place;
    }

    public String getNameOfPerson() {
        return nameOfPerson;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = LocalDate.parse(reservationDate, dtf);
    }

/**
 * Přepsana metoda toString() pro výpis objektu typu Registration
 * @return 
 */
    @Override
    public String toString() {
        String s, date;
        date = reservationDate.format(dtf);
        s = String.format("%4d %30s %12s %90s %98s", place.getInternalID(), nameOfPerson, date, place.getName(), place.getAddress());
        return s;
    }
}
