package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;

import java.time.format.DateTimeFormatter;

public class PdfPatientSection {

    /**
     * =====================================================
     * Patient Information Section
     * =====================================================
     */
    public static void add(

            Document document,

            Prescription prescription

    ) throws Exception {

        //==================================================
        // Section Title
        //==================================================

        Paragraph title =
                new Paragraph(
                        "PATIENT INFORMATION",
                        PdfStyle.TITLE_FONT
                );

        title.setSpacingAfter(10);

        document.add(title);

        //==================================================
        // Table
        //==================================================

        PdfPTable table = new PdfPTable(4);

        table.setWidthPercentage(100);

        table.setWidths(new float[]{2f,3f,2f,3f});

        table.setSpacingAfter(15);

        //==================================================
        // Row-1
        //==================================================

        table.addCell(labelCell("Patient Name"));

        table.addCell(valueCell(
                prescription.getPatient().getName()
        ));

        table.addCell(labelCell("Gender"));

        table.addCell(valueCell(
                String.valueOf(
                        prescription.getPatient().getGender()
                )
        ));

        //==================================================
        // Row-2
        //==================================================

        table.addCell(labelCell("Phone"));

        table.addCell(valueCell(
                prescription.getPatient().getPhone()
        ));

        table.addCell(labelCell("Blood Group"));

        table.addCell(valueCell(
                prescription.getPatient().getBloodGroup()
        ));

        //==================================================
        // Row-3
        //==================================================

        table.addCell(labelCell("Age"));

        table.addCell(valueCell(
                prescription.getPatient().getAge()+" Years"
        ));

        table.addCell(labelCell("Visit Date"));

        table.addCell(valueCell(

                prescription.getCreatedDate()

                        .format(
                                DateTimeFormatter.ofPattern("dd MMM yyyy")
                        )

        ));

        document.add(table);

    }

    /**
     * =====================================================
     * Label Cell
     * =====================================================
     */

    private static PdfPCell labelCell(String text){

        PdfPCell cell =
                new PdfPCell(
                        new Phrase(
                                text,
                                PdfStyle.LABEL_FONT
                        )
                );

        cell.setPadding(8);

        cell.setBackgroundColor(
                new BaseColor(235,245,255)
        );

        return cell;

    }

    /**
     * =====================================================
     * Value Cell
     * =====================================================
     */

    private static PdfPCell valueCell(String text){

        PdfPCell cell =
                new PdfPCell(
                        new Phrase(
                                text==null?"":text,
                                PdfStyle.VALUE_FONT
                        )
                );

        cell.setPadding(8);

        return cell;

    }

}