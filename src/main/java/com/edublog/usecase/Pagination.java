package com.edublog.usecase;

import org.springframework.data.domain.PageRequest;

public class Pagination {
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 5;

    public static PageRequest buildPageRequest(Integer pageNumber, Integer pageSize) {
        int queryPageNumber;
        int queryPageSize;

        if(pageNumber == null || pageNumber <= 1) {
            queryPageNumber = DEFAULT_PAGE_NUMBER;
        } else {
            queryPageNumber = pageNumber - 1;
        }

        if (pageSize == null || pageSize <= 1) {
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else if(pageSize >= 15) {
            queryPageSize = 15;
        } else {
            queryPageSize = pageSize;
        }

        return PageRequest.of(queryPageNumber, queryPageSize);
    }

    public static PageRequest buildPageRequest() {
        return PageRequest.of(DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }
}
