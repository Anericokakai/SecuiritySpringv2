package com.springSecurity.springSecurityV2.Repository;

import com.springSecurity.springSecurityV2.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
}
