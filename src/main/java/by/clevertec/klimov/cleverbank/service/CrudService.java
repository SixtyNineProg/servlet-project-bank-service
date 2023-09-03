package by.clevertec.klimov.cleverbank.service;

import java.util.Optional;

public interface CrudService<T, ID> {
  int create(T entity);

  Optional<T> readById(ID id);

  int update(T entity);

  int deleteById(ID id);
}
