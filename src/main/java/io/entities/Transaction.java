package io.entities;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction implements Serializable {
    private long id;
    private Long documentId;
    private UUID uuid;
    private Timestamp time;
    private BigDecimal sum;
    private BigDecimal fee;

    public Transaction copy() {
        return new Transaction(this.id, this.documentId, this.uuid, this.time, this.sum, this.fee);
    }
}
