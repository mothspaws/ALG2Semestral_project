package app;

/**
 *
 * @author Viktoriia Sergeeva
 */
public class Place {

    public String name;
    public String address;
    public String id;
    public int test_capacity;
    public int internalID;
    public boolean PCR;
    public boolean antigenic;
    public boolean drive_in;

    /**
     * Konstructor
     *
     * @param internalID
     * @param id
     * @param name
     * @param address
     * @param test_capacity
     * @param PCR
     * @param antigenic
     * @param drive_in
     */
    public Place(int internalID, String id, String name, String address, int test_capacity, boolean PCR, boolean antigenic, boolean drive_in) {
        this.internalID = internalID;
        this.id = id;
        this.name = name;
        this.address = address;
        this.test_capacity = test_capacity;
        this.PCR = PCR;
        this.antigenic = antigenic;
        this.drive_in = drive_in;
    }

    /**
     * Getter pro přídělené ID testovácího místa
     *
     * @return
     */
    public int getInternalID() {
        return internalID;
    }

    /**
     * Getter pro ID z open dat testovácího místa
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Getter pro název testovácího místa
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Getter pro adresu testovácího místa
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Getter pro maximální počet osob testovácího místa
     *
     * @return
     */
    public int getTest_capacity() {
        return test_capacity;
    }

    /**
     * Vrátí true nebo false, v závislosti na tom, jestli má testovácí místo PCR
     * testy
     *
     * @return
     */
    public boolean isPCR() {
        return PCR;
    }

    /**
     * Vrátí true nebo false, v závislosti na tom, jestli má testovácí místo
     * antigenné testy
     *
     * @return
     */
    public boolean isAntigenic() {
        return antigenic;
    }

    /**
     * Vrátí true nebo false, v závislosti na tom, jestli má testovácí místo
     * vyjezdní testy
     *
     * @return
     */
    public boolean isDrive_in() {
        return drive_in;
    }

    /**
     * Přepsana metoda toString() pro výpis objektu typu Place
     *
     * @return
     */
    @Override
    public String toString() {
        String s;
        s = String.format("%4d %90s %98s", internalID, name, address);
        return s;
    }
}
