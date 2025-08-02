package com.rio.Blogging.website.security;

import com.rio.Blogging.website.Modal.User;
import com.rio.Blogging.website.repo.userRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class myUserDetailService implements UserDetailsService {
   private  final userRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepo.findByUsernameCaseSensitive(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("user not found");
        }
        return new userDetailImp(user.get());
    }
}
