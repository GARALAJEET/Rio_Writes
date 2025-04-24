package com.rio.Blogging.website.Controller;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.ServiceImp.UserserviceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserContoller {
    @Autowired
    UserserviceImp userserviceImp = new UserserviceImp();

    @GetMapping("/Home")
    public String hello(){
        return "Hello from Rio";
    }
    @PostMapping("/newUser")
    public ResponseEntity<?> createUser( @Valid  @RequestBody UserDto userDto){return userserviceImp.createUser(userDto);}
    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        return userserviceImp.getUser(id);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return userserviceImp.getAllUsers();
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDto userDto){
        if(userDto==null){
            return ResponseEntity.badRequest().body("Enter data to update");
        }
        return userserviceImp.updateUser(id, userDto);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return userserviceImp.deleteUser(id);
    }

}
