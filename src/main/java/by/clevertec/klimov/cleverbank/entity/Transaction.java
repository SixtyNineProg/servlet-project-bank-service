package by.clevertec.klimov.cleverbank.entity;

import java.time.LocalDateTime;

import by.clevertec.klimov.cleverbank.emum.TransactionType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

//    private long id;

    private TransactionType type;

    private double amount;

    private LocalDateTime date;
}
