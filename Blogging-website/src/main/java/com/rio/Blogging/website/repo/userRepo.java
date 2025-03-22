package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String Username);
}
