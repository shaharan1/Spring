package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;

public class PdfVitalSection {

    /**
     * ==========================================
     * Patient Vital Information
     * ==========================================
     */
    public static void add(
            Document document,
            Prescription prescription
    ) throws Exception {

        Paragraph title = new Paragraph(
                "PATIENT VITALS",
                PdfStyle.TITLE_FONT
        );

        title.setSpacingBefore(10);
        title.setSpacingAfter(8);

        document.add(title);

        PdfPTable table = new PdfPTable(4);

        table.setWidthPercentage(100);
        table.setSpacingAfter(15);

        String[] headers = {

                "Blood Pressure",
                "Pulse",
                "Temperature",
                "Weight"

        };

        for (String h : headers) {

            PdfPCell cell =
                    new PdfPCell(
                            new Phrase(
                                    h,
                                    PdfStyle.SECTION_FONT
                            )
                    );

            cell.setBackgroundColor(PdfStyle.PRIMARY);

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);

            cell.setPadding(8);

            table.addCell(cell);

        }

        table.addCell(
                prescription.getBloodPressure() == null
                        ? "-"
                        : prescription.getBloodPressure()
        );

        table.addCell(
                prescription.getPulseRate() == null
                        ? "-"
                        : prescription.getPulseRate()
        );

        table.addCell(
                prescription.getBodyTemperature() == null
                        ? "-"
                        : prescription.getBodyTemperature()
        );

        table.addCell(
                prescription.getWeight() == null
                        ? "-"
                        : prescription.getWeight()
        );

        document.add(table);

    }

}