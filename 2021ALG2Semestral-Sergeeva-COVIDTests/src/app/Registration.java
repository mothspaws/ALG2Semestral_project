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
     * Registrace pro datum, zadaný ve foermě textového řetězce
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
     * Registrace pro datum, zadaný ve foermě datumu
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

    /**
     * Getter pro testovácí místo, pro které je vytvořena tato rezervace
     *
     * @return
     */
    public Place getPlace() {
        return place;
    }

    /**
     * Getter pro jméno člověka, pro kterého je vytvořena tato rezervace
     *
     * @return
     */
    public String getNameOfPerson() {
        return nameOfPerson;
    }

    /**
     * Getter pro datum, pro který je vytvořena tato rezervace
     *
     * @return
     */
    public LocalDate getReservationDate() {
        return reservationDate;
    }

    /**
     * Pomocná metoda pro updateRegistrationDate(), která nastáví nový datum pro
     * existujicí rezervace
     *
     * @param reservationDate
     */
    public void setReservationDate(String reservationDate) {
        this.reservationDate = LocalDate.parse(reservationDate, dtf);
    }

    /**
     * Přepsana metoda toString() pro výpis objektu typu Registration
     *
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
