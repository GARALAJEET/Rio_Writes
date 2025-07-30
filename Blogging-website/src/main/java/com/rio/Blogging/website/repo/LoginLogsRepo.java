package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.LoginLogs;
import com.rio.Blogging.website.Modal.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoginLogsRepo extends JpaRepository<LoginLogs,Long> {
    List<LoginLogs> findByUsername(String Username);
}
