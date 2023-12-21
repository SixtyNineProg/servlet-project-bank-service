package by.clevertec.klimov.cleverbank.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Objects;

public class SessionRequestContent {

  /** The request attributes. */
  private final HashMap<String, Object> requestAttributes = new HashMap<>();

  /** The request parameters. */
  private final HashMap<String, String[]> requestParameters;

  /** The session attributes. */
  private final HashMap<String, Object> sessionAttributes = new HashMap<>();

  /** The invalidate session. */
  private boolean invalidateSession;

  /**
   * Instantiates a new session request content.
   *
   * @param request the request
   */
  public SessionRequestContent(HttpServletRequest request) {
    Enumeration<String> attributeNames = request.getAttributeNames();
    for (String s : Collections.list(attributeNames)) {
      requestAttributes.put(s, request.getAttribute(s));
    }
    requestParameters = new HashMap<>(request.getParameterMap());
    HttpSession session = request.getSession(false);
    if (Objects.nonNull(session)) {
      Enumeration<String> sessionAttributesNames = session.getAttributeNames();
      Collections.list(sessionAttributesNames)
          .forEach(attr -> sessionAttributes.put(attr, session.getAttribute(attr)));
    }
  }

  /**
   * Insert attributes.
   *
   * @param request the request
   */
  public void insertAttributes(HttpServletRequest request) {
    requestAttributes.forEach(request::setAttribute);
    HttpSession session = request.getSession(false);
    if (Objects.nonNull(session)) {
      sessionAttributes.forEach(session::setAttribute);
    }
    if (invalidateSession) {
      request.getSession().invalidate();
    }
  }

  /**
   * Gets the attribute by name.
   *
   * @param attributeName the attribute name
   * @return the attribute by name
   */
  public Object getAttributeByName(String attributeName) {
    return requestAttributes.get(attributeName);
  }

  /**
   * Put attribute.
   *
   * @param attributeName the attribute name
   * @param object the object
   * @return the object
   */
  public Object putAttribute(String attributeName, Object object) {
    return requestAttributes.put(attributeName, object);
  }

  /**
   * Gets the parameter by name.
   *
   * @param parameterName the parameter name
   * @return the parameter by name
   */
  public String getParameterByName(String parameterName) {
    String[] parameters = requestParameters.get(parameterName);
    return parameters.length != 0 ? parameters[0] : null;
  }

  /**
   * Gets the session attribute by name.
   *
   * @param sessionAttributeName the session attribute name
   * @return the session attribute by name
   */
  public Object getSessionAttributeByName(String sessionAttributeName) {
    return sessionAttributes.get(sessionAttributeName);
  }

  /**
   * Put session attribute.
   *
   * @param sessionAttributeName the session attribute name
   * @param object the object
   * @return the object
   */
  public Object putSessionAttribute(String sessionAttributeName, Object object) {
    return sessionAttributes.put(sessionAttributeName, object);
  }

  /** Invalidate session. */
  public void invalidateSession() {
    invalidateSession = true;
  }
}
