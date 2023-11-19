package by.clevertec.klimov.cleverbank.mapper.impl;

import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import by.clevertec.klimov.cleverbank.mapper.Mapper;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;

public class BankMapper implements Mapper<Bank, BankDto> {

  private final ModelMapper modelMapper = new ModelMapper();

  @Override
  public BankDto mapToDto(Bank entityObject, Class<BankDto> model) {
    return modelMapper.map(entityObject, model);
  }

  @Override
  public List<BankDto> mapToDto(List<Bank> entityObjects, Class<BankDto> model) {
    return entityObjects.stream().map(entityObject -> mapToDto(entityObject, model)).toList();
  }

  @Override
  public List<Bank> mapToEntity(List<BankDto> dtoObjects, Class<Bank> model) {
    return dtoObjects.stream().map(dtoObject -> mapToEntity(dtoObject, model)).toList();
  }

  @Override
  public Bank mapToEntity(BankDto dtoObject, Class<Bank> model) {
    return modelMapper.map(dtoObject, model);
  }

  @Override
  public Bank merge(BankDto source, Bank target) {
    ModelMapper mergeModelMapper = new ModelMapper();
    mergeModelMapper
        .getConfiguration()
        .setSkipNullEnabled(true)
        .setAmbiguityIgnored(true)
        .setMatchingStrategy(MatchingStrategies.STRICT);
    mergeModelMapper.addMappings(
        new PropertyMap<BankDto, Bank>() {
          @Override
          protected void configure() {
            skip(destination.getId());
          }
        });
    mergeModelMapper.map(source, target);
    return target;
  }
}
