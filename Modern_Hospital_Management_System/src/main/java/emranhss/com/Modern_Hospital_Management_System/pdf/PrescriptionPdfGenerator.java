package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import emranhss.com.Modern_Hospital_Management_System.entity.Prescription;
import emranhss.com.Modern_Hospital_Management_System.entity.Tests;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PrescriptionPdfGenerator {

    /**
     * Main PDF Generate Method
     */
    public byte[] generate(
            Prescription prescription,
            List<Tests> tests
    ){

        try{

            // ===========================================
            // Create Memory Stream
            // ===========================================

            ByteArrayOutputStream out =
                    new ByteArrayOutputStream();

            // ===========================================
            // Create A4 Document
            // ===========================================

            Document document =
                    new Document(
                            PageSize.A4,
                            25,
                            25,
                            25,
                            25
                    );

            // ===========================================
            // Attach Writer
            // ===========================================

            PdfWriter.getInstance(document,out);

            // ===========================================
            // Open PDF
            // ===========================================

            document.open();

            // *******************************************
            // Header
            // *******************************************

            PdfHeader.add(document);

            // *******************************************
            // Patient Section
            // *******************************************

            PdfPatientSection.add(
                    document,
                    prescription
            );

            // *******************************************
            // Doctor Section
            // *******************************************

            PdfDoctorSection.add(
                    document,
                    prescription
            );

            // *******************************************
            // Vital Section
            // *******************************************

            PdfVitalSection.add(
                    document,
                    prescription
            );

            // *******************************************
            // Medicine Table
            // *******************************************

            PdfMedicineTable.add(
                    document,
                    prescription
            );

            // *******************************************
            // Test Table
            // *******************************************

            PdfTestTable.add(
                    document,
                    tests
            );

            // *******************************************
            // Advice
            // *******************************************

            PdfAdviceSection.add(
                    document,
                    prescription
            );

            // *******************************************
            // Footer
            // *******************************************

            PdfFooter.add(
                    document,
                    prescription
            );

            document.close();

            return out.toByteArray();

        }

        catch (Exception e){

            throw new RuntimeException(e);

        }

    }

}