package io.entities;


import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
public class Transaction {
    private Long id;
    private Long documentId;
    private Long uuid;
    private Instant time;
    private BigDecimal sum;
    private BigDecimal transactionFee;
}
