package by.clevertec.klimov.cleverbank.service;

import java.util.Optional;

public interface CrudService<T, ID> {
  int create(T entity);

  Optional<T> readById(ID id);

  <S extends T> S update(S entity);

  void deleteById(ID id);
}
