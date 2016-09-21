package me.dong.model.repository;

import me.dong.model.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Dong on 2016-09-17.
 */
public interface PostRepository extends JpaRepository<Post, Long> {
}
