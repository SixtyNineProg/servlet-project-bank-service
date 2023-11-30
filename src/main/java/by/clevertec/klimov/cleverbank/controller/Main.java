package by.clevertec.klimov.cleverbank.controller;

import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.service.BankService;
import by.clevertec.klimov.cleverbank.service.impl.BankServiceImpl;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.IOException;

public class Main {
  public static void main(String[] args) throws DocumentException, IOException {

    //    Document document = new Document(PageSize.A4, 36, 36, 36, 36);
    //    PdfWriter writer =
    //        PdfWriter.getInstance(
    //            document, new FileOutputStream("c:\\Users\\Keks\\Desktop\\BankPdf.pdf"));
    //    document.open();
    //    FileInputStream template = new
    // FileInputStream("src/main/resources/Clevertec_Template.pdf");
    //    PdfReader reader = new PdfReader(template);
    //    PdfImportedPage page = writer.getImportedPage(reader, 1);
    //    PdfContentByte pdfContentByte = writer.getDirectContent();
    //    pdfContentByte.addTemplate(page, 0, 0, false);
    //    document.add(new Paragraph("Hello world, " + "this is a test pdf file."));
    //    Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
    //    Chunk chunk = new Chunk("Hello World", font);
    //    document.add(chunk);
    //    document.close();
    //    System.out.println("PDF Created");

    //    PdfReader reader = new PdfReader("src/main/resources/Clevertec_Template.pdf");
    //    OutputStream outputStream = new FileOutputStream("c:\\Users\\Keks\\Desktop\\BankPdf.pdf");
    //    PdfStamper stamper = new PdfStamper(reader, outputStream);
    //    Map<String, Object> data = new HashMap<>();
    //    data.put("object", new Object());
    //    stamper.setFormFlattening(true);
    //    stamper.getAcroFields().setFields(data);
    //    stamper.close();
    //    reader.close();
    //    System.out.println("PDF created and printed successfully.");

    //    try {
    //      // Load the PDF template
    //      PdfReader reader = new PdfReader("src/main/resources/Clevertec_Template.pdf");
    //      // Create a new PDF file
    //      Document document = new Document();
    //      PdfCopy copy = new PdfCopy(document, new
    // FileOutputStream("c:\\Users\\Keks\\Desktop\\BankPdf.pdf"));
    //      document.open();
    //      // Add the template to the new PDF file
    //      copy.addDocument(reader);
    //      // Add "Hello world" to the new PDF file
    //      document.add(new Paragraph("Hello world"));
    //      // Close the new PDF file
    //      document.close();
    //      // Print the new PDF file
    //      // ...
    //    } catch (Exception e) {
    //      e.printStackTrace();
    //    }

    //    createPdf("c:\\\\Users\\\\Keks\\\\Desktop\\\\BankPdf.pdf");
    //    createPdfFromTemplate("src/main/resources/Clevertec_Template.pdf");

    BankService bankService = new BankServiceImpl();
    bankService.writeToPdf(4L);
  }

  //  public static void createPdf(String filename) {
  //    Document document = new Document();
  //    try {
  //      try {
  //        PdfWriter.getInstance(document, new FileOutputStream(filename));
  //      } catch (FileNotFoundException e) {
  //        throw new RuntimeException(e);
  //      }
  //      document.open();
  //      document.add(new Paragraph("Hello World"));
  //    } catch (DocumentException e) {
  //      e.printStackTrace();
  //    } finally {
  //      document.close();
  //    }
  //  }

  //  public static void createPdfFromTemplate(String templatePath) {
  //    try (PDDocument document = Loader.loadPDF(new File(templatePath))) {
  //      PDPage page = document.getPage(0);
  //      PDRectangle mediabox = page.getMediaBox();
  //      float margin = 172;
  //      float startX = mediabox.getLowerLeftX() + margin;
  //      float startY = mediabox.getUpperRightY() - margin;
  //
  //      try (PDPageContentStream contentStream =
  //          new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true))
  // {
  //        contentStream.beginText();
  //        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);
  //        contentStream.newLineAtOffset(startX, startY);
  //        contentStream.showText("Hello world");
  //        contentStream.endText();
  //      }
  //
  //      document.save("c:\\Users\\Keks\\Desktop\\BankPdf.pdf");
  //    } catch (IOException e) {
  //      e.printStackTrace();
  //    }
  //  }
}
