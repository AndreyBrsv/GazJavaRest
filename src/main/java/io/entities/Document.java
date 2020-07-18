package io.entities;

import lombok.*;
import org.springframework.lang.NonNull;

import java.time.Instant;

@RequiredArgsConstructor
@Builder
@Getter
public class Document {

    private final long id;

    private final String documentNumber;

    private final Instant openDate;

    private final String companyName;

    private final String inn;

    private final String kpp;
}
