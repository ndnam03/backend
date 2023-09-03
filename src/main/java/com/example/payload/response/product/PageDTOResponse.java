package com.example.payload.response.product;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PageDTOResponse<T> {

    private Integer size;
    private Integer page;
    private long totalElement;
    private boolean isLast;
    private boolean isFirst;
    private long totalPage;
    private List<T> data;
}
