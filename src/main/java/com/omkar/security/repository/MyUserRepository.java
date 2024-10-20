package com.omkar.security.repository;

import com.omkar.security.model.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyUserRepository extends JpaRepository<MyUserDetails,Integer> {


    public MyUserDetails findByUsername(String username);

}
