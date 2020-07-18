package io.entities;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Builder
@Getter
public class Transaction {
    private long id;
    private long documentId;
    private long uuid;
    private LocalDateTime time;
    private long sum;
    private long transactionFee;
}
