package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;
import emranhss.com.Modern_Hospital_Management_System.entity.PrescriptionItem;

public class PdfMedicineTable {

    /**
     * ==========================================
     * Medicine Table
     * ==========================================
     */
    public static void add(
            Document document,
            Prescription prescription
    ) throws Exception {

        Paragraph title = new Paragraph(
                "PRESCRIBED MEDICINES",
                PdfStyle.TITLE_FONT
        );

        title.setSpacingBefore(10);
        title.setSpacingAfter(8);

        document.add(title);

        PdfPTable table = new PdfPTable(5);

        table.setWidthPercentage(100);

        table.setWidths(new float[]{1, 4, 2, 2, 3});

        String[] headers = {

                "#",
                "Medicine",
                "Dose",
                "Duration",
                "Instruction"

        };

        for (String h : headers) {

            PdfPCell cell =
                    new PdfPCell(
                            new Phrase(
                                    h,
                                    PdfStyle.SECTION_FONT
                            )
                    );

            cell.setBackgroundColor(PdfStyle.GREEN);

            cell.setHorizontalAlignment(Element.ALIGN_CENTER);

            cell.setPadding(8);

            table.addCell(cell);

        }

        int serial = 1;

        for (PrescriptionItem item : prescription.getPrescriptionItems()) {

            table.addCell(String.valueOf(serial++));

            table.addCell(
                    item.getMedicine().getMedicineName()
            );

            table.addCell(
                    item.getDosage() == null
                            ? "-"
                            : item.getDosage()
            );

            table.addCell(
                    item.getDuration() == null
                            ? "-"
                            : item.getDuration()
            );

            table.addCell(
                    item.getInstruction() == null
                            ? "-"
                            : item.getInstruction()
            );

        }

        document.add(table);

    }

}