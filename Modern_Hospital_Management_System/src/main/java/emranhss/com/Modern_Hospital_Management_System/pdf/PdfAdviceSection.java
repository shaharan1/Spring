package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;

import java.time.format.DateTimeFormatter;

public class PdfAdviceSection {

    /**
     * ==========================================
     * Doctor Advice
     * ==========================================
     */
    public static void add(
            Document document,
            Prescription prescription
    ) throws Exception {

        // ===========================
        // Advice Title
        // ===========================

        Paragraph title =
                new Paragraph(
                        "DOCTOR'S ADVICE",
                        PdfStyle.TITLE_FONT
                );

        title.setSpacingBefore(15);

        document.add(title);

        // ===========================
        // Advice Box
        // ===========================

        PdfPTable adviceTable = new PdfPTable(1);

        adviceTable.setWidthPercentage(100);

        PdfPCell adviceCell = new PdfPCell();

        adviceCell.setPadding(12);

//        adviceCell.setBackgroundColor(
//                PdfStyle.LIGHT_GREEN
//        );

        adviceCell.addElement(

                new Paragraph(

                        prescription.getNotes()==null
                                ? "No Advice"
                                : prescription.getNotes(),

                        PdfStyle.VALUE_FONT

                )

        );

        adviceTable.addCell(adviceCell);

        document.add(adviceTable);

        // ===========================
        // Follow Up Title
        // ===========================

        Paragraph followTitle =
                new Paragraph(

                        "NEXT FOLLOW-UP",

                        PdfStyle.TITLE_FONT

                );

        followTitle.setSpacingBefore(15);

        document.add(followTitle);

        // ===========================
        // Follow Up Card
        // ===========================

        PdfPTable followTable =
                new PdfPTable(1);

        followTable.setWidthPercentage(40);

        PdfPCell followCell =
                new PdfPCell();

        followCell.setPadding(12);

        followCell.setHorizontalAlignment(
                Element.ALIGN_CENTER
        );

//        followCell.setBackgroundColor(
//                PdfStyle.LIGHT_ORANGE
//        );

        String date = "Not Scheduled";

        if(prescription.getNextFollowUpDate()!=null){

            date = prescription.getNextFollowUpDate()

                    .format(

                            DateTimeFormatter.ofPattern(
                                    "dd MMM yyyy"
                            )

                    );

        }

        Paragraph follow =
                new Paragraph(

                        date,

                        PdfStyle.FOLLOWUP_FONT

                );

        follow.setAlignment(Element.ALIGN_CENTER);

        followCell.addElement(follow);

        followTable.addCell(followCell);

        document.add(followTable);

    }

}