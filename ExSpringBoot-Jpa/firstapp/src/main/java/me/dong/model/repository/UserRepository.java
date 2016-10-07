package me.dong.model.repository;

import me.dong.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CRUD 기능 담당
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
