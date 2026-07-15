package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;

public class PdfFooter {

    public static void add(

            Document document,

            Prescription prescription

    ) throws Exception {

        //=========================================
        // QR Code
        //=========================================

        BarcodeQRCode qr = new BarcodeQRCode(

                "ELITE CARE HOSPITAL\n"
                        + "Prescription ID : "
                        + prescription.getId(),

                100,

                100,

                null

        );

        Image qrImage = qr.getImage();

        qrImage.scaleAbsolute(65,65);

        //=========================================
        // Footer Table
        //=========================================

        PdfPTable table = new PdfPTable(2);

        table.setWidthPercentage(100);

        table.setWidths(new float[]{1f,2f});

        table.setSpacingBefore(15);

        //=========================================
        // Left Cell (QR)
        //=========================================

        PdfPCell left = new PdfPCell();

        left.setBorder(Rectangle.NO_BORDER);

        left.addElement(

                new Paragraph(

                        "Scan Verification",

                        PdfStyle.LABEL_FONT

                )

        );

        left.addElement(qrImage);

        table.addCell(left);

        //=========================================
        // Right Cell (Doctor)
        //=========================================

        PdfPCell right = new PdfPCell();

        right.setBorder(Rectangle.NO_BORDER);

        right.setHorizontalAlignment(Element.ALIGN_CENTER);

        Paragraph sign = new Paragraph(

                "__________________________"

        );

        sign.setAlignment(Element.ALIGN_CENTER);

        right.addElement(sign);

        Paragraph doctor = new Paragraph(

                "Dr. "
                        + prescription.getDoctor().getUser().getName(),

                PdfStyle.LABEL_FONT

        );

        doctor.setAlignment(Element.ALIGN_CENTER);

        right.addElement(doctor);

        Paragraph specialist = new Paragraph(

                prescription.getDoctor().getSpecialization(),

                PdfStyle.VALUE_FONT

        );

        specialist.setAlignment(Element.ALIGN_CENTER);

        right.addElement(specialist);

        table.addCell(right);

        document.add(table);

        //=========================================
        // Footer Text
        //=========================================

        Paragraph footer = new Paragraph(

                "ELITE CARE HOSPITAL\n"
                        + "Caring Beyond Treatment",

                PdfStyle.VALUE_FONT

        );

        footer.setAlignment(Element.ALIGN_CENTER);

        footer.setSpacingBefore(10);

        document.add(footer);

    }

}