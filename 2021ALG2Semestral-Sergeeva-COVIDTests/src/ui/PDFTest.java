package ui;

import app.Place;
import app.Places;
import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import static ui.ReservationApp.getInformationAboutPlaces;

/**
 *
 * @author Viktoriia Sergeeva
 */
public class PDFTest {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws org.apache.pdfbox.exceptions.COSVisitorException
     */
    static Places places = new Places();

    public static void main(String[] args) throws IOException, COSVisitorException {
        getInformationAboutPlaces();
        String filename = "EmptyPdf.pdf";
        try {
            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();

            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);
            //Creating the PDDocumentInformation object 
            PDDocumentInformation pdd = doc.getDocumentInformation();

            //Setting the author of the document
            pdd.setAuthor("COVID application");

            // Setting the title of the document
            pdd.setTitle("Places");

            //Setting the creator of the document 
            pdd.setCreator("PDF Examples");

            //Setting the subject of the document 
            pdd.setSubject("Places of testin");
            content.beginText();
            content.setFont(PDType1Font.HELVETICA, 14);
            content.moveTextPositionByAmount(20, 700);
            content.drawString("text");
            content.endText();
            content.close();;

            doc.save(filename);
            doc.close();
            System.out.println("PDF created in " + System.getProperty("user.dir"));
        } catch (IOException | COSVisitorException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createPDF(Places places) {

    }
}
