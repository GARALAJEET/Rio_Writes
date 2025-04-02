package com.rio.Blogging.website.DTO;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;

public class PostDto {
    private Long postId;
    @NotEmpty(message = "Post title can't be empty")
    private String title;
    @NotNull(message = "Post content can't be null")
    private String content;
    private String imageUrl;
    private LocalDateTime addedDate;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate( LocalDateTime addedDate) {
        this.addedDate = addedDate;
    }
}
