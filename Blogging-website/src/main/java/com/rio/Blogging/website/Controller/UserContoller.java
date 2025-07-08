package com.rio.Blogging.website.Controller;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.ReqObj.validOTPObj;
import com.rio.Blogging.website.ServiceImp.UserserviceImp;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/user")
public class UserContoller {
    @Autowired
    UserserviceImp userserviceImp = new UserserviceImp();
//    private  final RestTemplate restTemplate;
//    public UserContoller(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @GetMapping("/Home")
    public String hello(){
        return "Hello from Rio";
    }
    @PostMapping("/newUser")
    public ResponseEntity<?> createUser( @Valid  @RequestBody UserDto userDto){
        ResponseEntity<?> ans= userserviceImp.createNewUser(userDto);
        if(ans.getStatusCode().is2xxSuccessful()){
            return userserviceImp.sentOTP(userDto);
        }
        return new ResponseEntity<>("User not created", org.springframework.http.HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/verifyAcc")
    public ResponseEntity<?> verifyAcc(@Valid @RequestBody UserDto userDto){
        ResponseEntity<?>ans=userserviceImp.cheackUser(userDto);
        if(ans.getStatusCode().is2xxSuccessful()){
            return userserviceImp.sentOTP(userDto);
        }
        return new ResponseEntity<>("acc not  verfiy", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/verifyOTP")
    public ResponseEntity<?> verifyOTP(@RequestBody validOTPObj valid ){
        return userserviceImp.validateOTP( valid);
    }
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
