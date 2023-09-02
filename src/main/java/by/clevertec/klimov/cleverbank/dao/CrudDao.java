package by.clevertec.klimov.cleverbank.dao;

import java.util.Optional;

public interface CrudDao<T, ID> {
  int save(T entity);

  Optional<T> findById(ID id);

  void deleteById(ID id);
}
