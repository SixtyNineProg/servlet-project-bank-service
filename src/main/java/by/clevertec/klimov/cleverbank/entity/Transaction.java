package by.clevertec.klimov.cleverbank.entity;

import by.clevertec.klimov.cleverbank.emum.TransactionType;
import java.util.Date;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

    private long id;

    private TransactionType type;

    private double amount;

    private Date date;

    private UUID uuid;
}
