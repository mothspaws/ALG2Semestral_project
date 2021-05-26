package ui;

import app.Places;
import app.Registrations;
import static app.staticMethods.getInformationAboutPlaces;
import static app.staticMethods.konecProgramu;
import static app.staticMethods.loadingOption;
import static app.staticMethods.optionHandling;
import static app.staticMethods.printMenu;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import utils.DateHaveAlreadyExistException;
import utils.NameHaveAlreadyExistException;
import utils.NameHaveNotExistException;

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
