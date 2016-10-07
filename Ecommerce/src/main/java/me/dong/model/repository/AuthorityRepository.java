package me.dong.model.repository;

import me.dong.model.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Authority 엔티티의 CRUD처리 담당
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByAuthority(String name);
}
