package io.entities;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document implements Serializable {
    private long id;
    private Long number;
    private Timestamp openDate;
    private String companyName;
    private String inn;
    private String kpp;

    public Document copy() {
        return new Document(this.id, this.number, this.openDate, this.companyName, this.inn, this.kpp);
    }
}
