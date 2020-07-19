package io.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableView<T extends Serializable> implements Serializable {
    List<T> entities;
    Long idFrom;
    int limit;
    Long lastId;
    int allIds;
}
