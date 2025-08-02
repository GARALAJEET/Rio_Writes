package com.rio.Blogging.website.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty(message = "Username can't be empty")
    private  String username;
    @Email(message = "Email should be valid")
    private  String email;
    @Size(max = 14, min = 6,message = "Password should be between 6 to 14 characters")
//    @JsonIgnore
    private String password;
    @NotEmpty(message = "About can't be empty")
    private String about;
    private  boolean isvarified=false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean isIsvarified() {
        return isvarified;
    }

    public void setIsvarified(boolean isvarified) {
        this.isvarified = isvarified;
    }
}
