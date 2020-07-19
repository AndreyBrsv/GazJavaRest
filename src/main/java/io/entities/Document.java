package io.entities;

import lombok.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {
    private long id;
    private Long documentNumber;
    private Timestamp openDate;
    private String companyName;
    private String inn;
    private String kpp;

    public Document copy() {
        return new Document(this.id, this.documentNumber, this.openDate, this.companyName, this.inn, this.kpp);
    }
}
