package by.clevertec.klimov.cleverbank.dao;

import java.util.Optional;

public interface CrudDao<T, ID> {
  <S extends T> S save(S entity);

  Optional<T> findById(ID id);

  void deleteById(ID id);
}
