package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
//    Optional<User> findByUsername(String Username);
@Query(value = "SELECT * FROM user WHERE BINARY username = :username", nativeQuery = true)
    Optional<User> findByUsernameCaseSensitive(@Param("username") String username);
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isvarified = false")
    Optional<User>findByEmailAndIsVerifiedFalse(@Param("email") String email);
}
