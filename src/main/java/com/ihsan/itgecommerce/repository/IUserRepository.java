package com.ihsan.itgecommerce.repository;

import com.ihsan.itgecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);
}
