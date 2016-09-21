package me.dong.model.repository;

import me.dong.model.domain.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dong on 2016-09-17.
 */
public interface HelloRepository extends JpaRepository<Hello, Long> {

}
