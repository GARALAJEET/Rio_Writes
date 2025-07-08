package com.rio.Blogging.website.security;

import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.repo.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class myUserDetailService implements UserDetailsService {
   private userRepo userRepo;
   @Autowired
   public myUserDetailService(userRepo userRepo){
       this.userRepo=userRepo;
   }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepo.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }
        return new userDetailImp(user.get());
    }
}
