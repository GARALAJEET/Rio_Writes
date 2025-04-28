package com.rio.Blogging.website.resMsg;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.Modal.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class validOtp
{
    UserDto user;
    String msg;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
