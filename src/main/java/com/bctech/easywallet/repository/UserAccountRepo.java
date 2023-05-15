package com.bctech.easywallet.repository;

import com.bctech.easywallet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepo extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
}
