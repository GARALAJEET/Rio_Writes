package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface useRepo extends JpaRepository<User,Integer> {

}
