package emranhss.com.Modern_Hospital_Management_System.serviceimp;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import emranhss.com.Modern_Hospital_Management_System.dto.mapper.PrescriptionMapper;
import emranhss.com.Modern_Hospital_Management_System.dto.request.PrescriptionRequest;
import emranhss.com.Modern_Hospital_Management_System.dto.response.PrescriptionResponse;
import emranhss.com.Modern_Hospital_Management_System.entity.*;
import emranhss.com.Modern_Hospital_Management_System.exception.ResourceNotFoundException;
import emranhss.com.Modern_Hospital_Management_System.repository.*;
import emranhss.com.Modern_Hospital_Management_System.service.PrescriptionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.time.format.DateTimeFormatter;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImp implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final MedicineRepository medicineRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final TestsRepository testsRepository;
    private final TestMasterRepository testMasterRepository;

    @Override
    @Transactional
    public PrescriptionResponse createPrescription(PrescriptionRequest request) {

        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id: " + request.getAppointmentId()));

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with id: " + request.getDoctorId()));

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id: " + request.getPatientId()));

        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setDiagnosis(request.getDiagnosis());
        prescription.setChiefComplaints(request.getChiefComplaints());
        prescription.setSymptoms(request.getSymptoms());
        prescription.setBloodPressure(request.getBloodPressure());
        prescription.setPulseRate(request.getPulseRate());
        prescription.setBodyTemperature(request.getBodyTemperature());
        prescription.setWeight(request.getWeight());
        prescription.setNotes(request.getNotes());
        prescription.setNextFollowUpDate(request.getNextFollowUpDate());

        // Process and map prescription items (medicines)
        if (request.getPrescriptionItems() != null) {
            List<PrescriptionItem> items = request.getPrescriptionItems().stream()
                    .map(itemDto -> {
                        Medicine medicine = medicineRepository.findById(itemDto.getMedicineId())
                                .orElseThrow(() -> new ResourceNotFoundException("Medicine not found with id: " + itemDto.getMedicineId()));

                        PrescriptionItem item = new PrescriptionItem();
                        item.setPrescription(prescription);
                        item.setMedicine(medicine);
                        item.setDosage(itemDto.getDosage());
                        item.setDuration(itemDto.getDuration());
                        item.setInstruction(itemDto.getInstruction());
                        return item;

                    })
                    .collect(Collectors.toList());

            prescription.setPrescriptionItems(items);
        }

        // Save prescription entity first
        Prescription savedPrescription = prescriptionRepository.save(prescription);

        // Process and assign laboratory tests
        if (request.getTestIds() != null && !request.getTestIds().isEmpty()) {
            List<Tests> assignedTests = request.getTestIds().stream()
                    .map(testId -> {
                        TestMaster master = testMasterRepository.findById(testId)
                                .orElseThrow(() -> new ResourceNotFoundException("Test not found with id: " + testId));

                        Tests test = new Tests();
                        test.setPrescription(savedPrescription);
                        test.setPatient(patient);
                        test.setPrescribedBy(doctor);
                        test.setTestMaster(master);
                        test.setOrderStatus("PENDING");
                        return test;
                    })
                    .collect(Collectors.toList());

            testsRepository.saveAll(assignedTests); // Batch save optimized performance
        }

        return prescriptionMapper.toResponse(savedPrescription);
    }

    @Override
    public PrescriptionResponse getPrescriptionById(Long id) {
        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + id));
        return prescriptionMapper.toResponse(prescription);
    }

    @Override
    public List<PrescriptionResponse> getPrescriptionsByPatientId(Long patientId) {
        return prescriptionRepository.findByPatientId(patientId).stream()
                .map(prescriptionMapper::toResponse)
                .collect(Collectors.toList());
    }




    @Override
    public byte[] generatePdf(Long id) {

        Prescription prescription = prescriptionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prescription not found"));

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            Document document = new Document(PageSize.A4,25,25,25,25);

            PdfWriter writer = PdfWriter.getInstance(document, out);

            document.open();

            Font hospitalFont =
                    new Font(Font.FontFamily.HELVETICA,22,Font.BOLD,BaseColor.BLUE);

            Font titleFont =
                    new Font(Font.FontFamily.HELVETICA,16,Font.BOLD,BaseColor.DARK_GRAY);

            Font sectionFont =
                    new Font(Font.FontFamily.HELVETICA,13,Font.BOLD,BaseColor.WHITE);

            Font labelFont =
                    new Font(Font.FontFamily.HELVETICA,10,Font.BOLD);

            Font valueFont =
                    new Font(Font.FontFamily.HELVETICA,10);

//            --------Logo-----
            Image logo = Image.getInstance(
                    "src/main/resources/static/images/logo/elite_care_hospital_Logo.png"
            );

            logo.scaleAbsolute(90, 90);

//            ------Header Table------
            PdfPTable header = new PdfPTable(2);

            header.setWidthPercentage(100);

            header.setWidths(new float[]{1,4});

            header.setSpacingAfter(15);

//            -------Logo Cell----------
            PdfPCell logoCell = new PdfPCell();

            logoCell.setBorder(Rectangle.NO_BORDER);

            logoCell.addElement(logo);

            header.addCell(logoCell);




//            ---------Hospital Info---------
            Paragraph hospitalName = new Paragraph(
                    "ELITE CARE HOSPITAL",
                    hospitalFont
            );

            hospitalName.setAlignment(Element.ALIGN_LEFT);

            Paragraph address = new Paragraph(
                    "House #25, Road #12, Dhanmondi, Dhaka-1209",
                    valueFont
            );

            Paragraph phone = new Paragraph(
                    "Phone : +880 1711-123456",
                    valueFont
            );

            Paragraph email = new Paragraph(
                    "Email : info@elitecarehospital.com",
                    valueFont
            );

            PdfPCell infoCell = new PdfPCell();

            infoCell.setBorder(Rectangle.NO_BORDER);

            infoCell.addElement(hospitalName);
            infoCell.addElement(address);
            infoCell.addElement(phone);
            infoCell.addElement(email);

            header.addCell(infoCell);

            document.add(header);

            PdfPTable infoTable = new PdfPTable(2);

            infoTable.setWidthPercentage(100);

            infoTable.setSpacingAfter(15);

            PdfPCell left = new PdfPCell();

            left.setPadding(10);

            left.addElement(new Paragraph(
                    "Patient Information",
                    titleFont));

            left.addElement(new Paragraph(
                    "Name : "
                            + prescription.getPatient().getName(),
                    valueFont));

            left.addElement(new Paragraph(
                    "Phone : "
                            + prescription.getPatient().getPhone(),
                    valueFont));

            left.addElement(new Paragraph(
                    "Gender : "
                            + prescription.getPatient().getGender(),
                    valueFont));

            left.addElement(new Paragraph(
                    "Blood Group : "
                            + prescription.getPatient().getBloodGroup(),
                    valueFont));

            infoTable.addCell(left);

            PdfPCell right = new PdfPCell();

            right.setPadding(10);

            right.addElement(new Paragraph(
                    "Doctor Information",
                    titleFont));

            right.addElement(new Paragraph(
                    "Dr. "
                            + prescription.getDoctor().getUser().getName(),
                    valueFont));

            right.addElement(new Paragraph(
                    prescription.getDoctor().getSpecialization(),
                    valueFont));

            right.addElement(new Paragraph(
                    "Consultation Fee : "
                            + prescription.getDoctor().getConsultationFee(),
                    valueFont));

            right.addElement(new Paragraph(
                    prescription.getCreatedDate()
                            .format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                    valueFont));

            infoTable.addCell(right);

            document.add(infoTable);

            PdfPTable vital = new PdfPTable(4);

            vital.setWidthPercentage(100);

            vital.setSpacingAfter(15);

            String[] headers = {

                    "Blood Pressure",

                    "Pulse",

                    "Temperature",

                    "Weight"

            };

            for(String h:headers){

                PdfPCell cell =
                        new PdfPCell(new Phrase(h,sectionFont));

                cell.setBackgroundColor(BaseColor.BLUE);

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                cell.setPadding(8);

                vital.addCell(cell);

            }

            vital.addCell(prescription.getBloodPressure());

            vital.addCell(prescription.getPulseRate());

            vital.addCell(prescription.getBodyTemperature());

            vital.addCell(prescription.getWeight());

            document.add(vital);

            PdfPTable diagnosis = new PdfPTable(1);

            diagnosis.setWidthPercentage(100);

            PdfPCell cell = new PdfPCell();

            cell.setPadding(10);

            cell.addElement(new Paragraph(
                    "Diagnosis",
                    titleFont));

            cell.addElement(new Paragraph(
                    prescription.getDiagnosis(),
                    valueFont));

            cell.addElement(new Paragraph(""));

            cell.addElement(new Paragraph(
                    "Chief Complaints",
                    titleFont));

            cell.addElement(new Paragraph(
                    prescription.getChiefComplaints(),
                    valueFont));

            cell.addElement(new Paragraph(""));

            cell.addElement(new Paragraph(
                    "Symptoms",
                    titleFont));

            cell.addElement(new Paragraph(
                    prescription.getSymptoms(),
                    valueFont));

            diagnosis.addCell(cell);

            document.add(diagnosis);

            Paragraph medicineTitle = new Paragraph("PRESCRIBED MEDICINES", titleFont);
            medicineTitle.setSpacingBefore(15);
            medicineTitle.setSpacingAfter(8);
            document.add(medicineTitle);

            PdfPTable medicineTable = new PdfPTable(5);

            medicineTable.setWidthPercentage(100);

            medicineTable.setWidths(new float[]{1f,4f,2f,2f,3f});

            medicineTable.setSpacingAfter(20);

            String[] medicineHeaders = {

                    "#",

                    "Medicine",

                    "Dose",

                    "Duration",

                    "Instruction"

            };

            for(String h : medicineHeaders){

                PdfPCell cell = new PdfPCell(new Phrase(h, sectionFont));

                cell.setBackgroundColor(new BaseColor(33,150,243));

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                cell.setPadding(8);

                medicineTable.addCell(cell);

            }

            int serial = 1;

            for(PrescriptionItem item : prescription.getPrescriptionItems()){

                medicineTable.addCell(String.valueOf(serial++));

                medicineTable.addCell(item.getMedicine().getMedicineName());

                medicineTable.addCell(item.getDosage());

                medicineTable.addCell(item.getDuration());

                medicineTable.addCell(item.getInstruction());

            }

            document.add(medicineTable);

            Paragraph testTitle = new Paragraph("LABORATORY INVESTIGATIONS", titleFont);

            testTitle.setSpacingBefore(10);

            testTitle.setSpacingAfter(8);

            document.add(testTitle);

            PdfPTable testTable = new PdfPTable(4);

            testTable.setWidthPercentage(100);

            testTable.setWidths(new float[]{2f,5f,2f,3f});

            String[] testHeader = {

                    "Code",

                    "Test Name",

                    "Status",

                    "Normal Range"

            };

            for(String h : testHeader){

                PdfPCell cell = new PdfPCell(new Phrase(h, sectionFont));

                cell.setBackgroundColor(new BaseColor(76,175,80));

                cell.setHorizontalAlignment(Element.ALIGN_CENTER);

                cell.setPadding(8);

                testTable.addCell(cell);

            }
            List<Tests> tests =
                    testsRepository.findByPrescriptionId(prescription.getId());

            for(Tests t : tests){

                testTable.addCell(t.getTestMaster().getTestCode());

                testTable.addCell(t.getTestMaster().getTestName());

                testTable.addCell(t.getOrderStatus());

                testTable.addCell(t.getTestMaster().getNormalRange());

            }

            document.add(testTable);

            Paragraph adviceTitle = new Paragraph("DOCTOR'S ADVICE", titleFont);

            adviceTitle.setSpacingBefore(15);

            document.add(adviceTitle);

            PdfPTable adviceTable = new PdfPTable(1);

            adviceTable.setWidthPercentage(100);

            PdfPCell adviceCell = new PdfPCell();

            adviceCell.setPadding(12);

            adviceCell.setBackgroundColor(new BaseColor(240,255,240));

            adviceCell.addElement(

                    new Paragraph(

                            prescription.getNotes()==null ? "" : prescription.getNotes(),

                            valueFont

                    )

            );

            adviceTable.addCell(adviceCell);

            document.add(adviceTable);

            Paragraph followTitle = new Paragraph("NEXT FOLLOW-UP", titleFont);

            followTitle.setSpacingBefore(15);

            document.add(followTitle);

            PdfPTable followTable = new PdfPTable(1);

            followTable.setWidthPercentage(40);

            PdfPCell followCell = new PdfPCell();

            followCell.setPadding(12);

            followCell.setHorizontalAlignment(Element.ALIGN_CENTER);

            followCell.setBackgroundColor(new BaseColor(255,245,230));

            String followDate = "Not Scheduled";

            if(prescription.getNextFollowUpDate()!=null){

                followDate = prescription.getNextFollowUpDate()

                        .format(DateTimeFormatter.ofPattern("dd MMM yyyy"));

            }

            Paragraph follow = new Paragraph(

                    followDate,

                    new Font(Font.FontFamily.HELVETICA,14,Font.BOLD,BaseColor.RED)

            );

            follow.setAlignment(Element.ALIGN_CENTER);

            followCell.addElement(follow);

            followTable.addCell(followCell);

            document.add(followTable);
    }
