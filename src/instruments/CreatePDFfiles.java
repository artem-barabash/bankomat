package instruments;

import model.Person;


import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CreatePDFfiles {
    public static void createDocumentOfTransakcia(Person person1, String path, float summ) throws IOException, DocumentException {
        var doc = new Document();
        String filePath = path + "\\" + generatePwd() + ".pdf";
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(filePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        doc.open();

        BaseFont bf = BaseFont.createFont("F:\\banking\\font\\times.ttf", "cp1251", BaseFont.EMBEDDED);
        Font caption = new Font(bf, 20, Font.BOLD, new CMYKColor(0, 255, 255,17));


        Paragraph title1 = new Paragraph("ТРАНЗАКЦІЯ", caption);
        title1.setAlignment(Element.ALIGN_CENTER);

        Chapter chapter1 = new Chapter(title1, 1);
        chapter1.setNumberDepth(0);

        Font paragraphFont = new Font(bf, 14);

        PdfPTable t = new PdfPTable(3);

        t.setSpacingBefore(25);
        t.setSpacingAfter(25);

        PdfPCell c1 = new PdfPCell(Phrase.getInstance(""));
        t.addCell(c1);
        PdfPCell c2 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("ПІБ")), paragraphFont));
        t.addCell(c2);
        PdfPCell c3 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("Номер рахунку")), paragraphFont));
        t.addCell(c3);
        t.addCell(new Phrase(String.valueOf(new Paragraph("Клієнт")), paragraphFont));
        t.addCell(new Phrase(String.valueOf(new Paragraph(person1.surname + " " + person1.name.substring(0, 1).toUpperCase() + "." + person1.fathername.substring(0, 1).toUpperCase() + ".")), paragraphFont));
        t.addCell(person1.getNumberCode());

        Paragraph titleService = new Paragraph("Знято з банкомату №1.", paragraphFont);


        PdfPTable sum = new PdfPTable(2);

        sum.setSpacingBefore(25);
        sum.setSpacingAfter(25);

        PdfPCell t1 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("Сума")), paragraphFont));
        sum.addCell(t1);
        PdfPCell t2 = new PdfPCell(new Phrase(String.valueOf(new Paragraph("Комісія")), paragraphFont));
        sum.addCell(t2);
        sum.addCell(new Phrase(String.valueOf(summ)));
        sum.addCell(new Phrase("0"));

        Paragraph date = new Paragraph(getDateInTransaction(), paragraphFont);

        Section section1 = chapter1.addSection("");
        section1.add(t);

        section1.add(sum);
        section1.add(titleService);
        section1.add(date);
        doc.add(chapter1);

        doc.close();
    }

    private static String getDateInTransaction() {
        SimpleDateFormat dnt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss  ");
        Date date = new Date();

        return dnt.format(date);
    }

    private static String generatePwd() {
        String charsCaps = "abcdefghijklmnopqrstuvwxyz";
        String nums = "0123456789";
        String passSymbols = charsCaps + nums;
        Random rnd = new Random();

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(passSymbols.charAt(rnd.nextInt(passSymbols.length())));
        }
        return sb.toString();
    }

}
