package me.dong.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dong on 2017-02-02.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
