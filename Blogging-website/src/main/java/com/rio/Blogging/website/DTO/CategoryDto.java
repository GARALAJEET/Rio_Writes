package com.rio.Blogging.website.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Long categoryid;
    @NotEmpty(message = "Category title can't be empty")
    private String categorytitle;
    @NotEmpty(message = "Category description can't be empty")
    private String categotydescription;

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategorytitle() {
        return categorytitle;
    }

    public void setCategorytitle(String categorytitle) {
        this.categorytitle = categorytitle;
    }

    public String getCategotydescription() {
        return categotydescription;
    }

    public void setCategotydescription(String categotydescription) {
        this.categotydescription = categotydescription;
    }
}
