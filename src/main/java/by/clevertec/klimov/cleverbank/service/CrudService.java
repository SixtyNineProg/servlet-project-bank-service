package by.clevertec.klimov.cleverbank.service;

import java.util.Optional;

public interface CrudService<T, Id> {

  long create(T entity);

  Optional<T> readById(Id id);

  int update(T entity);

  int deleteById(Id id);
}
