package by.clevertec.klimov.cleverbank.pdf.impl;

import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.pdf.PdfWriter;
import by.clevertec.klimov.cleverbank.util.ReflectionUtil;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class BankPdfWriter implements PdfWriter<BankDto> {

  public static final String PDF_FILE_NAME = "Bank.pdf";
  public static final String TEMPLATE_PDF_FILE_NAME = "Clevertec_Template.pdf";
  public static final int MARGIN_LEFT_X = 72;
  public static final int MARGIN_TOP_Y = 216;
  public static final int MARGIN_BOTTOM_Y = 72;
  public static final String TITLE_BANK_INFO = "Required bank info";
  public static final int SPACE_AFTER_TITLE = 30;
  public static final int TITLE_FONT_SIZE = 20;
  public static final int BODY_FONT_SIZE = 12;
  private float startX;
  private float startY;
  private float endY;
  private final PDFont pdFont = new PDType1Font(Standard14Fonts.FontName.COURIER);

  @Override
  public String printToPdf(BankDto bankDto) {
    Map<String, Object> bankForPdf = ReflectionUtil.mapObjectToMap(bankDto);
    Set<Map.Entry<String, Object>> entrySet = bankForPdf.entrySet();
    try (PDDocument document = Loader.loadPDF(getFileFromResources())) {
      PDPage page = document.getPage(0);
      PDRectangle mediabox = page.getMediaBox();
      startX = mediabox.getLowerLeftX() + MARGIN_LEFT_X;
      startY = mediabox.getUpperRightY() - MARGIN_TOP_Y;
      endY = mediabox.getLowerLeftY() + MARGIN_BOTTOM_Y;
      try (PDPageContentStream contentStream =
          new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
        printTitle(contentStream);
        printBody(entrySet, contentStream);
      }
      String pathToResultFile =
          Objects.requireNonNull(this.getClass().getResource("/")).getPath() + PDF_FILE_NAME;
      document.save(pathToResultFile);
      return pathToResultFile;
    } catch (IOException | URISyntaxException | IllegalArgumentException e) {
      return StringUtils.EMPTY;
    }
  }

  private File getFileFromResources() throws URISyntaxException {
    return new File(
        Objects.requireNonNull(getClass().getClassLoader().getResource(TEMPLATE_PDF_FILE_NAME))
            .toURI());
  }

  private void printTitle(PDPageContentStream contentStream) throws IOException {
    contentStream.beginText();
    contentStream.setFont(pdFont, TITLE_FONT_SIZE);
    contentStream.newLineAtOffset(startX, startY);
    contentStream.showText(TITLE_BANK_INFO);
    contentStream.endText();
    startY -= SPACE_AFTER_TITLE;
  }

  private void printBody(Set<Map.Entry<String, Object>> set, PDPageContentStream contentStream)
      throws IOException {
    for (Map.Entry<String, Object> entry : set) {
      contentStream.beginText();
      contentStream.setFont(pdFont, BODY_FONT_SIZE);
      contentStream.newLineAtOffset(startX, startY);
      contentStream.showText(entry.getKey() + " : " + entry.getValue());
      contentStream.endText();
      startY -= 20;
      if (startY < endY) {
        throw new IllegalArgumentException();
      }
    }
  }
}
