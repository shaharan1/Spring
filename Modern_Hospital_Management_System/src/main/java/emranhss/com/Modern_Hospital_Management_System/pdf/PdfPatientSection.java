package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;

public class PdfPatientSection {

    /**
     * ==========================================
     * Patient Information Card
     * ==========================================
     */
    public static void add(
            Document document,
            Prescription prescription
    ) throws Exception {

        PdfPTable table = new PdfPTable(1);

        table.setWidthPercentage(100);

        table.setSpacingAfter(12);

        PdfPCell cell = new PdfPCell();

        cell.setPadding(10);

        cell.setBorderColor(PdfStyle.PRIMARY);

        Paragraph title =
                new Paragraph(
                        "PATIENT INFORMATION",
                        PdfStyle.TITLE_FONT
                );

        title.setSpacingAfter(8);

        cell.addElement(title);

        cell.addElement(new Paragraph(
                "Name : "
                        + prescription.getPatient().getName(),
                PdfStyle.VALUE_FONT));

        cell.addElement(new Paragraph(
                "Phone : "
                        + prescription.getPatient().getPhone(),
                PdfStyle.VALUE_FONT));

        cell.addElement(new Paragraph(
                "Gender : "
                        + prescription.getPatient().getGender(),
                PdfStyle.VALUE_FONT));

        cell.addElement(new Paragraph(
                "Blood Group : "
                        + (prescription.getPatient().getBloodGroup() == null
                        ? "-"
                        : prescription.getPatient().getBloodGroup()),
                PdfStyle.VALUE_FONT));

        if (prescription.getPatient().getDateOfBirth() != null) {

            cell.addElement(new Paragraph(
                    "Date of Birth : "
                            + prescription.getPatient().getDateOfBirth(),
                    PdfStyle.VALUE_FONT));
        }

        table.addCell(cell);

        document.add(table);

    }

}