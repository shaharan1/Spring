package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;
import emranhss.com.Modern_Hospital_Management_System.entity.PrescriptionItem;

public class PdfMedicineTable {

    /**
     * =====================================================
     * Medicine Table
     * =====================================================
     */
    public static void add(

            Document document,

            Prescription prescription

    ) throws Exception {

        //--------------------------------------------------
        // Title
        //--------------------------------------------------

        Paragraph title = new Paragraph(

                "PRESCRIBED MEDICINES",

                PdfStyle.TITLE_FONT

        );

        title.setSpacingBefore(15);
        title.setSpacingAfter(10);

        document.add(title);

        //--------------------------------------------------
        // Table
        //--------------------------------------------------

        PdfPTable table = new PdfPTable(6);

        table.setWidthPercentage(100);

        table.setWidths(new float[]{0.8f,4f,2f,2f,2f,3f});

        //--------------------------------------------------
        // Header
        //--------------------------------------------------

        addHeader(table,"SL");
        addHeader(table,"Medicine");
        addHeader(table,"Type");
        addHeader(table,"Dose");
        addHeader(table,"Duration");
        addHeader(table,"Instruction");

        //--------------------------------------------------
        // Data
        //--------------------------------------------------

        int sl = 1;

        boolean alternate = false;

        for(PrescriptionItem item : prescription.getPrescriptionItems()){

            BaseColor rowColor =
                    alternate ?
                            new BaseColor(250,250,250)
                            :
                            BaseColor.WHITE;

            addValue(table,String.valueOf(sl++),rowColor);

            addValue(
                    table,
                    item.getMedicine().getMedicineName(),
                    rowColor
            );

            addValue(
                    table,
                    item.getMedicineType()==null ?
                            "-" :
                            item.getMedicineType(),
                    rowColor
            );

            addValue(
                    table,
                    item.getDosage(),
                    rowColor
            );

            addValue(
                    table,
                    item.getDuration(),
                    rowColor
            );

            addValue(
                    table,
                    item.getInstruction(),
                    rowColor
            );

            alternate = !alternate;

        }

        document.add(table);

    }

    /**
     * =====================================================
     * Header Cell
     * =====================================================
     */

    private static void addHeader(

            PdfPTable table,

            String text

    ){

        PdfPCell cell =
                new PdfPCell(
                        new Phrase(
                                text,
                                PdfStyle.SECTION_FONT
                        )
                );

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell.setBackgroundColor(
                new BaseColor(33,150,243)
        );

        cell.setPadding(8);

        table.addCell(cell);

    }

    /**
     * =====================================================
     * Value Cell
     * =====================================================
     */

    private static void addValue(

            PdfPTable table,

            String value,

            BaseColor color

    ){

        PdfPCell cell =
                new PdfPCell(
                        new Phrase(
                                value==null?"":value,
                                PdfStyle.VALUE_FONT
                        )
                );

        cell.setBackgroundColor(color);

        cell.setPadding(8);

        table.addCell(cell);

    }

}