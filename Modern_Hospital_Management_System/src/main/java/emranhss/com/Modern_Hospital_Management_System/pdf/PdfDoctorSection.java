package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Doctor;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;

public class PdfDoctorSection {

    /**
     * =====================================================
     * Doctor Information Section
     * =====================================================
     */
    public static void add(
            Document document,
            Prescription prescription
    ) throws Exception {

        Doctor doctor = prescription.getDoctor();

        Paragraph title = new Paragraph(
                "DOCTOR INFORMATION",
                PdfStyle.TITLE_FONT
        );

        title.setSpacingBefore(10);
        title.setSpacingAfter(10);

        document.add(title);

        PdfPTable table = new PdfPTable(4);

        table.setWidthPercentage(100);
        table.setWidths(new float[]{2f, 3f, 2f, 3f});
        table.setSpacingAfter(15);

        //--------------------------------------------------
        // Row-1
        //--------------------------------------------------

        table.addCell(labelCell("Doctor"));

        table.addCell(valueCell(
                "Dr. " + doctor.getUser().getName()
        ));

        table.addCell(labelCell("Specialization"));

        table.addCell(valueCell(
                doctor.getSpecialization()
        ));

        //--------------------------------------------------
        // Row-2
        //--------------------------------------------------

        table.addCell(labelCell("Qualification"));

        table.addCell(valueCell(
                doctor.getStudy()
        ));

        table.addCell(labelCell("BMDC No"));

        table.addCell(valueCell(
                doctor.getRegistrationNumber()
        ));

        //--------------------------------------------------
        // Row-3
        //--------------------------------------------------

        table.addCell(labelCell("Consultation Fee"));

        table.addCell(valueCell(
                "Tk. " + doctor.getConsultationFee()
        ));

        table.addCell(labelCell("Chamber"));

        table.addCell(valueCell(
                doctor.getChamber()
        ));

        //--------------------------------------------------
        // Row-4
        //--------------------------------------------------

        table.addCell(labelCell("Phone"));

        table.addCell(valueCell(
                doctor.getUser().getPhone()
        ));

        table.addCell(labelCell("Status"));

        table.addCell(valueCell(
                doctor.getStatus()
        ));

        document.add(table);

        //--------------------------------------------------
        // Doctor Photo (Optional)
        //--------------------------------------------------

        if (doctor.getPhoto() != null && !doctor.getPhoto().isEmpty()) {

            try {

                Image photo = Image.getInstance(
                        "src/main/resources/static/" + doctor.getPhoto()
                );

                photo.scaleAbsolute(90, 90);

                photo.setAlignment(Image.ALIGN_RIGHT);

                document.add(photo);

            } catch (Exception e) {

                // যদি ছবি না পাওয়া যায় তাহলে PDF বন্ধ হবে না
                System.out.println("Doctor photo not found.");

            }

        }

    }

    //======================================================
    // Label Cell
    //======================================================

    private static PdfPCell labelCell(String text) {

        PdfPCell cell = new PdfPCell(
                new Phrase(text, PdfStyle.LABEL_FONT)
        );

        cell.setPadding(8);

        cell.setBackgroundColor(new BaseColor(235, 245, 255));

        return cell;
    }

    //======================================================
    // Value Cell
    //======================================================

    private static PdfPCell valueCell(String text) {

        PdfPCell cell = new PdfPCell(
                new Phrase(text == null ? "" : text, PdfStyle.VALUE_FONT)
        );

        cell.setPadding(8);

        return cell;
    }

}