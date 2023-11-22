package com.edublog.usecase;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PaginationTest {
    @Test
    public void buildPageRequest_ReturnsCorrectPageRequestWithDefaults() {
        PageRequest pageRequest = Pagination.buildPageRequest(null, null);
        assertThat(pageRequest.getPageNumber()).isEqualTo(0);
        assertThat(pageRequest.getPageSize()).isEqualTo(5);
    }

    @Test
    public void buildPageRequest_ReturnsCorrectPageRequestWithProvidedValues() {
        PageRequest pageRequest = Pagination.buildPageRequest(2, 10);
        assertThat(pageRequest.getPageNumber()).isEqualTo(1);
        assertThat(pageRequest.getPageSize()).isEqualTo(10);
    }

    @Test
    public void buildPageRequest_HandlesInvalidPageNumber() {
        PageRequest pageRequest = Pagination.buildPageRequest(-1, 10);
        assertThat(pageRequest.getPageNumber()).isEqualTo(0);
        assertThat(pageRequest.getPageSize()).isEqualTo(10);
    }

    @Test
    public void buildPageRequest_HandlesInvalidPageSize() {
        PageRequest pageRequest = Pagination.buildPageRequest(2, -1);
        assertThat(pageRequest.getPageNumber()).isEqualTo(1);
        assertThat(pageRequest.getPageSize()).isEqualTo(5);
    }

    @Test
    public void buildPageRequest_HandlesPageSizeTooLarge() {
        PageRequest pageRequest = Pagination.buildPageRequest(2, 16);
        assertThat(pageRequest.getPageNumber()).isEqualTo(1);
        assertThat(pageRequest.getPageSize()).isEqualTo(15);
    }

    @Test
    public void buildPageRequest_WithoutArguments_ReturnsDefaultPageRequest() {
        PageRequest pageRequest = Pagination.buildPageRequest();
        assertThat(pageRequest.getPageNumber()).isEqualTo(0);
        assertThat(pageRequest.getPageSize()).isEqualTo(5);
    }
}