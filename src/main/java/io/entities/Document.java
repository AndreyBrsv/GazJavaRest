package io.entities;

import lombok.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@Builder
@Getter
public class Document {
    private final long id;
    private final long documentNumber;
    private final LocalDate openDate;
    private final String companyName;
    private final String inn;
    private final String kpp;
}
