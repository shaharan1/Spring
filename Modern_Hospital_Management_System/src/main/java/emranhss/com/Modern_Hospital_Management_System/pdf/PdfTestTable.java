package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Tests;

import java.util.List;

public class PdfTestTable {

    /**
     * ==========================================
     * Laboratory Investigation
     * ==========================================
     */
    public static void add(
            Document document,
            List<Tests> tests
    ) throws Exception {

        // যদি কোন Test না থাকে তাহলে কিছু দেখাবে না
        if (tests == null || tests.isEmpty()) {
            return;
        }

        Paragraph title = new Paragraph(
                "LABORATORY INVESTIGATIONS",
                PdfStyle.TITLE_FONT
        );

        title.setSpacingBefore(15);
        title.setSpacingAfter(8);

        document.add(title);

        PdfPTable table = new PdfPTable(4);

        table.setWidthPercentage(100);

        table.setWidths(new float[]{2,5,2,3});

        String[] headers = {

                "Code",

                "Test Name",

                "Status",

                "Normal Range"

        };

        for(String h : headers){

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

        for(Tests t : tests){

            table.addCell(
                    t.getTestMaster().getTestCode()
            );

            table.addCell(
                    t.getTestMaster().getTestName()
            );

            table.addCell(
                    t.getOrderStatus()
            );

            table.addCell(
                    t.getTestMaster().getNormalRange()
            );

        }

        document.add(table);

    }

}