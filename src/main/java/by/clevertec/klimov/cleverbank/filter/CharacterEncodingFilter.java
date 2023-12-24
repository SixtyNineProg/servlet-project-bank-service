package by.clevertec.klimov.cleverbank.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * The Class CharacterEncodingFilter.
 *
 * @author Vladislav Kuzmich
 */
@WebFilter(urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {

  /**
   * Do filter.
   *
   * @param req the Servlet request
   * @param resp the Servlet response
   * @param chain the Filter chain
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws ServletException the servlet exception
   */
  @Override
  public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
      throws IOException, ServletException {
    req.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
    resp.setCharacterEncoding(StandardCharsets.UTF_8.displayName());
    chain.doFilter(req, resp);
  }
}
