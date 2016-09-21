package me.dong.rest.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import me.dong.rest.dto.Comment;

@Repository("commentDao")
public interface CommentDao {
	
	List<Comment> getCommentList(Long postId);
	
	void insertComment(Comment comment);
	
	Integer deleteComment(Long commentId);

}
