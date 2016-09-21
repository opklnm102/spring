package me.dong.rest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import me.dong.rest.dto.Post;

@Repository ("postDao")
public interface PostDao {

	List<Post> getPostList();
	
	Post getPost(Long postId);
	
	void insertPost(Post post);
	
	Integer updatePost(Post post);
	
	Integer deletePost(Long postId);

}
