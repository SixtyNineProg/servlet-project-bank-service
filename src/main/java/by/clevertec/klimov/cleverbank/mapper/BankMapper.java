package by.clevertec.klimov.cleverbank.mapper;

import by.clevertec.klimov.cleverbank.dto.BankDto;
import by.clevertec.klimov.cleverbank.entity.Bank;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankMapper {

  BankMapper INSTANCE = Mappers.getMapper(BankMapper.class);

  BankDto toBankDto(Bank bank);

  List<BankDto> toBankDto(List<Bank> banks);

  @Mapping(target = "users", ignore = true)
  Bank toBank(BankDto bankDto);

  @Mapping(target = "users", ignore = true)
  List<Bank> toBank(List<BankDto> bankDto);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "users", ignore = true)
  Bank merge(BankDto source, @MappingTarget Bank target);
}
