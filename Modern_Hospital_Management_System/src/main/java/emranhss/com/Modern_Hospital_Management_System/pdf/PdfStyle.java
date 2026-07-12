package emranhss.com.Modern_Hospital_Management_System.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class PdfStyle {

    // =====================================================
    // Hospital Main Font
    // =====================================================

    public static final Font HOSPITAL_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    22,
                    Font.BOLD,
                    new BaseColor(25,118,210)
            );

    // =====================================================
    // Page Title
    // =====================================================

    public static final Font TITLE_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    16,
                    Font.BOLD,
                    BaseColor.DARK_GRAY
            );

    // =====================================================
    // Section Header
    // =====================================================

    public static final Font SECTION_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    12,
                    Font.BOLD,
                    BaseColor.WHITE
            );

    // =====================================================
    // Label Font
    // =====================================================

    public static final Font LABEL_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    10,
                    Font.BOLD
            );

    // =====================================================
    // Value Font
    // =====================================================

    public static final Font VALUE_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    10
            );

    // =====================================================
    // Follow Up Font
    // =====================================================

    public static final Font FOLLOWUP_FONT =
            new Font(
                    Font.FontFamily.HELVETICA,
                    14,
                    Font.BOLD,
                    BaseColor.RED
            );

    // =====================================================
    // Colors
    // =====================================================

    public static final BaseColor PRIMARY =
            new BaseColor(25,118,210);

    public static final BaseColor GREEN =
            new BaseColor(76,175,80);

    public static final BaseColor ORANGE =
            new BaseColor(255,183,77);

    public static final BaseColor LIGHT_GREEN =
            new BaseColor(240,255,240);

    public static final BaseColor LIGHT_ORANGE =
            new BaseColor(255,245,230);

}