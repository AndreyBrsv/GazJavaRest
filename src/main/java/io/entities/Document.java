package io.entities;

import lombok.*;

import java.time.Instant;

@Data
@NoArgsConstructor
public class Document {
    private long id;
    private long documentNumber;
    private Instant openDate;
    private String companyName;
    private String inn;
    private String kpp;
}
