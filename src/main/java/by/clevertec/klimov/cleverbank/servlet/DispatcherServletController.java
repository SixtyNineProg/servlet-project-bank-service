package by.clevertec.klimov.cleverbank.servlet;

import by.clevertec.klimov.cleverbank.controller.Controller;
import java.lang.annotation.Annotation;
import java.util.Set;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class DispatcherServletController {

  {
    controllers = findAllClasses("src/main/java/by/clevertec/klimov/cleverbank", Controller.class);
  }

  private Set<Class<?>> controllers;

  public Set<Class<?>> findAllClasses(String packageName, Class<? extends Annotation> annotation) {
    return null;
  }

  private Class getClass(String className, String packageName) {
    try {
      return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
    } catch (ClassNotFoundException e) {
      // handle the exception
    }
    return null;
  }
}
