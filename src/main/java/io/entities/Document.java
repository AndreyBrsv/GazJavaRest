package io.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Document {
    private long id;
    private long documentNumber;
    private LocalDate openDate;
    private String companyName;
    private String inn;
    private String kpp;
}
