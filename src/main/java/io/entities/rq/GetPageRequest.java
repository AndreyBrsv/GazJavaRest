package io.entities.rq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPageRequest implements Serializable {
    private Integer page;
    private Integer limit;
}
