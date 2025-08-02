package com.rio.Blogging.website.DTO;

import com.rio.Blogging.website.Modal.Post;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

public class ImageDto {
    private Long ImageId;
    private byte[] data;
    private Post post;
}
