package com.rio.Blogging.website.Controller;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.ReqObj.loginReq;
import com.rio.Blogging.website.ReqObj.validOTPObj;
import com.rio.Blogging.website.ReqObj.veriAcc;
import com.rio.Blogging.website.ServiceImp.UserserviceImp;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserContoller {

   private final UserserviceImp userserviceImp ;


    @GetMapping("/Home")
    public String hello(){
        return "Hello from Rio";
    }
    @PostMapping("/newUser")
    public ResponseEntity<?> createUser( @Valid  @RequestBody UserDto userDto){
        ResponseEntity<?> ans= userserviceImp.createNewUser(userDto);
        System.out.println(ans);
        if(ans.getStatusCode().is2xxSuccessful()){
            System.out.println(ans);
            return userserviceImp.sentOTP(userDto.getusername());
        }
        return new ResponseEntity<>("User not created", org.springframework.http.HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/verifyAcc")
    public ResponseEntity<?> verifyAcc( @RequestBody veriAcc veriAcc){
        ResponseEntity<?>ans=userserviceImp.cheackUser(veriAcc);
        System.out.println(ans);
        if(ans.getStatusCode().is2xxSuccessful()){
            return userserviceImp.sentOTP(veriAcc.getUsername());
        }
        return new ResponseEntity<>("acc not  verfiy", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/verifyOTP")
    public ResponseEntity<?> verifyOTP(@RequestBody validOTPObj valid ){
        return userserviceImp.validateOTP( valid);
    }
    @GetMapping("/getUser/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id){
        return userserviceImp.getUser(id);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return userserviceImp.getAllUsers();
    }
    @PutMapping("/updateUser/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto){
        if(userDto==null){
            return ResponseEntity.badRequest().body("Enter data to update");
        }
        return userserviceImp.updateUser(id, userDto);
    }
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id){
        return userserviceImp.deleteUser(id);
    }
    @PostMapping("/loginUser")
    public ResponseEntity<?>loginUser( @Valid @RequestBody loginReq reqUser){
        return userserviceImp.verifyUser(reqUser);
    }
    @GetMapping("/loginDetails/{userName}")
    public ResponseEntity<?> loginDetails(@PathVariable("userName")String userName){
        return userserviceImp.loginDetails(userName);
    }

}
