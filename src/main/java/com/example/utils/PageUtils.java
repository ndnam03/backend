package com.example.utils;

import com.example.payload.response.product.PageDTOResponse;

public class PageUtils {

    public static PageDTOResponse calculatePage(Integer size, Integer page, long totalElement) {
        PageDTOResponse pageDTOResponse = new PageDTOResponse();
        boolean isFirst = false;
        boolean isLast = false;
        long totalPage = 0;
        if (totalElement % size == 0) {
            totalPage = totalElement / size;
        } else {
            totalPage = totalElement / size + 1;
        }
        if (page == totalPage) {
            isFirst = true;
        }

        if (page == 1) {
            isFirst = true;
        }

        pageDTOResponse.setFirst(isFirst);
        pageDTOResponse.setLast(isLast);
        pageDTOResponse.setPage(page);
        pageDTOResponse.setTotalPage(totalPage);
        pageDTOResponse.setSize(size);
        pageDTOResponse.setTotalElement(totalElement);

        return pageDTOResponse;

    }
}
