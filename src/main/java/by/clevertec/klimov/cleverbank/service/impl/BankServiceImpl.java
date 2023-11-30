package by.clevertec.klimov.cleverbank.service.impl;

import by.clevertec.klimov.cleverbank.dao.BankDao;
import by.clevertec.klimov.cleverbank.dao.impl.BankDaoImpl;
import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.mapper.Mapper;
import by.clevertec.klimov.cleverbank.mapper.impl.BankMapper;
import by.clevertec.klimov.cleverbank.service.BankService;
import by.clevertec.klimov.cleverbank.util.ReflectionUtil;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

@Slf4j
@Data
public class BankServiceImpl implements BankService {

  public static final String ERROR_OCCURRED_WHILE_CREATE_BANK =
      "An error occurred while create bank";
  public static final String PDF_FILE_NAME = "Bank.pdf";
  public static final String TEMPLATE_PDF_FILE_NAME = "Clevertec_Template.pdf";
  private final BankDao bankDao = new BankDaoImpl();
  private final Mapper<Bank, BankDto> bankMapper = new BankMapper();

  @Override
  public long create(BankDto bank) {
    log.debug("Create bank: {}", bank);
    return bankDao.save(bankMapper.mapToEntity(bank, Bank.class));
  }

  @Override
  public Optional<BankDto> readById(Long id) {
    log.info("Read bank by id = {}", id);
    Optional<Bank> optionalBank = bankDao.findById(id);
    return optionalBank.map(bank -> bankMapper.mapToDto(bank, BankDto.class));
  }

  @Override
  public int update(BankDto bankDto) {
    log.debug("Update bank: {}", bankDto);
    Optional<Bank> optionalBank = bankDao.findById(bankDto.getId());
    if (optionalBank.isPresent()) {
      Bank dbBank = optionalBank.get();
      bankMapper.merge(bankDto, dbBank);
      log.info("Update user with id = {} successfully", bankDto.getId());
      return bankDao.update(dbBank);
    } else {
      log.info(String.format("Bank with id = %s not found", bankDto.getId()));
      return 0;
    }
  }

  @Override
  public int deleteById(Long id) {
    log.info("Delete bank by id = {}", id);
    return bankDao.deleteById(id);
  }

  @Override
  public String writeToPdf(Long id) {
    Optional<Bank> optionalBank = bankDao.findById(id);
    if (optionalBank.isPresent()) {
      BankDto dbBank = bankMapper.mapToDto(optionalBank.get(), BankDto.class);
      Map<String, Object> bankForPdf = ReflectionUtil.mapObjectToMap(dbBank);
      Set<Map.Entry<String, Object>> set = bankForPdf.entrySet();

      try (PDDocument document =
          Loader.loadPDF(
              new File(
                  Objects.requireNonNull(
                          getClass().getClassLoader().getResource(TEMPLATE_PDF_FILE_NAME))
                      .toURI()))) {
        PDPage page = document.getPage(0);
        PDRectangle mediabox = page.getMediaBox();
        float marginLeftX = 72;
        float marginTopY = 216;
        float marginBottomY = 72;
        float startX = mediabox.getLowerLeftX() + marginLeftX;
        float startY = mediabox.getUpperRightY() - marginTopY;
        float endY = mediabox.getLowerLeftY() + marginBottomY;

        try (PDPageContentStream contentStream =
            new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {

          contentStream.beginText();
          contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 20);
          contentStream.newLineAtOffset(startX, startY);
          contentStream.showText("Required bank info");
          contentStream.endText();
          startY -= 30;

          for (Map.Entry<String, Object> entry : set) {
            contentStream.beginText();
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText(entry.getKey() + " : " + entry.getValue());
            contentStream.endText();
            startY -= 20;
            if (startY < endY) {
              throw new IllegalArgumentException();
            }
          }
        }
        String pathToResultFile =
            Objects.requireNonNull(this.getClass().getResource("/")).getPath() + PDF_FILE_NAME;
        document.save(pathToResultFile);
        return pathToResultFile;
      } catch (IOException | URISyntaxException e) {
        return StringUtils.EMPTY;
      }
    } else {
      return StringUtils.EMPTY;
    }
  }
}
