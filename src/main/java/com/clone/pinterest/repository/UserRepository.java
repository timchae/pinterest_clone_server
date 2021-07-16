package com.clone.pinterest.repository;

import com.clone.pinterest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
