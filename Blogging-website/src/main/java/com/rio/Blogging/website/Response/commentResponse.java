package com.rio.Blogging.website.Response;

import com.rio.Blogging.website.DTO.CommentDto;
import com.rio.Blogging.website.DTO.PostDto;

import java.util.List;

public class commentResponse {
    List<CommentDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
    private boolean firstPage;

    private int getTotalPages() {
        return totalPages;
    }
    private void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<CommentDto> getContent() {
        return content;
    }

    public void setContent(List<CommentDto> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public boolean isFirstPage() {
        return firstPage;
    }

    public void setFirstPage(boolean firstPage) {
        this.firstPage = firstPage;
    }

}
