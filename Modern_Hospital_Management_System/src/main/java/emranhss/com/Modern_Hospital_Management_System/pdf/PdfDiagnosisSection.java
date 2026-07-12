package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;

public class PdfDiagnosisSection {

    /**
     * ======================================================
     * Diagnosis Section
     * ======================================================
     */
    public static void add(

            Document document,

            Prescription prescription

    ) throws Exception {

        //---------------------------------------------------
        // Title
        //---------------------------------------------------

        Paragraph title = new Paragraph(

                "CLINICAL FINDINGS",

                PdfStyle.TITLE_FONT

        );

        title.setSpacingBefore(15);
        title.setSpacingAfter(10);

        document.add(title);

        //---------------------------------------------------
        // Diagnosis
        //---------------------------------------------------

        document.add(createCard(

                "Diagnosis",

                prescription.getDiagnosis(),

                new BaseColor(232,245,233)

        ));

        //---------------------------------------------------
        // Chief Complaints
        //---------------------------------------------------

        document.add(createCard(

                "Chief Complaints",

                prescription.getChiefComplaints(),

                new BaseColor(255,248,225)

        ));

        //---------------------------------------------------
        // Symptoms
        //---------------------------------------------------

        document.add(createCard(

                "Symptoms",

                prescription.getSymptoms(),

                new BaseColor(232,240,254)

        ));

    }

    /**
     * ======================================================
     * Create Card
     * ======================================================
     */

    private static PdfPTable createCard(

            String heading,

            String value,

            BaseColor color

    ){

        PdfPTable table = new PdfPTable(1);

        table.setWidthPercentage(100);

        table.setSpacingAfter(8);

        PdfPCell cell = new PdfPCell();

        cell.setPadding(12);

        cell.setBackgroundColor(color);

        cell.setBorderColor(new BaseColor(220,220,220));

        Paragraph h = new Paragraph(

                heading,

                PdfStyle.LABEL_FONT

        );

        h.setSpacingAfter(5);

        cell.addElement(h);

        Paragraph body = new Paragraph(

                value == null ? "-" : value,

                PdfStyle.VALUE_FONT

        );

        cell.addElement(body);

        table.addCell(cell);

        return table;

    }

}