//       ===========QR Code=========
        BarcodeQRCode qr = new BarcodeQRCode(

                "ELITE CARE HOSPITAL\n"
                        + "Patient : " + prescription.getPatient().getName()
                        + "\nDoctor : " + prescription.getDoctor().getUser().getName()
                        + "\nPrescription : " + prescription.getId(),

                120,
                120,
                null
        );

        Image qrImage = qr.getImage();

        qrImage.scaleAbsolute(80,80);


//        ===============Footer Table===============
        PdfPTable footer = new PdfPTable(2);

        footer.setWidthPercentage(100);

        footer.setSpacingBefore(25);

        footer.setWidths(new float[]{1,1});

//        ============QR Cell============
        PdfPCell qrCell = new PdfPCell();

        qrCell.setBorder(Rectangle.NO_BORDER);

        qrCell.addElement(new Paragraph("Scan Verification", labelFont));

        qrCell.addElement(qrImage);

        footer.addCell(qrCell);

//    ===============Signature Cell=============
        PdfPCell signCell = new PdfPCell();

        signCell.setBorder(Rectangle.NO_BORDER);

        Paragraph line = new Paragraph("__________________________");

        line.setAlignment(Element.ALIGN_CENTER);

        signCell.addElement(line);

        Paragraph doctor = new Paragraph(

                "Dr. " + prescription.getDoctor().getUser().getName(),

                titleFont

        );

        doctor.setAlignment(Element.ALIGN_CENTER);

        signCell.addElement(doctor);

        Paragraph spec = new Paragraph(

                prescription.getDoctor().getSpecialization(),

                valueFont

        );

        spec.setAlignment(Element.ALIGN_CENTER);

        signCell.addElement(spec);

        footer.addCell(signCell);

        document.add(footer);

//        =============Footer Text============
        Paragraph footerText = new Paragraph(

                "ELITE CARE HOSPITAL\n"
                        + "Caring for every moment...\n"
                        + "Powered By Elite IT Institute",

                valueFont

        );

        footerText.setAlignment(Element.ALIGN_CENTER);

        footerText.setSpacingBefore(20);

        document.add(footerText);


        document.close();

        return out.toByteArray();

    } catch (Exception e) {

        throw new RuntimeException(e);

    }
}
