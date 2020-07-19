package io.entities;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {
    private long id;
    private long documentNumber;
    private Timestamp openDate;
    private String companyName;
    private String inn;
    private String kpp;

    public Document copy() {
        return new Document(this.id, this.documentNumber, this.openDate, this.companyName, this.inn, this.kpp);
    }
}
