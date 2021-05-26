package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Collator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import utils.DateHaveAlreadyExistException;
import utils.NameHaveAlreadyExistException;
import utils.NameHaveNotExistException;

/**
 *
 * @author Viktoriia Sergeeva
 */
public class Registrations {

    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d.M.yyyy");
    private final List<Registration> registrations;

    /**
     * Při vytváření Registrations() vytvoří list registrací
     */
    public Registrations() {
        registrations = new ArrayList<>();
    }

    /**
     * Načítá data ze soubru
     *
     * @param fileWithAListOfPlaces
     * @param separator
     * @param header
     * @param places
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void load(String fileWithAListOfPlaces, String separator, boolean header, Places places) throws FileNotFoundException, IOException {
        TextFileReader in = new TextFileReader();
        List<String[]> data = in.readFile(new File(fileWithAListOfPlaces), separator, header);
        dataOfRegistrations(data, places);
    }

    /**
     * Naplní list registrací daty
     *
     * @param data
     * @param places
     */
    private void dataOfRegistrations(List<String[]> data, Places places) throws IOException {
        List<String> errors = new ArrayList<>();
        String nameOfPerson, reservationDate;
        int internalID;
        Registration r;
        Place p;
        for (String[] parts : data) {//1,ADAM NOVAK,11.05.2021
            internalID = Integer.parseInt(parts[0]);
            nameOfPerson = parts[1];
            reservationDate = parts[2];
            try {
                setRegistration(nameOfPerson, reservationDate, places.findPlaceByInternalID(internalID));
            } catch (NameHaveAlreadyExistException e) {
                errors.add(e.getMessage() + ": " + nameOfPerson + " " + reservationDate);
            } catch (DateHaveAlreadyExistException e) {
                errors.add(e.getMessage() + ": " + nameOfPerson + " " + reservationDate);
            }
        }
        if (!errors.isEmpty()) {
            String s = "";
            for (String error : errors) {
                s += error + "\n";
            }
            throw new IllegalArgumentException("Rezervace nebylo možné vytvořit pro:  \n" + s);
        }
    }

    /**
     * Vytvoří novou registraci
     *
     * @param nameOfPerson
     * @param reservationDate
     * @param place
     * @throws java.io.IOException
     */
    public void setRegistration(String nameOfPerson, String reservationDate, Place place) throws IOException {
        existenceOfName(nameOfPerson);
        existenceOfDate(reservationDate, place);
        Registration reg = new Registration(nameOfPerson, reservationDate, place);
        registrations.add(reg);
    }

    /**
     * Pomocná metoda, která určue existence člověka
     *
     * @param nameOfPerson
     */
    public void existenceOfName(String nameOfPerson) {
        if (registrations.stream().anyMatch((registration) -> (registration.getNameOfPerson().equals(nameOfPerson))) == true) {
            throw new NameHaveAlreadyExistException("Takový člověk existuje");
        }
    }

    /**
     * Pomocná metoda, která určue existence datumu
     *
     * @param reservationDate
     * @param place
     * @throws java.io.IOException
     */
    public void existenceOfDate(String reservationDate, Place place) throws IOException {
        LocalDate date = LocalDate.parse(reservationDate, dtf);
        if (registrations.stream().anyMatch((registration) -> (registration.getReservationDate().equals(date) && registration.getPlace().equals(place)))) {
            throw new DateHaveAlreadyExistException("Takový datum existuje");
        }
    }

    /**
     * Nastaví nový datum rezervace podle id a jména člověka
     *
     * @param nameOfPerson
     * @param reservationDate
     * @param internalId
     * @throws java.io.IOException
     */
    public void updateRegistrationDate(String nameOfPerson, String reservationDate, int internalId) throws IOException {
        try {
            existenceOfName(nameOfPerson);
            throw new NameHaveNotExistException("Pro zadaného uživatele není rezervace, kterou je možné změnit");
        } catch (NameHaveAlreadyExistException a) {
            for (Registration registration : registrations) {
                try {
                    existenceOfDate(reservationDate, registration.getPlace());
                    if (registration.getPlace().getInternalID() == internalId) {
                        if (registration.getNameOfPerson().equals(nameOfPerson)) {
                            registration.setReservationDate(reservationDate);
                        }
                    }
                } catch (DateHaveAlreadyExistException e) {
                    break;
                }
            }
        }
    }

