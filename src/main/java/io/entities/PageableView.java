package io.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableView<T extends Serializable> implements Serializable {
    private List<T> entities;
    private Long idFrom;
    private int limit;
    private Long lastId;
    private int allIds;
}
