package ui;

import static app.staticMethods.getInformationAboutPlaces;
import static app.staticMethods.konecProgramu;
import static app.staticMethods.loadingOption;
import static app.staticMethods.optionHandling;
import static app.staticMethods.printMenu;
import java.io.IOException;

/**
 *
 * @author Viktoriia Sergeeva
 */
public class ReservationApp {

    public static void main(String[] args) throws IOException {
        getInformationAboutPlaces();
        do {
            printMenu();
            int option = loadingOption();
            optionHandling(option);
        } while (!konecProgramu);
    }

}
