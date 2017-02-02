package me.dong.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dong on 2017-02-01.
 */
// SpringBoot가 알아서 생성
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
}
