package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;

public class PdfVitalSection {

    /**
     * ============================================
     * Patient Vital Information
     * ============================================
     */
    public static void add(

            Document document,

            Prescription prescription

    ) throws Exception {

        //------------------------------------------------
        // Section Title
        //------------------------------------------------

        Paragraph title = new Paragraph(

                "PATIENT VITALS",

                PdfStyle.TITLE_FONT

        );

        title.setSpacingBefore(12);
        title.setSpacingAfter(10);

        document.add(title);

        //------------------------------------------------
        // Table
        //------------------------------------------------

        PdfPTable table = new PdfPTable(4);

        table.setWidthPercentage(100);

        table.setWidths(new float[]{1,1,1,1});

        table.setSpacingAfter(15);

        //------------------------------------------------
        // Blood Pressure
        //------------------------------------------------

        table.addCell(createVitalCard(

                "Blood Pressure",

                prescription.getBloodPressure(),

                new BaseColor(33,150,243)

        ));

        //------------------------------------------------
        // Pulse
        //------------------------------------------------

        table.addCell(createVitalCard(

                "Pulse Rate",

                prescription.getPulseRate(),

                new BaseColor(76,175,80)

        ));

        //------------------------------------------------
        // Temperature
        //------------------------------------------------

        table.addCell(createVitalCard(

                "Temperature",

                prescription.getBodyTemperature(),

                new BaseColor(255,87,34)

        ));

        //------------------------------------------------
        // Weight
        //------------------------------------------------

        table.addCell(createVitalCard(

                "Weight",

                prescription.getWeight(),

                new BaseColor(156,39,176)

        ));

        document.add(table);

    }

    /**
     * ============================================
     * Create Vital Card
     * ============================================
     */

    private static PdfPCell createVitalCard(

            String title,

            String value,

            BaseColor color

    ){

        PdfPCell cell = new PdfPCell();

        cell.setPadding(12);

        cell.setBackgroundColor(color);

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell.setFixedHeight(80);

        cell.setBorderColor(BaseColor.WHITE);

        Paragraph p1 = new Paragraph(

                title,

                new Font(

                        Font.FontFamily.HELVETICA,

                        11,

                        Font.BOLD,

                        BaseColor.WHITE

                )

        );

        p1.setAlignment(Element.ALIGN_CENTER);

        Paragraph p2 = new Paragraph(

                value == null ? "-" : value,

                new Font(

                        Font.FontFamily.HELVETICA,

                        15,

                        Font.BOLD,

                        BaseColor.WHITE

                )

        );

        p2.setAlignment(Element.ALIGN_CENTER);

        cell.addElement(p1);

        cell.addElement(p2);

        return cell;

    }

}