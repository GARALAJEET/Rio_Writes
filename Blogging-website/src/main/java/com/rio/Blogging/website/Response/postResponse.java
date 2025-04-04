package com.rio.Blogging.website.Response;

import com.rio.Blogging.website.DTO.PostDto;
import com.rio.Blogging.website.Modal.Post;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor

public class postResponse
{
    List<PostDto>content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    private int totalPages;
    private boolean lastPage;
    private boolean firstPage;
    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
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
