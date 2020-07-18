package io.entities;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Builder
@Getter
public class Transaction {
    private final long id;
    private final long documentId;
    private final long uuid;
    private final LocalDateTime time;
    private final long sum;
    private final long transactionFee;
}
