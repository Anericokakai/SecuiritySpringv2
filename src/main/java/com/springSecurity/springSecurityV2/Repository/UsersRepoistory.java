package com.springSecurity.springSecurityV2.Repository;

import com.springSecurity.springSecurityV2.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepoistory  extends JpaRepository<Users,Integer> {

    Optional<Users> findByEmail(String username);
}
