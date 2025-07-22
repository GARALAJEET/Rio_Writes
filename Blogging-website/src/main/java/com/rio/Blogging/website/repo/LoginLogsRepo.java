package com.rio.Blogging.website.repo;

import com.rio.Blogging.website.Modal.LoginLogs;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogsRepo extends JpaRepository<LoginLogs,Long> {
}
