package io.entities;

import lombok.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@Builder
@Getter
public class Document {
    private long id;
    private long documentNumber;
    private LocalDate openDate;
    private String companyName;
    private String inn;
    private String kpp;
}
