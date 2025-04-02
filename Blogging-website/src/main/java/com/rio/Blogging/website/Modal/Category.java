package com.rio.Blogging.website.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryid;
    private String categorytitle;
    private String categotydescription;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch= FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();

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
