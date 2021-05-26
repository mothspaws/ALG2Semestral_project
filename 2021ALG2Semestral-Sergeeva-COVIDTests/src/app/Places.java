package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Viktoriia Sergeeva
 */
public class Places {

    private final List<Place> listOfPlaces;
    private static int internalId = 0;

    /**
     * Při vytváření Places() vytvoří list testovácích míst, který pak budeme
     * naplňovat
     */
    public Places() {
        listOfPlaces = new ArrayList<>();
    }

    /**
     * Vrátí list testovácích míst
     *
     * @return
     */
    public List<Place> getListOfPlaces() {
        return listOfPlaces;
    }

    /**
     * Tato metoda načítá data ze souboru
     *
     * @param fileWithAListOfPlaces
     * @param separator
     * @param header
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void load(String fileWithAListOfPlaces, String separator, boolean header) throws FileNotFoundException, IOException {
        TextFileReader in = new TextFileReader();
        List<String[]> data = in.readFile(new File(fileWithAListOfPlaces), separator, header);
        dataOfPlaces(data);
    }

    /**
     * Tato metoda vytváří pole objektů typu String[]
     *
     * @param data
     */
    private void dataOfPlaces(List<String[]> data) {
        String id, name, address;
        int test_capacity;
        boolean PCR, antigenic, drive_in;
        Place p;
        for (String[] parts : data) {//29c33c7a-fa97-4a31-a192-4ff733d85998;Nemocnice Jindřichův Hradec a.s.;U Nemocnice 380/III, 377 01 Jindřichův Hradec;500;1;1;1;1
            String[] partsNL = {"0", "0", "0", "0", "0", "0", "0", "0"};
            System.arraycopy(parts, 0, partsNL, 0, parts.length);
            id = partsNL[0];
            name = partsNL[1];
            address = partsNL[2];
            test_capacity = Integer.parseInt(partsNL[3]);
            PCR = partsNL[4].equalsIgnoreCase("1") || partsNL[5].equalsIgnoreCase("1");
            antigenic = partsNL[6].equalsIgnoreCase("1");
            drive_in = partsNL[7].equalsIgnoreCase("1");
            internalId += 1;
            p = new Place(internalId, id, name, address, test_capacity, PCR, antigenic, drive_in);
            listOfPlaces.add(p);
        }
    }

    /**
     * Tato metoda hledá místo podle internalId
     *
     * @param internalId
     * @return
     */
    public Place findPlaceByInternalID(int internalId) {
        for (Place place : listOfPlaces) {
            if (place.getInternalID() == internalId) {
                return place;
            }
        }
        throw new NoSuchElementException(internalId + " neexistuje");
    }

    /**
     * Tato metoda třídí místa na základě města a typu testu, na který chce
     * uživatel se příhlasit
     *
     * @param town
     * @param type
     * @return
     */
    public Places filterPlaces(String town, String type) {
        Places filteredPlaces = new Places();
        switch (type) {
            case "PCR":
                listOfPlaces.stream().filter((place) -> (place.getAddress().contains(town) && place.isPCR())).forEachOrdered((place) -> {
                    filteredPlaces.addPlace(place);
                });
                break;
            case "Antigenic":
                listOfPlaces.stream().filter((place) -> (place.getAddress().contains(town) && place.isAntigenic())).forEachOrdered((place) -> {
                    filteredPlaces.addPlace(place);
                });
                break;
            case "Drive_in":
                listOfPlaces.stream().filter((place) -> (place.getAddress().contains(town) && place.isDrive_in())).forEachOrdered((place) -> {
                    filteredPlaces.addPlace(place);
                });
                break;
        }
        return filteredPlaces;
    }

    /**
     * Je to pomocná metoda pro třídění testovácích míst
     *
     * @param p
     */
    private void addPlace(Place p) {
        listOfPlaces.add(p);
    }

    /**
     * Uloží seznam míst do samostatného souboru s vlastním názvem
     *
     * @param resultFile
     * @throws IOException
     */
    public void save(String resultFile) throws IOException {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(resultFile + ".txt"), true)))) {
            String s;
            for (Place place : listOfPlaces) {
                s = String.format("%4d %90s %98s  %6b %6b %6b", place.getInternalID(), place.getName(), place.getAddress(), place.isPCR(), place.isAntigenic(), place.isDrive_in());
                pw.println(s);
            }
        }
    }

    /**
     * Vrátí textový řetězec s testovácími místy
     *
     * @return
     */
    public String printPlaces() {
        StringBuilder sb = new StringBuilder();
        listOfPlaces.forEach((place) -> {
            sb.append(place).append("\n");
        });
        return sb.toString();
    }
}