    /**
     * Smaže registraci podle jména a internalId ConcurrentModificationException
     *
     * @param nameOfPerson
     * @param internalId
     */
    public boolean deleteRegistrationForPerson(String nameOfPerson, int internalId) {
        for (Registration registration : registrations) {
            if (registration.getNameOfPerson().equals(nameOfPerson) && registration.getPlace().getInternalID() == internalId) {
                registrations.remove(registration);
                return true;
            }
        }
        return false;
    }

    /**
     * Vrátí seznam registrací podle internalID
     *
     * @param internalID
     * @return Registrations
     */
    public Registrations getRegistrationsFor(int internalID) {
        Registrations reg = new Registrations();
        registrations.stream().filter((registration) -> (registration.getPlace().getInternalID() == internalID)).forEachOrdered((registration) -> {
            reg.addRegistration(registration);
        });
        return reg;
    }

    /**
     * Je to pomocná metoda pro nalezení rezervací
     *
     * @param reg
     */
    private void addRegistration(Registration reg) {
        registrations.add(reg);
    }

    /**
     * Metoda pro výpis všech rezervací
     *
     * @return
     */
    public String toStringRegistrations() {
        StringBuilder s = new StringBuilder();
        registrations.stream().map((registration) -> {
            s.append(registration.toString());
            return registration;
        }).forEachOrdered((_item) -> {
            s.append("\n");
        });
        return s.toString();
    }

    /**
     * Vrátí setřídění seznam rezervací podle datumu
     *
     * @return
     */
    public String sortRegToStringByDate() {
        Collections.sort(registrations, Registrations.COMP_RESERV_DATE);
        return toStringRegistrations();
    }

    /**
     * Vrátí setřídění seznam rezervací podle jména
     *
     * @return
     */
    public String sortRegToStringByName() {
        Collections.sort(registrations, Registrations.COMP_NAME);
        return toStringRegistrations();
    }

    /**
     * Vrátí seznam rezervací pro zvolené testovácí místo
     *
     * @param internalID
     * @return
     */
    public String toStringRegistrationsFor(int internalID) {
        StringBuilder s = new StringBuilder();
        registrations.stream().filter((registration) -> (registration.getPlace().getInternalID() == internalID)).map((registration) -> {
            s.append(registration.toString());
            return registration;
        }).forEachOrdered((_item) -> {
            s.append("\n");
        });
        return s.toString();
    }

    /**
     * Třídění rezervace podle datumu pro zvolené testovácí místo
     *
     * @param internalID
     * @return
     */
    public String sortRegToStringByDate(int internalID) {
        Collections.sort(registrations, Registrations.COMP_RESERV_DATE);
        return toStringRegistrationsFor(internalID);
    }

    /**
     * Třídění rezervace podle jména pro zvolené testovácí místo
     *
     * @param internalID
     * @return
     */
    public String sortRegToStringByName(int internalID) {
        Collections.sort(registrations, Registrations.COMP_NAME);
        return toStringRegistrationsFor(internalID);
    }

    /**
     * Comparator podle jména s využitím collatoru
     */
    public static Collator col = Collator.getInstance(new Locale("cs", "CZ"));
    public static final Comparator<Registration> COMP_NAME = (Registration r1, Registration r2) -> {
        int result = col.compare(r1.getNameOfPerson(), r2.getNameOfPerson());
        return result;
    };
    /**
     * Comparator podle datumu
     */
    public static final Comparator<Registration> COMP_RESERV_DATE = new Comparator<Registration>() {
        @Override
        public int compare(Registration r1, Registration r2) {
            int result;
            result = r1.getReservationDate().compareTo(r2.getReservationDate());
            return result;
        }
    };

    /**
     * Uloží seznam rezervací do souboru
     *
     * @param resultFile
     * @param places
     * @throws IOException
     */
    public void saveRegistrations(String resultFile, Places places) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(resultFile + ".txt"), true)))) {
            pw.println("Všechny rezervace");
            String s = toStringRegistrations();
            pw.println(s);
        }
    }

    /**
     * Uloží seznam rezervací pro zvolené testovácí místo do souboru
     *
     * @param internalID
     * @param resultFile
     * @param places
     * @throws IOException
     */
    public void saveRegistrationsFor(int internalID, String resultFile, Places places) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(resultFile + ".txt"), true)))) {
            pw.println("Rezervace pro " + places.findPlaceByInternalID(internalID));
            String s = toStringRegistrationsFor(internalID);
            pw.println(s);
        }
    }
}
