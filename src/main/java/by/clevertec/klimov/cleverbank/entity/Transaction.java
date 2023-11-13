package by.clevertec.klimov.cleverbank.entity;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private long id;

    private double amount;

    private Date date;

    private UUID uuid;

    private String authorizationCode;

    public Transaction(double amount, Date date, UUID uuid, String authorizationCode) {
        this.amount = amount;
        this.date = date;
        this.uuid = uuid;
        this.authorizationCode = authorizationCode;
    }
}
