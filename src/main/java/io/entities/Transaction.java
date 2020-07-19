package io.entities;


import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    private long id;
    private long documentId;
    private UUID uuid;
    private Timestamp time;
    private BigDecimal sum;
    private BigDecimal transactionFee;

    public Transaction copy() {
        return new Transaction(this.id, this.documentId, this.uuid, this.time, this.sum, this.transactionFee);
    }
}
