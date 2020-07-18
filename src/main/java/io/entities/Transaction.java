package io.entities;


import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@RequiredArgsConstructor
@Builder
@Getter
public class Transaction {
    private final long id;
    private final long documentId;
    @NonNull
    private final UUID uuid;
    @NonNull
    private final Instant time;
    @NonNull
    private final BigDecimal sum;
    @NonNull
    private final BigDecimal transactionFee;
}
