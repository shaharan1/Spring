package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;

import java.time.format.DateTimeFormatter;

public class PdfDoctorSection {

    /**
     * ==========================================
     * Doctor Information
     * ==========================================
     */
    public static void add(
            Document document,
            Prescription prescription
    ) throws Exception {

        PdfPTable table = new PdfPTable(1);

        table.setWidthPercentage(100);

        table.setSpacingAfter(15);

        PdfPCell cell = new PdfPCell();

        cell.setPadding(10);

        cell.setBorderColor(PdfStyle.GREEN);

        Paragraph title =
                new Paragraph(
                        "DOCTOR INFORMATION",
                        PdfStyle.TITLE_FONT
                );

        title.setSpacingAfter(8);

        cell.addElement(title);

        cell.addElement(new Paragraph(
                "Doctor : Dr. "
                        + prescription.getDoctor().getUser().getName(),
                PdfStyle.VALUE_FONT));

        cell.addElement(new Paragraph(
                "Specialization : "
                        + prescription.getDoctor().getSpecialization(),
                PdfStyle.VALUE_FONT));

        cell.addElement(new Paragraph(
                "Consultation Fee : "
                        + prescription.getDoctor().getConsultationFee()
                        + " BDT",
                PdfStyle.VALUE_FONT));

        cell.addElement(new Paragraph(
                "Prescription Date : "
                        + prescription.getCreatedDate()
                        .format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                PdfStyle.VALUE_FONT));

        table.addCell(cell);

        document.add(table);

    }

}