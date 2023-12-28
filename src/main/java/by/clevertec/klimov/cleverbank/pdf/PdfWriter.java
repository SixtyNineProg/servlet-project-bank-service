package by.clevertec.klimov.cleverbank.pdf;

public interface PdfWriter<T> {

  String printToPdf(T object);
}
