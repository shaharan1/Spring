package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PdfHeader {

    /**
     * =====================================================
     * Hospital Header
     * =====================================================
     */
    public static void add(Document document) throws Exception {

        // =====================================================
        // Load Hospital Logo
        // =====================================================

        Image logo = Image.getInstance(
                "src/main/resources/static/images/logo/elite_care_hospital_Logo.png"
        );

        logo.scaleAbsolute(90,90);

        // =====================================================
        // Header Table
        // =====================================================

        PdfPTable table = new PdfPTable(2);

        table.setWidthPercentage(100);

        table.setWidths(new float[]{1,4});

        table.setSpacingAfter(15);

        // =====================================================
        // Logo Cell
        // =====================================================

        PdfPCell logoCell = new PdfPCell();

        logoCell.setBorder(Rectangle.NO_BORDER);

        logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        logoCell.addElement(logo);

        table.addCell(logoCell);

        // =====================================================
        // Hospital Information
        // =====================================================

        PdfPCell infoCell = new PdfPCell();

        infoCell.setBorder(Rectangle.NO_BORDER);

        infoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);

        Paragraph hospital =
                new Paragraph(
                        "ELITE CARE HOSPITAL",
                        PdfStyle.HOSPITAL_FONT
                );

        Paragraph address =
                new Paragraph(
                        "House #25, Road #12, Dhanmondi, Dhaka-1209",
                        PdfStyle.VALUE_FONT
                );

        Paragraph phone =
                new Paragraph(
                        "Phone : +880 1711-123456",
                        PdfStyle.VALUE_FONT
                );

        Paragraph email =
                new Paragraph(
                        "Email : info@elitecarehospital.com",
                        PdfStyle.VALUE_FONT
                );

        Paragraph line =
                new Paragraph(
                        "-----------------------------------------------------------"
                );

        Paragraph title =
                new Paragraph(
                        "PRESCRIPTION",
                        PdfStyle.TITLE_FONT
                );

        title.setSpacingBefore(5);

        infoCell.addElement(hospital);
        infoCell.addElement(address);
        infoCell.addElement(phone);
        infoCell.addElement(email);
        infoCell.addElement(line);
        infoCell.addElement(title);

        table.addCell(infoCell);

        document.add(table);

    }

}