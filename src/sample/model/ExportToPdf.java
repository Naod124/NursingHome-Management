package sample.model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class ExportToPdf {

    public void createSchedulePdf(ObservableList<PatientTable> patients, String filePath) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Paragraph pg = new Paragraph();
            pg.add("\n Your daily schedule : \n\n");
            PdfPCell pdfPCell = new PdfPCell(new Paragraph("Name"));

            document.add(pg);
            Image img = Image.getInstance("NursingLogo.png");
            img.setAbsolutePosition(455f, 755f);
            img.scaleAbsolute(120, 40);


            document.add(img);

            document.add(pdfPCell);
            PdfPTable table;
            table = new PdfPTable(4); // 3 columns.
            table.setWidthPercentage(100); //Width 100%
            table.setSpacingBefore(10f); //Space before table
            table.setSpacingAfter(10f); //Space after table

            float[] columnWidths = {1f, 1f, 1f, 1f};
            table.setWidths(columnWidths);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Patient name"));
            cell1.setBorderColor(BaseColor.BLACK);
            cell1.setPaddingLeft(10);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell2 = new PdfPCell(new Paragraph("Time from: "));
            cell2.setBorderColor(BaseColor.BLACK);
            cell2.setPaddingLeft(10);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Time to: "));
            cell3.setBorderColor(BaseColor.BLACK);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Description: "));
            cell3.setBorderColor(BaseColor.BLACK);
            cell3.setPaddingLeft(10);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

            cell1.setUseBorderPadding(true);
            cell2.setUseBorderPadding(true);
            cell3.setUseBorderPadding(true);
            cell4.setUseBorderPadding(true);


            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);


            ArrayList<PdfPCell> pdfcells = new ArrayList<>();
            for (PatientTable p : patients) {
                pdfcells.add(new PdfPCell(new Paragraph(p.getFullName())));
                pdfcells.add(new PdfPCell(new Paragraph(p.getTimeFrom())));
                pdfcells.add(new PdfPCell(new Paragraph(p.getTimeTo())));
                pdfcells.add(new PdfPCell(new Paragraph(p.getDescription())));


            }
            for (PdfPCell pdf : pdfcells) {
                table.addCell(pdf);
            }


            document.add(table);


            document.close();
            writer.close();


        } catch (Exception e) {
            System.out.println("did not work!");
            System.out.println(e.getMessage());
        }

    }

    public void createPatientPdf(ObservableList<PatientTable> patients, String filePath) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            PdfPTable table = new PdfPTable(4); // 3 columns.
            //schedulePdfDesign(table); //Change this to PatientPDfDesign since they have different get methods and different constructors.

            ArrayList<PdfPCell> pdfcell = new ArrayList<>();
            for (PatientTable p : patients) {
                pdfcell.add(new PdfPCell(new Paragraph(p.getFullName())));
                pdfcell.add(new PdfPCell(new Paragraph(p.getTimeFrom())));
                pdfcell.add(new PdfPCell(new Paragraph(p.getTimeTo())));
                pdfcell.add(new PdfPCell(new Paragraph(p.getDescription())));


            }
            for (PdfPCell pdf : pdfcell) {
                table.addCell(pdf);
            }

            document.add(table);
            document.close();
            writer.close();


            System.out.println("did work");
        } catch (Exception e) {
            System.out.println("did not work!");
            System.out.println(e.getMessage());
        }
    }

    public void createEmployeePdf(ObservableList<StaffTable> employees, String filePath) {
        Document document = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();
            PdfPTable table = new PdfPTable(4); // 3 columns.
            //employeePdfDesign(table);

            ArrayList<PdfPCell> pdfcells = new ArrayList<>();
            for (StaffTable p : employees) {
                pdfcells.add(new PdfPCell(new Paragraph(p.getName())));
                pdfcells.add(new PdfPCell(new Paragraph(p.getSsn())));
                pdfcells.add(new PdfPCell(new Paragraph(p.getEmail())));
                pdfcells.add(new PdfPCell(new Paragraph(p.getAddress())));
                pdfcells.add(new PdfPCell(new Paragraph(p.getRole())));

            }

            for (PdfPCell pdf : pdfcells) {
                table.addCell(pdf);
            }

            document.add(table);
            document.close();
            writer.close();

            System.out.println("did work");
        } catch (Exception e) {
            System.out.println("did not work!");
            System.out.println(e.getMessage());
        }
    }
}


   /* public void employeePdfDesign(PdfPTable table) throws DocumentException {
        table = new PdfPTable(4); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        float[] columnWidths = {1f, 1f, 1f, 1f};
        table.setWidths(columnWidths);
        PdfPCell cell1 = new PdfPCell(new Paragraph("First name: "));
        cell1.setBorderColor(BaseColor.BLACK);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell2 = new PdfPCell(new Paragraph("surname: "));
        cell2.setBorderColor(BaseColor.BLACK);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell3 = new PdfPCell(new Paragraph("SSN: "));
        cell3.setBorderColor(BaseColor.BLACK);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell4 = new PdfPCell(new Paragraph("Email: "));
        cell3.setBorderColor(BaseColor.BLACK);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell5 = new PdfPCell(new Paragraph("Address: "));
        cell3.setBorderColor(BaseColor.BLACK);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell6 = new PdfPCell(new Paragraph("Staff Role: "));
        cell3.setBorderColor(BaseColor.BLACK);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell1.setUseBorderPadding(true);
        cell2.setUseBorderPadding(true);
        cell3.setUseBorderPadding(true);
        cell4.setUseBorderPadding(true);

        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);

    }

    */

   /* public void schedulePdfDesign(PdfPTable table) throws DocumentException {


        table = new PdfPTable(4); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table

        float[] columnWidths = {1f, 1f, 1f, 1f};
        table.setWidths(columnWidths);
        PdfPCell cell1 = new PdfPCell(new Paragraph("Patient name"));
        cell1.setBorderColor(BaseColor.BLACK);
        cell1.setPaddingLeft(10);
        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell2 = new PdfPCell(new Paragraph("Time from: "));
        cell2.setBorderColor(BaseColor.BLACK);
        cell2.setPaddingLeft(10);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell3 = new PdfPCell(new Paragraph("Time to: "));
        cell3.setBorderColor(BaseColor.BLACK);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        PdfPCell cell4 = new PdfPCell(new Paragraph("Description: "));
        cell3.setBorderColor(BaseColor.BLACK);
        cell3.setPaddingLeft(10);
        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);

        cell1.setUseBorderPadding(true);
        cell2.setUseBorderPadding(true);
        cell3.setUseBorderPadding(true);
        cell4.setUseBorderPadding(true);


        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);

    }
}

    */
