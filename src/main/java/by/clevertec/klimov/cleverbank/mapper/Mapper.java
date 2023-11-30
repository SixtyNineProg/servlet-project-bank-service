package by.clevertec.klimov.cleverbank.mapper;

import java.util.List;

public interface Mapper<T, V> {

  V mapToDto(T entityObject, Class<V> model);

  List<V> mapToDto(List<T> entityObjects, Class<V> model);

  List<T> mapToEntity(List<V> dtoObjects, Class<T> model);

  T mapToEntity(V dtoObject, Class<T> model);

  T merge(V source, T target);
}
