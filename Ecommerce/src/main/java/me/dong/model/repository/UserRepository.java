package me.dong.model.repository;

import me.dong.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User 엔티티의 CRUD처리 담당
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);
}
