package com.cafe.repo;

import com.cafe.entity.User;
import com.cafe.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole role);

}
