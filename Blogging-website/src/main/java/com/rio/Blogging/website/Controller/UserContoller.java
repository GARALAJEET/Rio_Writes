package com.rio.Blogging.website.Controller;

import com.rio.Blogging.website.DTO.UserDto;
import com.rio.Blogging.website.ReqObj.loginReq;
import com.rio.Blogging.website.ReqObj.validOTPObj;
import com.rio.Blogging.website.ReqObj.veriAcc;
import com.rio.Blogging.website.ServiceImp.UserserviceImp;
import com.rio.Blogging.website.security.JWTService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserContoller {

   private final UserserviceImp userserviceImp ;
   private final JWTService jwtService;


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
    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(){
        String token=jwtService.getToken();
        String username=jwtService.extractUserName(token);
        Long Id= (Long) userserviceImp.getId(username).getBody();

        return userserviceImp.getUser(Id);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(){
        return userserviceImp.getAllUsers();
    }
    @PutMapping("/updateUser")
    public ResponseEntity<?> updateUser( @RequestBody UserDto userDto){
        if(userDto==null){
            return ResponseEntity.badRequest().body("Enter data to update");
        }
        String token=jwtService.getToken();
        String username=jwtService.extractUserName(token);
        Long Id= (Long) userserviceImp.getId(username).getBody();
        return userserviceImp.updateUser(Id, userDto);
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(){
        String token=jwtService.getToken();
        String username=jwtService.extractUserName(token);
        Long id= (Long) userserviceImp.getId(username).getBody();
        return userserviceImp.deleteUser(id);
    }
    @PostMapping("/loginUser")
    public ResponseEntity<?>loginUser( @Valid @RequestBody loginReq reqUser){
        return userserviceImp.verifyUser(reqUser);
    }
    @GetMapping("/loginDetails")
    public ResponseEntity<?> loginDetails(){
        String token=jwtService.getToken();
        String username=jwtService.extractUserName(token);

        return userserviceImp.loginDetails(username);
    }

}
